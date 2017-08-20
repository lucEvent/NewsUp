package com.lucevent.newsup.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.kernel.stats.SiteStat;
import com.lucevent.newsup.kernel.stats.SiteStats;
import com.lucevent.newsup.kernel.stats.Statistics;
import com.lucevent.newsup.view.fragment.StatisticsFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StatisticsService extends Service {

    public static final int REQ_GET = 0;
    public static final int REQ_RESET = 1;
    public static final int REQ_SEND = 2;


    private final IBinder binder = new Binder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class Binder extends android.os.Binder {
        public StatisticsService getService()
        {
            return StatisticsService.this;
        }
    }

    private ConnectivityManager connectivityManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        switch (intent.getExtras().getInt(AppCode.SEND_REQUEST_CODE)) {
            case REQ_GET:
                getStatistics(null, StatisticsFragment.SortOrder.SORT_BY_NAME);
            case REQ_RESET:
                resetStatistics(null);
                break;
            case REQ_SEND:
                sendUpdate();
                break;
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    public boolean isInternetAvailable()
    {
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
        return ni != null && ni.isConnected() && ni.isAvailable();
    }

    public void getStatistics(final Handler handler, final StatisticsFragment.SortOrder order)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                String url = "";
                switch (order) {
                    case SORT_BY_NAME:
                        url = "http://newsup-2406.appspot.com/app?stats&options=s";
                        break;
                    case SORT_BY_NUM_REQUESTS:
                        url = "http://newsup-2406.appspot.com/app?stats&options=n";
                        break;
                    case SORT_BY_READINGS:
                        url = "http://newsup-2406.appspot.com/app?stats&options=r";
                        break;
                    case SORT_BY_TIME:
                        url = "http://newsup-2406.appspot.com/app?stats&options=t";
                }

                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    AppSettings.printerror("[SR] Problem fetching Statistics:" + url, e);
                }
                if (doc == null) return;

                Element stats = doc.select("statistics").get(0);
                long since = Long.parseLong(stats.attr("since"));
                long lastStart = Long.parseLong(stats.attr("laststart"));
                SiteStats siteStats = new SiteStats();

                for (Element siteData : stats.select("site")) {
                    int siteCode = Integer.parseInt(siteData.attr("cd"));
                    Site site = AppData.getSiteByCode(siteCode);
                    String siteName = site == null ? "Unknown" : site.name;
                    int nAccesses = Integer.parseInt(siteData.attr("rq"));
                    int readings = Integer.parseInt(siteData.attr("rd"));
                    long lastAccess = Long.parseLong(siteData.attr("lt"));
                    String version = siteData.attr("v");
                    siteStats.add(new SiteStat(siteName, siteCode, nAccesses, readings, lastAccess, version));
                }

                Statistics statistics = new Statistics(since, lastStart, siteStats);
                handler.obtainMessage(AppCode.STATISTICS, statistics).sendToTarget();
            }
        }).start();
    }

    public void resetStatistics(final Handler handler)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://newsup-2406.appspot.com/app?stats&reset").get();
                } catch (IOException e) {
                    AppSettings.printerror("[SR] Exception reseting Statistics", e);
                }
                if (doc == null) return;

                Element stats = doc.select("statistics").get(0);
                long since = Long.parseLong(stats.attr("since"));
                long lastStart = Long.parseLong(stats.attr("laststart"));

                Statistics statistics = new Statistics(since, lastStart, new SiteStats());
                handler.obtainMessage(AppCode.STATISTICS, statistics).sendToTarget();
            }
        }).start();
    }

    public void sendUpdate()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    Thread.sleep(60000);    // sendUpdate after 1 minute

                    KernelManager manager = new KernelManager(StatisticsService.this);
                    ArrayList<Pair<Integer, Integer>> readingStats = manager.getTempReadingStats();

                    if (!readingStats.isEmpty()) {
                        StringBuilder url = new StringBuilder("http://newsup-2406.appspot.com/app?notify&values=");
                        for (Pair<Integer, Integer> pair : readingStats) {
                            url.append(pair.first).append(",")
                                    .append(pair.second).append(",");
                        }

                        URL request = new URL(url.substring(0, url.length() - 1));
                        request.openStream().close();

                        manager.clearReadingStats();
                    }
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

}


