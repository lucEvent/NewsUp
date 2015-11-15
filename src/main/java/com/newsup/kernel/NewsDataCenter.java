package com.newsup.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.io.DBManager;
import com.newsup.io.SDManager;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SiteList;
import com.newsup.kernel.util.Date;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;
import com.newsup.task.TaskMessage;

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

    public void getNews(Site site, int[] sections) {

        if (isInternetAvailable()) {
            new NewsLoader(site, sections);
        } else {
            new NoInternetTask(site);
        }

    }

    private class NewsLoader extends Thread implements com.newsup.task.Socket {

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
            site.news = new NewsList();

            int[] finalSections = sections;
            if (finalSections == null) {
                finalSections = getSettingsOf(site).sectionsOnMainIntegerArray();
            }
//debug
     /*     for (int s = 0; s < site.getSections().size(); ++s) {
                Section section = site.getSections().get(s);
                debug("Leyendo: " + section.name);
                int[] isecs = new int[]{s};
                site.getReader().readNews(isecs, this);

                int nc = 0;
                for (News N : site.news) {
                    nc++;
                    debug("   Reading news:" + nc);
                    if (site.getReader().readNewsContent(N).content.isEmpty()) {
                        debug("NO SE HA PODIDO LEER: "+N.link);
                    };
                }
                site.news.clear();
            }
            if (true) return;*/
//end debug
            site.getReader().readNews(finalSections, this);

            getSiteHistorial(site);

            int failCounter = 0;
            for (News N : site.news) {
                // Mirar si esta en el historial
                if (site.historial.add(N)) {
                    // Si no, leer el contenido
                    if (N.content == null || N.content.isEmpty()) {
                        site.getReader().readNewsContent(N);
                    }
                    // Si se ha podido leer el contenido
                    if (N.content != null && !N.content.isEmpty()) {
                        // insertar en la BD
                        try {
                            dbmanager.insertNews(site.code, N);
                        } catch (Exception e) {
                            debug("[Error al insertar la noticia en la BD");
                        }
                        // guardar el contenido en disco
                        sdmanager.saveNews(N);
                    } else {
                        failCounter++;
                        site.historial.remove(N);
                    }
                } else {
                    // Si esta, asignar id para que al clickar se lea
                    N.id = site.historial.ceiling(N).id;
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
                    site.news.add(news);
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

    public Site getSiteHistorial(Site site) {
        if (site.historial == null || site.historial.isEmpty()) {
            try {
                site.historial = dbmanager.readNews(site);
            } catch (Exception e) {
                dbmanager = new DBManager(context);
                return getSiteHistorial(site);
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

            getSiteHistorial(site);

            NewsMap reorder = new NewsMap(new Comparator<News>() {

                @Override
                public int compare(News o1, News o2) {
                    int comparison = -Date.compare(o1.date, o2.date);
                    return comparison != 0 ? comparison : (o1.id < o2.id ? -1 : (o1.id == o2.id ? 0 : 1));
                }
            });
            reorder.addAll(site.historial);

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
            s.historial = new NewsMap();
            s.news = new NewsList();
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
