package com.newsup.kernel;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SiteList;
import com.newsup.settings.AppSettings;
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
            int[] isites = AppSettings.main_sites_i;
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

                NewsMap newstosave = task.newsToSave;
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

        NewsMap newsTempList;
        NewsMap newsToSave;

        NewsSiteLoader(Site site) {
            this.site = site;
        }

        @Override
        public void run() {
            newsTempList = new NewsMap();
            newsToSave = new NewsMap();

            datacenter.getSiteHistory(site);

            int[] sections = datacenter.getSettingsOf(site).sectionsOnMainIntegerArray();
            datacenter.getNewsreader().readNewsHeader(site, sections, this);

            for (News N : newsTempList) {

                if (site.history.add(N)) {  // if added, it means it was not in yet, so:

                    if (N.content == null || N.content.isEmpty()) {  // We read the content (if needed)
                        datacenter.getNewsreader().readNewsContent(site, N);
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
                    handler.obtainMessage(message, dataAttached).sendToTarget();

                    News news = (News) dataAttached;
                    news.site = site;
                    newsTempList.add(news);
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
            int[] isites = AppSettings.main_sites_i;
            for (int isite : isites) {
                Site site = sites.get(isite);

                site = datacenter.getSiteHistory(site);

                handler.obtainMessage(NEWS_READ_HISTORY, site.history).sendToTarget();
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