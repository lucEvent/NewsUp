package com.lucevent.newsup.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.DBManager;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.io.SDManager;
import com.lucevent.newsup.net.NewsReader;

import java.util.ArrayList;
import java.util.Locale;

public class NewsManager {

    private static final double MAX_SITE_PETITIONS_SIMULTANEOUSLY_ON_SERVER = 5;

    private Context context;

    /**
     * Managers
     **/
    private static NewsReader newsreader;
    protected static DBManager dbmanager;
    protected static SDManager sdmanager;
    private static LogoManager logoManager;

    private static ConnectivityManager connectivityManager;

    public NewsManager(@NonNull Context context)
    {
        this.context = context;

        Date.setTitles(context.getResources().getStringArray(R.array.date_messages));

        if (dbmanager == null)
            dbmanager = new DBManager(context);

        if (sdmanager == null)
            sdmanager = new SDManager(context);

        if (newsreader == null && ProSettings.isProModeActivated())
            newsreader = new NewsReader(context.getString(R.string.app_version));

        if (connectivityManager == null)
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (AppData.sites == null)
            AppData.sites = Sites.getDefault(ProSettings.checkEnabled(ProSettings.FINLAND_SITES_KEY)
                    || Locale.getDefault().getLanguage().equals("fi"));

        if (logoManager == null)
            logoManager = LogoManager.getInstance(context, AppData.sites.size());
    }

    public Sites getFavoriteSites()
    {
        int[] favorite_codes = AppSettings.getFavoriteSitesCodes();
        Sites res = new Sites(favorite_codes.length);
        for (int code : favorite_codes) {
            res.add(AppData.getSiteByCode(code));
        }
        return res;
    }

    public News getNewsById(int id)
    {
        return dbmanager.readNews(id);
    }

