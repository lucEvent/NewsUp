package com.newsup.kernel;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Message;

import com.newsup.io.BookmarksManager;
import com.newsup.io.DBManager;
import com.newsup.io.SDManager;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SiteList;
import com.newsup.net.State;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;

import java.util.ArrayList;
import java.util.Comparator;

public class NewsDataCenter implements State {

    /**
     * Static constants
     **/
    private Context context;

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
    private Site currentsite;

    private Handler waiter;

    public NewsDataCenter(ContextWrapper context, Handler waiter) {
        this.context = context;
        dbmanager = new DBManager(context);
        sdmanager = new SDManager(context);
        bmmanager = new BookmarksManager(null);

        if (sites == null) {
            sites = new SiteList(handler, context);
        }
        if (appSettings == null) {
            appSettings = sdmanager.readSettings();
        }
        this.waiter = waiter;
    }

    public SiteList getSites() {
        if (sites == null) {
            sites = new SiteList(handler, context);
        }
        return sites;
    }


    public void getNews(Site site, int[] sections) {
        currentsite = site;
        currentsite.news = new NewsList();
        if (sections == null) {
            Boolean[] mainSections = getSettingsOf(site).sectionsOnMain;
            ArrayList<Integer> rsections = new ArrayList<Integer>();
            for (int i = 0; i < mainSections.length; ++i)
                if (mainSections[i]) rsections.add(i);
            sections = new int[rsections.size()];
            for (int i = 0; i < rsections.size(); ++i)
                sections[i] = rsections.get(i);

        }
        debug("Leyendo de site: " + site.name);
        site.getReader().readNews(sections);
    }

    public News getNewsContent(News news) {
        if (news.content == null || news.content.isEmpty()) {
            sdmanager.readNews(news);
        }
        return news;
    }

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SECTION_BEGIN:
                    waiter.dispatchMessage(msg);
                    break;
                case NEWS_READ:
                    News news = (News) msg.obj;
                    currentsite.news.add(news);
                    waiter.dispatchMessage(msg);
                    break;
                case NEWS_READ_CONTENT:
                    // waiter.dispatchMessage(msg);
                    break;
                case WORK_DONE:
                    new TaskMaintenanceNews(currentsite).start();
                    break;
                case NO_INTERNET:
                    no_internet_actions();
                    break;
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
            }
        }
    };

    private void no_internet_actions() {
        waiter.obtainMessage(NO_INTERNET, null).sendToTarget();
        if (currentsite.historial.isEmpty()) {
            currentsite.historial = dbmanager.readNews(currentsite.code);
        }
        NewsMap reorder = new NewsMap(new Comparator<News>() {

            @Override
            public int compare(News o1, News o2) {
                int comparison = -Date.compare(o1.date, o2.date);
                return comparison != 0 ? comparison : (o1.id < o2.id ? -1 : (o1.id == o2.id ? 0 : 1));
            }
        });
        reorder.addAll(currentsite.historial);

        waiter.obtainMessage(NEWS_READ_HISTORY, reorder).sendToTarget();
    }

    private class TaskMaintenanceNews extends Thread {

        public Site site;

        public TaskMaintenanceNews(Site site) {
            super();
            this.site = site;
        }

        @Override
        public void run() {

            if (site.historial.isEmpty()) {
                site.historial = dbmanager.readNews(site.code);
            }
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
    }

    ;

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

    private void debug(String text) {
        android.util.Log.d("##NewsDataCenter##", text);
    }


    private static ArrayList<Integer> bookmarkedNewsIds;

    public boolean isBookmarked(News currentNews) {
        if (bookmarkedNewsIds == null) {
            readBookmarkedNewsIds();
        }
        return bookmarkedNewsIds.contains(currentNews.id);
    }

    private void readBookmarkedNewsIds() {
        bookmarkedNewsIds = bmmanager.readBookmarkedNewsIds();
    }

    public void unBookmarkNews(News currentNews) {
        bmmanager.unBookmarkNews(currentNews);
    }

    public void bookmarkNews(News currentNews) {
        bmmanager.bookmarkNews(currentNews);
    }

}
