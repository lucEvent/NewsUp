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

public class NewsManager {

    private Context context;
    private Handler handler;

    /**
     * Managers
     **/
    private static NewsReader newsreader;
    protected static DBManager dbmanager;
    protected static SDManager sdmanager;
    private static LogoManager logoManager;

    /**
     * Static variables
     **/
    private static ConnectivityManager connectivityManager;

    public NewsManager(Context context)
    {
        this(context, null);
    }

    public NewsManager(Context context, @Nullable Handler handler)
    {
        this.context = context;
        this.handler = handler;

        Date.setTitles(context.getResources().getStringArray(R.array.date_messages));

        if (dbmanager == null)
            dbmanager = new DBManager(context);

        if (sdmanager == null)
            sdmanager = new SDManager(context);

        if (newsreader == null && ProSettings.isProModeActivated())
            newsreader = new NewsReader();

        if (connectivityManager == null)
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (AppData.sites == null)
            AppData.sites = Sites.getDefault(ProSettings.areFinlandSitesEnabled());

        if (logoManager == null)
            logoManager = LogoManager.getInstance(context, AppData.sites.size());
    }

    public void getNewsOf(Site site, int[] sections)
    {
        if (isInternetAvailable())
            new NewsLoader(site, sections);
        else
            new NoInternetTask(site);
    }

    public void getMainNews()
    {
        if (isInternetAvailable())
            new NewsLoader();
        else
            new NoInternetTask(null);
    }

    public boolean download_News_for_service(int[] sites_codes)
    {
        if (isInternetAvailable()) {
            for (int site_code : sites_codes) {
                try {

                    Site site = AppData.getSiteByCode(site_code);
                    NewsSiteLoader loader = new NewsSiteLoader(site, AppSettings.getDownloadSections(site));
                    loader.run();

                    for (News N : loader.newsToSave) {
                        dbmanager.insertNews(N);
                        saveNews(N);
                    }

                } catch (Exception e) {
                    AppSettings.printerror("[NM] FATAL ERROR DURING SERVICE :(", e);
                }
            }
            return true;
        }
        return false;
    }

    public News getNewsById(int id)
    {
        return dbmanager.readNews(id);
    }

    public static void addToHistory(News news)
    {
        dbmanager.insertHistoryNews(news);
    }

    private class NewsLoader {

        private final Site site;
        private final int[] sections;

        NewsLoader(@NonNull Site site, int[] sections)
        {
            this.site = site;
            this.sections = sections;
            new Thread(task_load_news_from).start();
        }

        private Runnable task_load_news_from = new Runnable() {

            @Override
            public void run()
            {
                AppSettings.printlog("[NM] Reading from: " + site.name);

                int[] finalSections = sections;
                if (finalSections == null)
                    finalSections = AppSettings.getMainSections(site);

                NewsMap tempNewsMap;
                if (newsreader != null)
                    tempNewsMap = new NewsMap(newsreader.readNewsHeaders(site, finalSections));
                else
                    tempNewsMap = new NewsMap(site.readNewsHeaders(finalSections));

                tempNewsMap.setCode(site.code);

                handler.obtainMessage(AppCode.NEWS_MAP_READ, tempNewsMap).sendToTarget();

                getSiteHistory(site);
                //    handler.obtainMessage(NEWS_READ_HISTORY, site.history).sendToTarget(); //TODO solo historial de la section

                int failCounter = 0;
                for (News N : tempNewsMap) {

                    if (site.news.add(N)) {  // if added, it means it was not in yet, so:

                        if (N.content == null || N.content.isEmpty())   // We read the content (if needed)
                            if (newsreader != null)
                                newsreader.readNewsContent(site, N);
                            else
                                site.readNewsContent(N);

                        if (N.content != null && !N.content.isEmpty()) { // If there is content, we save it...

                            try {   // We insert it in the database
                                dbmanager.insertNews(N);
                            } catch (Exception e) {
                                AppSettings.printerror("[NM] Error when dbmanager.insertNews(N)", e);
                            }
                            sdmanager.saveNews(N); // and we save it in the disc

                        } else {

                            failCounter++;
                            site.news.remove(N); // ... if there isn't content, we remove it from the history

                        }
                    } else {

                        N.id = site.news.ceiling(N).id; // If not added, it means it already is in, so we
                    }
                }
                AppSettings.printlog("[NM] [" + site.name + "] News impossible to read: " + failCounter);
            }

        };

        NewsLoader()
        {
            site = null;
            sections = null;
            new Thread(task_load_mainpage_news).start();
        }