    public void getNewsOf(@NonNull final Site site, @Nullable final int[] sections, @NonNull final Handler handler)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                if (isInternetAvailable()) {
                    int[] fSections = sections == null ? AppSettings.getMainSections(site) : sections;

                    NewsArray news = readNewsOf(site, fSections, handler, true, false);
                    for (News N : news) {
                        try {
                            dbmanager.insertNews(N);
                            sdmanager.saveNews(N);
                        } catch (Exception e) {
                            AppSettings.printerror("[NM2] Error when dbmanager.insertNews(N) in readNewsOf", e);
                        }
                    }
                    handler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
                } else
                    taskNoInternet(site, handler);
            }
        }).start();
    }

    public void getMainNews(@NonNull final Handler handler)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                if (isInternetAvailable()) {
                    int[] main_codes = AppSettings.getMainSitesCodes();
                    NewsSiteLoader[] tasks = new NewsSiteLoader[main_codes.length];

                    int i;
                    for (i = 0; i < Math.min(MAX_SITE_PETITIONS_SIMULTANEOUSLY_ON_SERVER, main_codes.length); ++i) {
                        Site site = AppData.getSiteByCode(main_codes[i]);
                        tasks[i] = new NewsSiteLoader(site, AppSettings.getMainSections(site), handler, false);
                        tasks[i].start();
                    }
                    for (; i < main_codes.length; ++i) {
                        Site site = AppData.getSiteByCode(main_codes[i]);
                        tasks[i] = new NewsSiteLoader(site, AppSettings.getMainSections(site), handler, true);
                        tasks[i].start();
                    }

                    long timeBound = System.currentTimeMillis() - AppSettings.getKeepTime() * 1000;
                    for (NewsSiteLoader task : tasks) {
                        try {
                            task.join();
                        } catch (InterruptedException e) {
                            AppSettings.printerror("", e);
                        }

                        for (News N : task.newsToSave)
                            try {
                                if (N.date >= timeBound) {
                                    dbmanager.insertNews(N);
                                    sdmanager.saveNews(N);
                                }
                            } catch (Exception e) {
                                AppSettings.printerror("[NM2] Error when dbmanager.insertNews(N) in readMainNews", e);
                            }
                    }
                    handler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();

                    AppSettings.printlog("Time bound: " + Date.getAge(timeBound));
                    NewsArray oldNews = dbmanager.deleteOldNews(timeBound);
                    for (News N : oldNews)
                        sdmanager.deleteNews(N);

                    AppSettings.printlog("[NM] readMainNews has finished");
                } else
                    taskNoInternet(null, handler);
            }
        }).start();
    }

    public NewsArray getScheduledNews(@NonNull int[] siteCodes)
    {
        if (isInternetAvailable()) {
            NewsArray res = new NewsArray();
            for (int siteCode : siteCodes) {
                try {

                    Site site = AppData.getSiteByCode(siteCode);
                    NewsArray news = readNewsOf(site, AppSettings.getDownloadSections(site), null, false, false/* de momento */);

                    for (News N : news) {
                        if (N.id == -1) {
                            dbmanager.insertNews(N);
                            sdmanager.saveNews(N);
                        }
                    }
                    res.addAll(news);

                } catch (Exception e) {
                    AppSettings.printerror("[NM] FATAL ERROR DURING SERVICE :(", e);
                }
            }
            return res;
        }
        return null;
    }

    private void taskNoInternet(@Nullable final Site site, @NonNull final Handler handler)
    {
        handler.obtainMessage(AppCode.NO_INTERNET, null).sendToTarget();
        if (site != null) {

            NewsMap history = getHistoryOf(site);
            handler.obtainMessage(AppCode.NEWS_MAP_READ, history).sendToTarget();

        } else {

            for (int siteCode : AppSettings.getMainSitesCodes()) {
                NewsMap history = getHistoryOf(AppData.getSiteByCode(siteCode));
                handler.obtainMessage(AppCode.NEWS_MAP_READ, history).sendToTarget();
            }
        }
        handler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
    }

    private NewsArray readNewsOf(@NonNull Site site, @NonNull int[] sections, @Nullable Handler handler, boolean onlyNew, boolean forceDevice)
    {
        NewsArray news;
        if (newsreader != null)
            news = newsreader.readNewsHeaders(site.code, sections);
        else
            news = site.readNewsHeaders(sections);

        news.setCode(site.code);

        if (handler != null)
            handler.obtainMessage(AppCode.NEWS_MAP_READ, new NewsMap(news)).sendToTarget();

        NewsMap history = getHistoryOf(site);

        if (onlyNew) {
            for (int i = 0; i < news.size(); ++i) {    // At first: set id of news that are already in the DB
                News N = news.get(i);
                if (history.contains(N)) {
                    N.id = history.ceiling(N).id;
                    news.remove(i);
                    i--;
                }
            }

            NewsMap newsMap = new NewsMap(news);
            for (News N : newsMap) {    // Next: read news content and store news that were not in the DB

                if (N.content == null || N.content.isEmpty())   // if there's need, read the content
                    if (newsreader != null && !forceDevice)
                        newsreader.readNewsContent(site, N);
                    else
                        site.readNewsContent(N);

                if (N.content != null && !N.content.isEmpty())  // If there is content, we save it...
                    history.add(N);
                else
                    news.remove(N);
            }

        } else {

            NewsMap newsMap = new NewsMap(news);
            for (News N : newsMap) {    // Next: read news content and store news that were not in the DB

                if (history.contains(N))
                    N.id = history.ceiling(N).id;

                else {   // id was not set in previous step, means it is not in the history

                    if (N.content == null || N.content.isEmpty())   // if there's need, read the content
                        if (newsreader != null && !forceDevice)
                            newsreader.readNewsContent(site, N);
                        else
                            site.readNewsContent(N);

                    if (N.content != null && !N.content.isEmpty())  // If there is content, we save it...
                        history.add(N);
                    else
                        news.remove(N);
                }
            }
        }
        return news;
    }

    private NewsMap getHistoryOf(Site site)
    {
        if (site.news == null || site.news.isEmpty()) {
            try {
                site.news = dbmanager.readNews(site);
            } catch (Exception e) {
                dbmanager = new DBManager(context);
                return getHistoryOf(site);
            }
        }
        return site.news;
    }

    private boolean isInternetAvailable()
    {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private class NewsSiteLoader extends Thread {

        final Site site;
        final int[] sections;
        final Handler handler;
        final boolean forceDevice;

        NewsArray newsToSave;

        NewsSiteLoader(Site site, int[] sections, Handler handler, boolean forceDevice)
        {
            this.site = site;
            this.sections = sections;
            this.handler = handler;
            this.forceDevice = forceDevice;
        }

        @Override
        public void run()
        {
            newsToSave = readNewsOf(site, sections, handler, true, forceDevice);
        }

    }

    public static News readContentOf(News news)
    {
        if (news.content == null || news.content.isEmpty())
            sdmanager.readNews(news);
        return news;
    }

    public static void fetchContentOf(final News news)
    {
        if (news.content == null || news.content.isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    Site site = AppData.getSiteByCode(news.site_code);
                    site.readNewsContent(news);

                    if (news.content != null && !news.content.isEmpty()) {
                        try {
                            dbmanager.insertNews(news);
                        } catch (Exception e) {
                            AppSettings.printerror("[NM] Error when dbmanager.insertNews(news)", e);
                        }
                        sdmanager.saveNews(news);
                        if (!site.news.contains(news))
                            site.news.add(news);
                    }
                }
            }).start();
        }
    }

    public static void addToHistory(News news)
    {
        dbmanager.insertHistoryNews(news);
    }

    public ArrayList<Pair<Integer, Integer>> getReadingStats()
    {
        return dbmanager.readReadingStats();
    }

    public void clearReadingStats()
    {
        dbmanager.deleteReadingStats();
    }

    public static long getCacheSize()
    {
        return sdmanager.getCacheSize();
    }

    public static void cleanCache()
    {
        sdmanager.wipeData();
        dbmanager.wipeData();
        for (Site s : AppData.sites)
            s.news.clear();
    }

}
