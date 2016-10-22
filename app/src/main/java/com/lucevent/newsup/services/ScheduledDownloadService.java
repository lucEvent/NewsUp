package com.lucevent.newsup.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.DownloadSchedule;
import com.lucevent.newsup.view.util.NotificationBuilder;

import java.util.Calendar;

public class ScheduledDownloadService extends IntentService {

    public ScheduledDownloadService()
    {
        super("ScheduledDownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        try {

            doWork(getApplicationContext(), intent);

        } catch (Exception e) {
            AppSettings.printerror("[SDS] Error executing service", e);
        }
    }

    private void doWork(Context context, Intent intent)
    {
        DownloadSchedule job = (DownloadSchedule) intent.getExtras().getSerializable(AppCode.SEND_DOWNLOAD_SCHEDULE);
        assert job != null : "DownloadSchedule received is null";

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
                    dataManager.updateDownloadSchedule(job);
                    break;
                }

            if (!maintain)
                dataManager.deleteDownloadSchedule(job);

        }
        ScheduledDownloadReceiver.scheduleDownloads(context, dataManager.getDownloadSchedules());

        NewsArray news = dataManager.getScheduledNews(job.sites_codes);

        if (job.notify && news != null && !news.isEmpty()) {
            int[] extras = new int[news.size()];
            String[] headlines = new String[job.sites_codes.length];

            int headlineIndex = 0;
            int currentSiteCode = -1;
            for (int index = 0; index < news.size(); index++) {
                News N = news.get(index);

                extras[index] = N.id;

                if (N.site_code != currentSiteCode) {
                    Site site = AppData.getSiteByCode(N.site_code);

                    headlines[headlineIndex++] = site.name + ": " + N.title;
                    currentSiteCode = N.site_code;
                }
            }

            // build notification
            Intent notificationIntent = new Intent(this, Main.class);
            notificationIntent.putExtra(AppCode.SEND_NEWS_IDS, extras);

            NotificationBuilder.notifyUser(this,
                    NotificationBuilder.build(this, notificationIntent, headlines));

        }
        if (news == null) {
            //todo
        }
    }

}