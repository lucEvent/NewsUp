package com.lucevent.newsup.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.DownloadSchedule;

import java.util.Calendar;

public class ScheduledDownloadService extends Service {

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try {
            doWork(getApplicationContext(), intent);

        } catch (Exception e) {
            AppSettings.printerror("[SDS] Error executing service");
            e.printStackTrace();
        }
        stopSelf();
        return Service.START_NOT_STICKY;
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
        AppSettings.initialize(context);
        ScheduledDownloadReceiver.scheduleDownloads(context, dataManager.getDownloadSchedules());

        boolean jobdone = dataManager.download_News_for_service(job.sites_codes);

        if (job.notify) {
            //todo notification not working properly in Lollipop
            int[] intentextra = new int[job.sites_codes.length];
            StringBuilder notiftext = new StringBuilder();

            for (int i = 0; i < job.sites_codes.length; i++) {
                Site site = AppData.getSiteByCode(job.sites_codes[i]);

                intentextra[i] = site.prior_news.id;

                if (i != 0) {
                    notiftext.append("\n");
                }
                notiftext.append(site.name.toUpperCase()).append(": ").append(site.prior_news.title);
                AppSettings.printlog("[SDS] Im packing up the ids: " + site.prior_news.id);
            }

            // build notification
            Intent notificationIntent = new Intent(this, Main.class);
            notificationIntent.putExtra(AppCode.SEND_NEWS_IDS, intentextra);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(Main.class);
            stackBuilder.addNextIntent(notificationIntent);

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("News Up")
                    .setAutoCancel(true)
                    .setLights(Color.GREEN, 1000, 2000)
                    .setVibrate(new long[]{500})
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(notiftext.toString()))
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(resultPendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify((int) System.currentTimeMillis(), notification);

        }
        if (!jobdone) {
            //todo
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}