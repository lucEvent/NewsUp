package com.newsup.kernel;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SiteList;
import com.newsup.task.TaskMessage;

import java.util.Comparator;

public class MainPageCenter implements TaskMessage {

    private Handler handler;
    private NewsDataCenter datacenter;

    private ConnectivityManager connectivityManager;

    public MainPageCenter(NewsDataCenter datacenter, ConnectivityManager connectivityManager, Handler handler) {
        this.datacenter = datacenter;
        this.connectivityManager = connectivityManager;
        this.handler = handler;
    }

    private NewsMap newsmap;

    public void loadNews() {
        newsmap = new NewsMap(new Comparator<News>() {

            @Override
            public int compare(News o1, News o2) {
                int comparison = -Date.compare(o1.date, o2.date);
                return comparison != 0 ? comparison : (o1.id < o2.id ? -1 : (o1.id == o2.id ? 0 : 1));
            }
        });

        if (isInternetAvailable()) {
            SiteList sites = datacenter.getSites();
            int[] isites = datacenter.getSettings().main_sites_i;
            for (int i = 0; i < isites.length; ++i) {
                new NewsLoader(sites.get(isites[i]));
            }
        } else {
            new NoInternetTask();
        }
    }

    private class NewsLoader extends Thread implements com.newsup.task.Socket {

        private final Site site;

        NewsLoader(Site site) {
            this.site = site;
            this.start();
        }

        @Override
        public void run() {
            site.news = new NewsList();

            int[] sections = datacenter.getSettingsOf(site).sectionsOnMainIntegerArray();
            site.getReader().readNews(sections, this);

            datacenter.getSiteHistorial(site);

            for (News N : site.news) {
                // Mirar si esta en el historial
                if (site.historial.add(N)) {
                    // Si no, leer el contenido
                    site.getReader().readNewsContent(N);
                    // Si se ha podido leer el contenido
                    if (N.content != null) {
                        // insertar en la BD
                        try {
                            datacenter.createNews(site.code, N);
                        } catch (Exception e) {
                            debug("[Error al insertar la noticia en la BD");
                        }
                        // guardar el contenido en disco
                        datacenter.saveNews(N);
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
                    //syncronize TODO
                    site.news.add(news);
                    newsmap.add(news);
                    //liberar TODO
                    break;
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
                case SECTION_BEGIN:
                    break;
            }
        }
    }

    private class NoInternetTask extends Thread {

        private NoInternetTask() {
            this.start();
        }

        @Override
        public void run() {
            handler.obtainMessage(NO_INTERNET, null).sendToTarget();

            SiteList sites = datacenter.getSites();
            int[] isites = datacenter.getSettings().main_sites_i;
            for (int i = 0; i < isites.length; ++i) {
                Site site = sites.get(isites[i]);

                site = datacenter.getSiteHistorial(site);

                newsmap.addAll(site.historial);
            }

            handler.obtainMessage(NEWS_READ_HISTORY, newsmap).sendToTarget();
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