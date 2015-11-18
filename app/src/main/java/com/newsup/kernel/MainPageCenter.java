package com.newsup.kernel;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.SiteList;
import com.newsup.net.NewsReader;
import com.newsup.task.TaskMessage;

public class MainPageCenter implements TaskMessage {

    private Handler handler;
    private NewsDataCenter datacenter;

    private ConnectivityManager connectivityManager;

    public MainPageCenter(NewsDataCenter datacenter, ConnectivityManager connectivityManager, Handler handler) {
        this.datacenter = datacenter;
        this.connectivityManager = connectivityManager;
        this.handler = handler;
    }

    public void loadNews() {
        if (isInternetAvailable()) {

            new NewsLoader().start();

        } else {
            new NoInternetTask().start();
        }
    }

    private class NewsLoader extends Thread {

        @Override
        public void run() {
            SiteList sites = datacenter.getSites();
            int[] isites = datacenter.getSettings().main_sites_i;
            NewsSiteLoader[] tasks = new NewsSiteLoader[isites.length];

            for (int i = 0; i < isites.length; ++i) {
                tasks[i] = new NewsSiteLoader(sites.get(isites[i]));
                tasks[i].start();
            }

            for (NewsSiteLoader task : tasks) {
                try {
                    task.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                NewsList newstosave = task.newsToSave;
                for (News N : newstosave) {
                    datacenter.createNews(task.site.code, N);
                    datacenter.saveNews(N);
                }
            }
            debug("Acabando en NewsLoader");
        }
    }

    private class NewsSiteLoader extends Thread implements com.newsup.task.Socket {

        final Site site;
        NewsList newsToSave;

        NewsSiteLoader(Site site) {
            this.site = site;
        }

        @Override
        public void run() {
            newsToSave = new NewsList();

            site.news = new NewsList();
            datacenter.getSiteHistorial(site);

            int[] sections = datacenter.getSettingsOf(site).sectionsOnMainIntegerArray();
            datacenter.getNewsreader().readNews(site, sections, this);

            for (News N : site.news) {
                // Mirar si esta en el historial
                if (site.historial.add(N)) {
                    // Si no, leer el contenido
                    datacenter.getNewsreader().readNewsContent(site, N);
                    // Si se ha podido leer el contenido
                    if (N.content != null && !N.content.isEmpty()) {
                        // Guardar para que el thread principal lo guarde en la BD
                        newsToSave.add(N);

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
        public void message(int message, Object dataAttached) {
            switch (message) {
                case NEWS_READ:
                    handler.obtainMessage(message, dataAttached).sendToTarget();

                    News news = (News) dataAttached;
                    news.site = site;
                    site.news.add(news);
                    break;
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
            }
        }
    }

    private class NoInternetTask extends Thread {

        @Override
        public void run() {
            handler.obtainMessage(NO_INTERNET, null).sendToTarget();

            SiteList sites = datacenter.getSites();
            int[] isites = datacenter.getSettings().main_sites_i;
            for (int i = 0; i < isites.length; ++i) {
                Site site = sites.get(isites[i]);

                site = datacenter.getSiteHistorial(site);

                handler.obtainMessage(NEWS_READ_HISTORY, site.historial).sendToTarget();
            }
        }
    }

    private void debug(String text) {
        android.util.Log.d("##MainPageCenter##", text);
    }

    private boolean isInternetAvailable() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}