package com.lucevent.newsup.kernel;

import android.content.Context;
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
import com.lucevent.newsup.io.StorageCallback;
import com.lucevent.newsup.net.NewsReaderManager;

import java.util.ArrayList;
import java.util.Locale;

public class KernelManager implements StorageCallback {

    private Context context;

    protected static DBManager dbmanager;
    protected static SDManager sdmanager;
    private static LogoManager logoManager;
    private static NewsReaderManager readerManager;

    public KernelManager(@NonNull Context context)
    {
        this.context = context;

        Date.setTitles(context.getResources().getStringArray(R.array.date_messages));

        if (dbmanager == null)
            dbmanager = new DBManager(context);

        if (sdmanager == null)
            sdmanager = new SDManager(context);

        if (AppData.sites == null)
            AppData.sites = Sites.getDefault(ProSettings.checkEnabled(ProSettings.FINLAND_SITES_KEY)
                    || Locale.getDefault().getLanguage().equals("fi"));

        if (logoManager == null)
            logoManager = LogoManager.getInstance(context, AppData.sites.size());

        if (readerManager == null)
            readerManager = new NewsReaderManager(context, null, this);
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
        readerManager.readNewsOf(site, sections, handler);
    }

    public void getMainNews(@NonNull final Handler handler)
    {
        readerManager.readNewsOf(AppData.getSites(AppSettings.getMainSitesCodes()), handler);
    }

    public NewsArray getScheduledNews(@NonNull int[] siteCodes)
    {
        return readerManager.readNewsOf(AppData.getSites(siteCodes));
    }

    @Override
    public void saveNews(News news)
    {
        try {
            dbmanager.insertNews(news);
            sdmanager.saveNews(news);
        } catch (Exception e) {
            AppSettings.printerror("Error on KM.saveNews", e);
        }
    }

    @Override
    public NewsMap getHistoryOf(Site site)
    {
        if (site.news == null) {
            try {
                site.news = dbmanager.readNews(site);
            } catch (Exception e) {
                dbmanager = new DBManager(context);
                return getHistoryOf(site);
            }
        }
        return site.news;
    }

    @Override
    public void deleteOldNews(long timeBound)
    {
        AppSettings.printlog("Time bound: " + Date.getAge(timeBound));
        NewsArray oldNews = dbmanager.deleteOldNews(timeBound);
        for (News N : oldNews)
            sdmanager.deleteNews(N);
    }

    public static News readContentOf(News news)
    {
        if (news.content == null || news.content.isEmpty())
            sdmanager.readNews(news);
        return news;
    }

    public static void fetchContentOf(News news)
    {
        if (news.content.isEmpty())
            readerManager.readNow(AppData.getSiteByCode(news.site_code), news);
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
