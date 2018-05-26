package com.lucevent.newsup.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.services.util.DownloadResponse;
import com.lucevent.newsup.view.util.NotificationBuilder;

import java.util.Calendar;

public class DownloadService extends IntentService {

    public DownloadService()
    {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        try {

            doWork(getApplicationContext(),
                    (Download) intent.getExtras().getSerializable(AppCode.DOWNLOAD_SCHEDULE));

        } catch (Exception e) {
            AppSettings.printerror("[SDS] Error executing service", e);
        }
    }

    private void doWork(Context context, Download job)
    {
        AppSettings.initialize(context);
        ScheduleManager dataManager = new ScheduleManager(context);
        if (!job.repeat) {
            Calendar calendar = Calendar.getInstance();
            int day = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7;

            job.days[day] = false;

            boolean maintain = false;
            for (boolean b : job.days)
                if (b) {
                    maintain = true;
                    dataManager.updateSchedule(job);
                    break;
                }

            if (!maintain)
                dataManager.deleteSchedule(job);

        }
        ScheduledDownloadReceiver.scheduleDownloads(context, dataManager.getSchedule());

        DownloadResponse resp = job.isEvent() ?
                dataManager.getEventNews(-job.id) :
                dataManager.getNews(job);

        if (job.notify && resp != null && !resp.isEmpty()) {
            String title;
            String[] headlines = new String[resp.headlines.size()];
            if (resp.sources.size() == 1) {
                Site s = AppData.getSiteByCode(resp.sources.get(0).siteCode);
                title = (s == null ? "" : s.name) + " [via News Up]";
                headlines[0] = resp.headlines.get(0);
            } else {
                title = "News Up";
                for (int i = 0; i < resp.headlines.size(); i++) {
                    Site s = AppData.getSiteByCode(resp.sources.get(i).siteCode);
                    String siteName = s == null ? "" : s.name;
                    headlines[i] = siteName + ": " + resp.headlines.get(i);
                }
            }

            // build notification
            Intent notificationIntent = new Intent(this, Main.class);
            notificationIntent.putExtra(AppCode.SOURCES, resp.sources);
            if (job.isEvent())
                notificationIntent.putExtra(AppCode.STRING_FILTERS, resp.filters);

            NotificationBuilder.notifyUser(this,
                    NotificationBuilder.build(this, notificationIntent, title, headlines));

        }
        if (resp == null) {
            //todo
        }
    }

}