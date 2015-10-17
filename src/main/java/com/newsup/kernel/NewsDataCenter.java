package com.newsup.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.io.BookmarksManager;
import com.newsup.io.DBManager;
import com.newsup.io.SDManager;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SiteList;
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
    private DBManager dbmanager;
    private SDManager sdmanager;
    private BookmarksManager bmmanager;

    /**
     * Variables
     **/
    private static AppSettings appSettings;
    private static SiteList sites;

    public NewsDataCenter(Context context, ConnectivityManager connectivityManager, Handler handler) {
        this.context = context;
        this.connectivityManager = connectivityManager;
        this.handler = handler;

        dbmanager = new DBManager(context);
        sdmanager = new SDManager(context);
        bmmanager = new BookmarksManager(null);

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
            noInternetTasks(site);
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

            site.getReader().readNews(finalSections, this);

            getSiteHistorial(site);

            for (News N : site.news) {
                // Mirar si esta en el historial
                if (site.historial.add(N)) {
                    // Si no, leer el contenido
                    site.getReader().readNewsContent(N);
                    // Si se ha podido leer el contenido
                    if (N.content != null) {
                        // insertar en la BD
                        try {
                            dbmanager.insertNews(site.code, N);
                        } catch (Exception e) {
                            debug("[Error al insertar la noticia en la BD");
                        }
                        // guardar el contenido en disco
                        sdmanager.saveNews(N);
                    } else {
                        site.historial.remove(N);
                    }
                } else {
                    // Si esta, asignar id para que al clickar se lea
                    N.id = site.historial.ceiling(N).id;
                }
            }
        }

        @Override
        public void message(int taskMessage, Object dataAttached) {
            switch (taskMessage) {
                case NEWS_READ:
                    site.news.add((News) dataAttached);
                    handler.obtainMessage(taskMessage, dataAttached).sendToTarget();
                    break;
                case SECTION_BEGIN:
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
            site.historial = dbmanager.readNews(site.code);
        }
        return site;
    }

    private void noInternetTasks(Site site) {
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

    public void createNews(int code, News news) {
        dbmanager.insertNews(code, news);
    }

    public void saveNews(News news) {
        sdmanager.saveNews(news);
    }

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

    public boolean isBookmarked(News currentNews) {
        return getBookmarkedNewsIds().contains(currentNews.id);
    }

    private ArrayList<Integer> getBookmarkedNewsIds() {
        return bmmanager.readBookmarkedNewsIds();
    }

    public void unBookmarkNews(News currentNews) {
        bmmanager.unBookmarkNews(currentNews);
    }

    public void bookmarkNews(News currentNews) {
        bmmanager.bookmarkNews(currentNews);
    }

    private boolean isInternetAvailable() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void debug(String text) {
        android.util.Log.d("##NewsDataCenter##", text);
    }

}
