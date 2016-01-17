package com.newsup.services.schedule;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.IBinder;

import com.newsup.Main;
import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.SiteList;
import com.newsup.settings.AppSettings;
import com.newsup.settings.DownloadScheduleSetting;

import java.util.Calendar;

public class ScheduledDownloadService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            System.out.println("Starting service SDRv1");
            dojob(getApplicationContext(), intent);

        } catch (Exception e) {
            System.out.println("Error executing service!!!!!!!!!!");
            e.printStackTrace();
        }

        stopSelf();
        return Service.START_NOT_STICKY;
    }

    private void dojob(Context context, Intent intent) {
        String dataCompacted = intent.getExtras().getString(DownloadScheduleSetting.DOWNLOAD_SCHEDULE);
        DownloadScheduleSetting job = new DownloadScheduleSetting(dataCompacted);

        NewsDataCenter dataManager = new NewsDataCenter(context);

        if (!job.repeat) {
            Calendar calendar = Calendar.getInstance();
            int day = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7;

            for (int i = 0; i < AppSettings.DL_SCHEDULES.size(); ++i) {
                DownloadScheduleSetting cand = AppSettings.DL_SCHEDULES.get(i);
                if (cand.toString().equals(dataCompacted)) {
                    cand.days[day] = false;

                    boolean maintain = false;
                    for (boolean b : cand.days) {
                        if (b) {
                            System.out.println("Manteniendo");
                            maintain = true;
                            dataManager.updateSetting(AppSettings.MOD_DL_SCHEDULE, i, cand);
                        }
                    }
                    if (!maintain) {
                        System.out.println("Eliminando");
                        dataManager.setSetting(AppSettings.DEL_DL_SCHEDULE, i);
                    }
                    break;
                }
            }
        } else {
            ScheduledDownloadReceiver.scheduleDownload(context, AppSettings.DL_SCHEDULES);
        }

        boolean jobdone = dataManager.download_News_for_service(job.dl_sites_codes);

        if (job.notify) {
            //todo
            int[] intentextra = new int[job.dl_sites_codes.length];
            StringBuilder notiftext = new StringBuilder();

            SiteList sites = dataManager.getSites();
            for (int i = 0; i < job.dl_sites_codes.length; i++) {
                Site site = sites.getSiteByCode(job.dl_sites_codes[i]);

                intentextra[i] = site.highlighted.id;

                if (i != 0) {
                    notiftext.append(". ");
                }
                notiftext.append(site.name.toUpperCase()).append(": ").append(site.highlighted.title);
                System.out.println("Im packing up the ids: " + site.highlighted.id);
            }


// prepare intent which is triggered if the notification is selected
            Intent notificationIntent = new Intent(this, Main.class);
            notificationIntent.putExtra("notification_codes", intentextra);
// use System.currentTimeMillis() to have a unique ID for the pending intent
            PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), notificationIntent, 0);

// build notification
            Notification n = new Notification.Builder(this)
                    .setContentTitle("News Up")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setStyle(new Notification.BigTextStyle()
                            .bigText(notiftext.toString()))
                    .setContentIntent(pIntent)
                    .setAutoCancel(true)
                    .setLights(Color.GREEN, 1000, 2000)
                    .setVibrate(new long[]{500})
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify((int) System.currentTimeMillis(), n);
        }
        if (!jobdone) {
            //todo
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}