package com.lucevent.newsup.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.kernel.ScheduleManager;

public class onConcerningEventReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent)
	{
		AppSettings.initialize(context);
		ScheduledDownloadReceiver.scheduleDownloads(context,
				new ScheduleManager(context).getSchedule());
	}

}
