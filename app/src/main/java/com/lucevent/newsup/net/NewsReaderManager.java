package com.lucevent.newsup.net;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Source;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.StorageCallback;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsReaderManager {

    private class NewsPetition {

        public Site site;
        public int[] sections;

    }

    private class EventNewsPetition extends NewsPetition {
        public Event event;
    }

    private static ConnectivityManager connectivityManager;

    private NewsReader newsreader;
    private Handler uiCallback;
    private StorageCallback storageCallback;

    /* Threads */
    private static final int NUM_SLAVE_THREADS = NewsReader.NUM_SERVERS;
    private final Thread masterThread;
    private final Thread slaveThreads[];

    private final ArrayList<NewsPetition> petitionQueue;
    private final NewsSet newsQueue;

    public NewsReaderManager(@NonNull Context context, @Nullable StorageCallback storageCallback)
    {
        this.storageCallback = storageCallback;

        newsreader = new NewsReader(context.getString(R.string.app_version));
        connectivityManager = new ConnectivityManager(context);

        petitionQueue = new ArrayList<>();
        newsQueue = new NewsSet();
        masterThread = new Thread(masterRunnable);
        masterThread.start();
        slaveThreads = new Thread[NUM_SLAVE_THREADS];
        for (int i = 0; i < NUM_SLAVE_THREADS; i++) {
            slaveThreads[i] = new Thread(slaveRunnable);
            slaveThreads[i].start();
        }
    }

    public void readNewsOf(@NonNull Site site, @Nullable int[] sections, Handler handler)
    {
        uiCallback = handler;
        addPetition(site, sections);
    }

    public void readNewsOf(@NonNull Sites sites, Handler handler)
    {
        uiCallback = handler;
        for (Site site : sites)
            addPetition(site, null);
    }

    public void readEvent(Event event, Handler handler)
    {
        uiCallback = handler;
        for (Source source : event.sources)
            addEventPetition(event, source.site, source.sections);
    }

    public NewsArray readNewsOf(Sites sites)
    {
        if (connectivityManager.isInternetAvailable()) {
            NewsArray res = new NewsArray();
            int server = 0;
            for (Site site : sites) {
                try {

                    NewsArray news = readHeaders(site, AppSettings.getDownloadSections(site));

                    NewsMap history = storageCallback.getSavedNews(site);

                    for (News n : news) {
                        if (!history.containsKey(n.id)) {
                            readContent(server % NewsReader.NUM_SERVERS, site, n);

                            if (!n.content.isEmpty())
                                storageCallback.saveNews(n);

                        }
                    }
                    res.addAll(news);

                } catch (Exception e) {
                    AppSettings.printerror("[NM] FATAL ERROR DURING SERVICE :(", e);
                }
            }
            return res;
        }
        return null;
    }

    public void cancelAll()
    {
        uiCallback = null;
        synchronized (petitionQueue) {
            petitionQueue.clear();
        }
        synchronized (newsQueue) {
            newsQueue.clear();
        }
    }

    private void addPetition(Site site, int[] sections)
    {
        NewsPetition petition = new NewsPetition();
        petition.site = site;
        petition.sections = sections == null ? AppSettings.getMainSections(site) : sections;
        synchronized (petitionQueue) {
            petitionQueue.add(petition);
            petitionQueue.notify();
        }
    }

    private void addEventPetition(Event event, Site site, int[] sections)
    {
        EventNewsPetition petition = new EventNewsPetition();
        petition.site = site;
        petition.sections = sections;
        petition.event = event;
        synchronized (petitionQueue) {
            petitionQueue.add(petition);
            petitionQueue.notify();
        }
    }

    private Runnable masterRunnable = new Runnable() {
        @Override
        public void run()
        {
            while (true) {
                NewsPetition petition = null;
                synchronized (petitionQueue) {
                    if (!petitionQueue.isEmpty()) {
                        petition = petitionQueue.get(0);
                        petitionQueue.remove(0);
                    }
                }
                if (petition == null) {
                    if (uiCallback != null) {
                        // Notify UI that news are loaded
                        uiCallback.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();

                        // Delete news already in db whose date<timeBound
                        long timeBound = System.currentTimeMillis() - AppSettings.getKeepTime() * 1000;
                        storageCallback.deleteOldNews(timeBound);
                    }
                    try {
                        synchronized (petitionQueue) {
                            petitionQueue.wait();
                        }
                    } catch (InterruptedException ignore) {
                    }
                    continue;
                }

                if (connectivityManager.isInternetAvailable()) {

                    NewsArray news = petition instanceof EventNewsPetition ?
                            readEventHeaders(((EventNewsPetition) petition).event, petition.site, petition.sections) :
                            readHeaders(petition.site, petition.sections);

                    if (news != null) {

                        storageCallback.getSavedNews(petition.site);

                        // Send news to Kernel
                        if (uiCallback != null)
                            uiCallback.obtainMessage(AppCode.NEWS_COLLECTION, news).sendToTarget();

                        // Add news to newsqueue
                        synchronized (newsQueue) {
                            newsQueue.addAll(news);
                            newsQueue.notifyAll();
                        }
                    }

                } else
                    onNoConnectionAvailable(petition);

            }
        }
    };

    private Runnable slaveRunnable = new Runnable() {
        @Override
        public void run()
        {
            while (true) {
                News news = null;
                synchronized (newsQueue) {
                    if (!newsQueue.isEmpty()) {
                        news = newsQueue.pollFirst();
                    }
                }
                if (news == null) {
                    try {
                        synchronized (newsQueue) {
                            newsQueue.wait();
                        }
                    } catch (InterruptedException ignore) {
                    }
                    continue;
                }

                Site site = AppData.getSiteByCode(news.site_code);

                NewsMap history = storageCallback.getSavedNews(site);
                if (!history.containsKey(news.id)) {

                    if (news.content.isEmpty())
                        readContent((int) (Thread.currentThread().getId()) % NewsReader.NUM_SERVERS, site, news);

                    if (!news.content.isEmpty())
                        storageCallback.saveNews(news);

                    synchronized (site.news) {
                        site.news.add(news);
                    }
                }
            }
        }
    };

    private void onNoConnectionAvailable(NewsPetition petition)
    {
        if (uiCallback == null)
            return;

        uiCallback.obtainMessage(AppCode.NO_INTERNET, null).sendToTarget();
        if (petition.site != null) {

            int[] section_codes = sectionIndexesToCodes(petition.site, petition.sections);

            NewsMap history = storageCallback.getSavedNews(petition.site, section_codes);

            if (petition instanceof EventNewsPetition) {
                NewsMap eventHistory = new NewsMap();
                for (News n : history.values())
                    if (n.hasKeyWords(((EventNewsPetition) petition).event.keyWords))
                        eventHistory.add(n);

                history = eventHistory;
            }

            if (uiCallback != null)
                uiCallback.obtainMessage(AppCode.NEWS_COLLECTION, history.values()).sendToTarget();

        } else {

            for (int siteCode : AppSettings.getMainSitesCodes()) {
                Site site = AppData.getSiteByCode(siteCode);
                int[] section_codes = sectionIndexesToCodes(petition.site, AppSettings.getMainSections(site));

                NewsMap history = storageCallback.getSavedNews(site, section_codes);
                if (uiCallback != null)
                    uiCallback.obtainMessage(AppCode.NEWS_COLLECTION, history.values()).sendToTarget();
            }
        }

        if (uiCallback != null)
            uiCallback.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
    }

    private NewsArray readHeaders(Site site, int[] section_indexes)
    {
        int[] section_codes = sectionIndexesToCodes(site, section_indexes);

        NewsArray news = newsreader.readNewsHeaders(site.code, section_codes);
        if (news == null) {
            try {
                news = site.readNewsHeaders(section_codes);
            } catch (Exception e) {
                AppSettings.printerror("Error reading header of " + site.name + ", sections " + Arrays.toString(section_indexes), e);
            }
        }

        return news;
    }

    private NewsArray readEventHeaders(Event event, Site site, int[] section_codes)
    {
        NewsArray news = newsreader.readEventHeaders(site.code, section_codes, event.code);
        if (news == null) {
            try {
                news = site.readNewsHeaders(section_codes);

                for (int i = news.size() - 1; i >= 0; i--)
                    if (!news.get(i).hasKeyWords(event.keyWords))
                        news.remove(i);

            } catch (Exception e) {
                AppSettings.printerror("Error reading header of " + site.name, e);
            }
        }

        return news;
    }

    private void readContent(int server, Site site, News news)
    {
        newsreader.readNewsContent(server, news);

        if (news.content.isEmpty()) {
            try {
                site.readNewsContent(news);
            } catch (Exception e) {
                AppSettings.printerror("Error reading content (in device) of " + news.link, e);
            }
        }
    }

    public void readNow(Site site, News news)
    {
        site.readNewsContent(news);

        if (!news.content.isEmpty())
            storageCallback.saveNews(news);
    }

    private int[] sectionIndexesToCodes(Site site, int[] indexes)
    {
        int[] codes = new int[indexes.length];

        Sections sections = site.getSections();
        for (int i = 0; i < indexes.length; i++)
            if (indexes[i] < sections.size())
                codes[i] = sections.get(indexes[i]).code;
            else
                codes[i] = -1;

        return codes;
    }

}