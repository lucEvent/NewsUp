package com.newsup.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.newsup.io.SDManager;
import com.newsup.services.schedule.ScheduledDownloadReceiver;
import com.newsup.settings.AppSettings;

public class onConcerningEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SDManager sdManager = new SDManager(context);
        sdManager.readSettings();

        ScheduledDownloadReceiver.scheduleDownload(context, AppSettings.DL_SCHEDULES);

    }

}