        private Runnable task_load_mainpage_news = new Runnable() {

            @Override
            public void run()
            {

                int[] main_codes = AppSettings.getMainSitesCodes();
                NewsSiteLoader[] tasks = new NewsSiteLoader[main_codes.length];

                for (int i = 0; i < main_codes.length; ++i) {
                    Site site = AppData.getSiteByCode(main_codes[i]);
                    tasks[i] = new NewsSiteLoader(site, AppSettings.getMainSections(site));
                    tasks[i].start();
                }

                long timeBound = System.currentTimeMillis() - AppSettings.getKeepTime() * 1000;
                for (NewsSiteLoader task : tasks) {
                    try {
                        task.join();
                    } catch (InterruptedException e) {
                        AppSettings.printerror("", e);
                    }

                    NewsMap newstosave = task.newsToSave;
                    for (News N : newstosave)
                        if (N.date >= timeBound) {
                            dbmanager.insertNews(N);
                            saveNews(N);
                        }
                }
                AppSettings.printlog("Time bound: " + Date.getAge(timeBound));
                NewsArray oldNews = dbmanager.deleteOldNews(timeBound);
                for (News N : oldNews) {
                    sdmanager.deleteNews(N);
                }

                AppSettings.printlog("[NM] NewsLoader has finished");
            }
        };
    }

    private class NewsSiteLoader extends Thread {

        final Site site;
        final int[] sections;

        NewsMap newsToSave;

        NewsSiteLoader(Site site, int[] sections)
        {
            this.site = site;
            this.sections = sections;
        }

        @Override
        public void run()
        {
            newsToSave = new NewsMap();

            getSiteHistory(site);

            NewsMap newsTempMap;
            if (newsreader != null)
                newsTempMap = new NewsMap(newsreader.readNewsHeaders(site, sections));
            else
                newsTempMap = new NewsMap(site.readNewsHeaders(sections));

            newsTempMap.setCode(site.code);

            if (handler != null)
                handler.obtainMessage(AppCode.NEWS_MAP_READ, newsTempMap).sendToTarget();

            for (News N : newsTempMap) {

                if (site.news.add(N)) {  // if added, it means it was not in yet, so:

                    if (N.content == null || N.content.isEmpty())   // We read the content (if needed)
                        if (newsreader != null)
                            newsreader.readNewsContent(site, N);
                        else
                            site.readNewsContent(N);

                    if (N.content != null && !N.content.isEmpty())  // If there is content, we save it...
                        newsToSave.add(N); // ... in the buffer. The master thread will save it in DB and disc

                    else
                        site.news.remove(N); // ... if there isn't content, we remove it from the history

                } else {

                    N.id = site.news.ceiling(N).id; // If not added, it means it is in already

                }
            }
        }
    }

    public static News getNewsContent(News news)
    {
        if (news.content == null || news.content.isEmpty())
            sdmanager.readNews(news);
        return news;
    }

    public Site getSiteHistory(Site site)
    {
        if (site.news == null || site.news.isEmpty()) {
            try {
                site.news = dbmanager.readNews(site);
            } catch (Exception e) {
                dbmanager = new DBManager(context);
                return getSiteHistory(site);
            }
        }
        return site;
    }

    private class NoInternetTask extends Thread {

        private final Site site;

        private NoInternetTask(Site site)
        {
            this.site = site;
            this.start();
        }

        @Override
        public void run()
        {
            handler.obtainMessage(AppCode.NO_INTERNET, null).sendToTarget();
            if (site != null) {

                getSiteHistory(site);
                handler.obtainMessage(AppCode.NEWS_MAP_READ, site.news).sendToTarget();

            } else {

                for (int isite : AppSettings.getMainSitesCodes()) {
                    Site site = AppData.getSiteByCode(isite);

                    site = getSiteHistory(site);

                    handler.obtainMessage(AppCode.NEWS_MAP_FRAGMENT_READ, site.news).sendToTarget();
                }
            }
        }
    }

    public void saveNews(News news)
    {
        sdmanager.saveNews(news);
    }

    public Sites getFavoritesSites()
    {
        int[] favorite_codes = AppSettings.getFavoriteSitesCodes();
        Sites res = new Sites(favorite_codes.length);
        for (int code : favorite_codes) {
            res.add(AppData.getSiteByCode(code));
        }
        return res;
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
            s.news = new NewsMap();
    }

    private boolean isInternetAvailable()
    {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public ArrayList<Pair<Integer, Integer>> getReadingStats()
    {
        return dbmanager.readReadingStats();
    }

    public void clearReadingStats()
    {
        dbmanager.deleteReadingStats();
    }

}

