package com.newsup.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.io.DBManager;
import com.newsup.io.SDManager;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SiteList;
import com.newsup.kernel.util.Date;
import com.newsup.net.NewsReader;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;
import com.newsup.task.TaskMessage;

import java.util.ArrayList;
import java.util.Comparator;

public class NewsDataCenter implements TaskMessage {

    /**
     * Static constants
     **/
    private final Context context;
    private final ConnectivityManager connectivityManager;
    private final Handler handler;

    /**
     * Controllers
     **/
    private NewsReader newsreader;
    private DBManager dbmanager;
    private SDManager sdmanager;

    /**
     * Variables
     **/
    private static AppSettings appSettings;
    private static SiteList sites;

    public NewsDataCenter(Context context) {
        this(context, null, null);
    }

    public NewsDataCenter(Context context, ConnectivityManager connectivityManager, Handler handler) {
        this.context = context;
        this.connectivityManager = connectivityManager;
        this.handler = handler;

        new Date(context);

        dbmanager = new DBManager(context);
        sdmanager = new SDManager(context);
        newsreader = new NewsReader();

        if (sites == null) {
            sites = new SiteList(context);
        }
        if (appSettings == null) {
            appSettings = sdmanager.readSettings();
        }
    }

    public SiteList getSites() {
        if (sites == null) {
            sites = new SiteList(context);
        }
        return sites;
    }

    public NewsReader getNewsreader() {
        return newsreader;
    }

    public void getNews(Site site, int[] sections) {

        if (isInternetAvailable()) {
            new NewsLoader(site, sections);
        } else {
            new NoInternetTask(site);
        }

    }

    private class NewsLoader extends Thread implements com.newsup.task.Socket {

        private NewsMap tempNewslist;

        private final Site site;
        private final int[] sections;

        NewsLoader(Site site, int[] sections) {
            this.site = site;
            this.sections = sections;
            this.start();
        }

        @Override
        public void run() {
            debug("Leyendo de site: " + site.name);
            tempNewslist = new NewsMap();

            int[] finalSections = sections;
            if (finalSections == null) {
                finalSections = getSettingsOf(site).sectionsOnMainIntegerArray();
            }

            newsreader.readNewsHeader(site, finalSections, this);

            getSiteHistory(site);

            int failCounter = 0;
            for (News N : tempNewslist) {

                if (site.history.add(N)) {  // if added, it means it was not in yet, so:

                    if (N.content == null || N.content.isEmpty()) {  // We read the content (if needed)
                        newsreader.readNewsContent(site, N);
                    }

                    if (N.content != null && !N.content.isEmpty()) { // If there is content, we save it...

                        try {   // We insert it in the database
                            dbmanager.insertNews(site.code, N);
                        } catch (Exception e) {
                            debug("[Error al insertar la noticia en la BD");
                        }
                        sdmanager.saveNews(N); // and we save it in the disc

                    } else {

                        failCounter++;
                        site.history.remove(N); // ... if there isn't content, we remove it from the history

                    }
                } else {

                    N.id = site.history.ceiling(N).id; // If not added, it means it already is in, so we
                }
            }
            debug("[" + site.name + "] Noticias que ha sido imposible leer:: " + failCounter);
        }

        @Override
        public void message(int taskMessage, Object dataAttached) {
            switch (taskMessage) {
                case NEWS_READ:
                    News news = (News) dataAttached;
                    news.site = site;
                    tempNewslist.add(news);
                    handler.obtainMessage(taskMessage, dataAttached).sendToTarget();
                    break;
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
            }
        }
    }

    public News getNewsContent(News news) {
        if (news.content == null || news.content.isEmpty()) {
            sdmanager.readNews(news);
        }
        return news;
    }

    public Site getSiteHistory(Site site) {
        if (site.history == null || site.history.isEmpty()) {
            try {
                site.history = dbmanager.readNews(site);
            } catch (Exception e) {
                dbmanager = new DBManager(context);
                return getSiteHistory(site);
            }
        }
        return site;
    }

    private class NoInternetTask extends Thread {

        private final Site site;

        private NoInternetTask(Site site) {
            this.site = site;
            this.start();
        }

        @Override
        public void run() {
            handler.obtainMessage(NO_INTERNET, null).sendToTarget();

            getSiteHistory(site);

            NewsMap reorder = new NewsMap(new Comparator<News>() {

                @Override
                public int compare(News o1, News o2) {
                    int comparison = -Date.compare(o1.date, o2.date);
                    return comparison != 0 ? comparison : (o1.id < o2.id ? -1 : (o1.id == o2.id ? 0 : 1));
                }
            });
            reorder.addAll(site.history);

            handler.obtainMessage(NEWS_READ_HISTORY, reorder).sendToTarget();
        }
    }

    public void createNews(int code, News news) {
        dbmanager.insertNews(code, news);
    }

    public void saveNews(News news) {
        sdmanager.saveNews(news);
    }

    /**
     * ************************* Settings methods ***********************************
     **/
    public SiteSettings getSettingsOf(Site site) {
        if (site.settings == null) {
            site.settings = sdmanager.readSettingsOf(site);
        }
        return site.settings;
    }

    public void setSettingsOf(Site site) {
        sdmanager.saveSettings(site.settings);
    }

    public void setSettingsWith(int settingcode, Object setting) {
        appSettings.setSetting(settingcode, setting);
        sdmanager.saveSettings(appSettings);
    }

    public AppSettings getSettings() {
        return appSettings;
    }

    public SiteList getFavoritesSites() {
        SiteList list = new SiteList();
        for (int i = 0; i < AppSettings.favorite_list.size(); ++i) {
            list.add(sites.get(AppSettings.favorite_list.get(i)));
        }
        return list;
    }

    public boolean isFavorite(Site site) {
        int position = sites.indexOf(site);
        for (int i = 0; i < AppSettings.favorite_list.size(); ++i)
            if (position == AppSettings.favorite_list.get(i)) return true;
        return false;
    }

    public void toggleFavorite(Site site) {
        int position = sites.indexOf(site);
        if (isFavorite(site)) {
            appSettings.setSetting(AppSettings.DEL_FAV_SITE, position);
        } else {
            appSettings.setSetting(AppSettings.ADD_FAV_SITE, position);
        }
        sdmanager.saveSettings(appSettings);
    }

    public long getCacheSize() {
        return sdmanager.getCacheSize();
    }

    public void cleanCache() {
        sdmanager.wipeData();
        dbmanager.wipeData();
        for (Site s : getSites()) {
            s.history = new NewsMap();
        }
    }

    /**
     * ************************************************************************
     **/
    private boolean isInternetAvailable() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void debug(String text) {
        android.util.Log.d("##NewsDataCenter##", text);
    }

}
