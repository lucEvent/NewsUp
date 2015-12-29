package com.newsup.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.io.DBManager;
import com.newsup.io.SDManager;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.NewsMap;
import com.newsup.kernel.set.SiteList;
import com.newsup.kernel.util.Date;
import com.newsup.net.NewsReader;
import com.newsup.services.schedule.ScheduledDownloadReceiver;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;
import com.newsup.task.SocketMessage;

import java.util.Comparator;
import java.util.TreeSet;

public class NewsDataCenter {

    /**
     *
     **/
    private Context context;
    private Handler handler;

    /**
     * Controllers
     **/
    private NewsReader newsreader;
    private DBManager dbmanager;
    private SDManager sdmanager;

    /**
     * Static variables
     **/
    private static ConnectivityManager connectivityManager;
    private static AppSettings appSettings;
    private static SiteList sites;

    public NewsDataCenter(Context context) {
        this(context, null);
    }

    public NewsDataCenter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;

        new Date(context);

        dbmanager = new DBManager(context);
        sdmanager = new SDManager(context);
        newsreader = new NewsReader();

        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
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

    public void load_News_from(Site site, int[] sections) {

        if (isInternetAvailable()) new NewsLoader(site, sections);

        else new NoInternetTask(site);

    }

    public void load_Mainpage_News() {

        if (isInternetAvailable()) new NewsLoader();

        else new NoInternetTask(null);

    }

