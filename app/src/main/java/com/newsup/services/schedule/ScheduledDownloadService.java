package com.newsup.services.schedule;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.newsup.kernel.NewsDataCenter;
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
            int day = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY) % 7;

            for (int i = 0; i < AppSettings.DL_SCHEDULES.size(); ++i) {
                DownloadScheduleSetting cand = AppSettings.DL_SCHEDULES.get(i);
                if (cand.toString().equals(dataCompacted)) {
                    cand.days[day] = false;
                    dataManager.updateSetting(AppSettings.MOD_DL_SCHEDULE, i, cand);
                    break;
                }
            }
        }
        else {
            ScheduledDownloadReceiver.scheduleDownload(context, AppSettings.DL_SCHEDULES);
        }

        boolean jobdone = dataManager.download_News_for_service(job.dl_sites_codes);

        if (job.notify) {
            //todo
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