    public boolean download_News_for_service(int[] dl_sites_codes) {

        if (isInternetAvailable()) {
            for (int dl_sites_code : dl_sites_codes) {
                try {
                    NewsSiteLoader thread = new NewsSiteLoader(sites.getSiteByCode(dl_sites_code), NewsSiteLoader.OFFLINE_SECTIONS);
                    thread.start();
                    thread.join();

                    NewsMap newstosave = thread.newsToSave;
                    for (News N : newstosave) {
                        dbmanager.insertNews(N);
                        saveNews(N);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    debug("HA HABIDO UN ERROR FATAL");
                }
            }
            return true;
        }
        return false;
    }


    public void addToHistory(News news) {
        dbmanager.insertHistoryNews(news);
    }

    private class NewsLoader {

        private final Site site;
        private final int[] sections;

        NewsLoader(Site site, int[] sections) {
            this.site = site;
            this.sections = sections;
            new Thread(task_load_news_from).start();
        }

        private Runnable task_load_news_from = new Runnable() {

            private NewsMap tempNewslist;

            @Override
            public void run() {
                debug("Leyendo de site: " + site.name);
                tempNewslist = new NewsMap();

                int[] finalSections = sections;
                if (finalSections == null) {
                    finalSections = getSettingsOf(site).sectionsOnMainIntegerArray();
                }

                newsreader.readNewsHeader(site, finalSections, socket);

                getSiteHistory(site);
                //    handler.obtainMessage(NEWS_READ_HISTORY, site.history).sendToTarget(); //TODO solo historial de la section

                int failCounter = 0;
                for (News N : tempNewslist) {

                    if (site.history.add(N)) {  // if added, it means it was not in yet, so:

                        if (N.content == null || N.content.isEmpty()) {  // We read the content (if needed)
                            newsreader.readNewsContent(site, N);
                        }

                        if (N.content != null && !N.content.isEmpty()) { // If there is content, we save it...

                            try {   // We insert it in the database
                                dbmanager.insertNews(N);
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

            com.newsup.task.Socket socket = new com.newsup.task.Socket() {

                @Override
                public void message(int taskMessage, Object dataAttached) {
                    switch (taskMessage) {
                        case NEWS_READ:
                            News news = (News) dataAttached;
                            news.site_code = site.code;
                            tempNewslist.add(news);
                            handler.obtainMessage(taskMessage, dataAttached).sendToTarget();
                            break;
                        case ERROR:
                            debug("Error recibido por el Handler");
                            break;
                    }
                }
            };
        };

        NewsLoader() {
            site = null;
            sections = null;
            new Thread(task_load_mainpage_news).start();
        }

        private Runnable task_load_mainpage_news = new Runnable() {

            @Override
            public void run() {
                SiteList sites = getSites();

                NewsSiteLoader[] tasks = new NewsSiteLoader[AppSettings.MAIN_CODES.length];

                for (int i = 0; i < AppSettings.MAIN_CODES.length; ++i) {
                    tasks[i] = new NewsSiteLoader(sites.getSiteByCode(AppSettings.MAIN_CODES[i]), NewsSiteLoader.MAIN_SECTIONS);
                    tasks[i].start();
                }

                for (NewsSiteLoader task : tasks) {
                    try {
                        task.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    NewsMap newstosave = task.newsToSave;
                    for (News N : newstosave) {
                        dbmanager.insertNews(N);
                        saveNews(N);
                    }
                }
                debug("Acabando en NewsLoader");
            }
        };
    }

    private class NewsSiteLoader extends Thread implements com.newsup.task.Socket {

        public static final int MAIN_SECTIONS = 0;
        public static final int OFFLINE_SECTIONS = 0;


        final Site site;
        final int type_sections;

        NewsMap newsTempList;
        NewsMap newsToSave;

        NewsSiteLoader(Site site, int type_sections) {
            this.site = site;
            this.type_sections = type_sections;
        }

        @Override
        public void run() {
            newsTempList = new NewsMap();
            newsToSave = new NewsMap();

            getSiteHistory(site);

            int[] sections;
            if (type_sections == MAIN_SECTIONS) {
                sections = getSettingsOf(site).sectionsOnMainIntegerArray();
            } else {
                sections = getSettingsOf(site).sectionsOfflineIntegerArray();
            }
            newsreader.readNewsHeader(site, sections, this);

            for (News N : newsTempList) {

                if (site.history.add(N)) {  // if added, it means it was not in yet, so:

                    if (N.content == null || N.content.isEmpty()) {  // We read the content (if needed)
                        newsreader.readNewsContent(site, N);
                    }

                    if (N.content != null && !N.content.isEmpty()) { // If there is content, we save it...

                        newsToSave.add(N); // ... in the buffer. The master thread will save it in DB and disc

                    } else {

                        site.history.remove(N); // ... if there isn't content, we remove it from the history

                    }
                } else {

                    N.id = site.history.ceiling(N).id; // If not added, it means it already is in, so we

                }
            }
        }

        @Override
        public void message(int message, Object dataAttached) {
            switch (message) {
                case NEWS_READ:
                    if (handler != null) {
                        handler.obtainMessage(message, dataAttached).sendToTarget();
                    }
                    News news = (News) dataAttached;
                    news.site_code = site.code;
                    newsTempList.add(news);
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
            handler.obtainMessage(SocketMessage.NO_INTERNET, null).sendToTarget();

            if (site != null) {

                getSiteHistory(site);
                handler.obtainMessage(SocketMessage.HISTORY_READ, site.history).sendToTarget();

            } else {

                SiteList sites = getSites();

                for (int isite : AppSettings.MAIN_CODES) {
                    Site site = sites.getSiteByCode(isite);

                    site = getSiteHistory(site);

                    handler.obtainMessage(SocketMessage.HISTORY_READ, site.history).sendToTarget();
                }
            }
        }
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

    public void setSetting(int settingcode, Object setting) {
        appSettings.setSetting(settingcode, setting);
        sdmanager.saveAppSettings();

        if (settingcode == AppSettings.ADD_DL_SCHEDULE || settingcode == AppSettings.DEL_DL_SCHEDULE) {
            ScheduledDownloadReceiver.scheduleDownload(context, AppSettings.DL_SCHEDULES);
        }
    }

    public void updateSetting(int settingcode, int position, Object setting) {
        appSettings.updateSetting(settingcode, position, setting);
        sdmanager.saveAppSettings();

        if (settingcode == AppSettings.MOD_DL_SCHEDULE) {
            ScheduledDownloadReceiver.scheduleDownload(context, AppSettings.DL_SCHEDULES);
        }
    }

    public AppSettings getSettings() {
        return appSettings;
    }

    public SiteList getFavoritesSites() {
        SiteList list = new SiteList();
        for (int i = 0; i < AppSettings.FAV_CODES.size(); ++i) {
            list.add(sites.getSiteByCode(AppSettings.FAV_CODES.get(i)));
        }
        return list;
    }

    public boolean isFavorite(Site site) {
        for (int i = 0; i < AppSettings.FAV_CODES.size(); ++i)
            if (site.code == AppSettings.FAV_CODES.get(i)) return true;
        return false;
    }

    public void toggleFavorite(Site site) {
        if (isFavorite(site)) {
            appSettings.setSetting(AppSettings.DEL_FAV_CODE, site.code);
        } else {
            appSettings.setSetting(AppSettings.ADD_FAV_CODE, site.code);
        }
        sdmanager.saveAppSettings();
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

    public static SiteList getAppSites() {
        TreeSet<Site> orderedList = new TreeSet<Site>(new Comparator<Site>() {
            @Override
            public int compare(Site s1, Site s2) {
                return s1.name.compareTo(s2.name);
            }
        });
        for (Site site : sites) {
            if (site.code >= 0) {
                orderedList.add(site);
            }
        }
        return new SiteList(orderedList);
    }

    public static Site getSiteByCode(int code) {
        return sites.getSiteByCode(code);
    }

    private void debug(String text) {
        android.util.Log.d("##NewsDataCenter##", text);
    }

}
