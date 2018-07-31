package com.lucevent.newsup.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.services.util.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

public class ScheduledDownloadReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent)
	{
		int schedule_id = intent.getExtras().getInt(AppCode.SCHEDULE_ID);

		Intent service = new Intent(context, DownloadService.class);
		service.putExtra(AppCode.SCHEDULE_ID, schedule_id);

		context.startService(service);
	}

	public static void scheduleDownloads(Context context, ArrayList<Download> schedules)
	{
		AppSettings.printlog("[SDR] Scheduling");

		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (schedules.isEmpty()) {
			Intent intent = new Intent(context, ScheduledDownloadReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			alarmMgr.cancel(pendingIntent);
			return;
		}

		Calendar calendar = Calendar.getInstance();
		TreeSet<Schedule> list = new TreeSet<>();
		for (Download schedule : schedules) {
			AppSettings.printlog("[SDR] candidate:" + schedule.toShortString());

			calendar.setTimeInMillis(System.currentTimeMillis());
			int c_hour = calendar.get(Calendar.HOUR_OF_DAY);
			int c_minute = calendar.get(Calendar.MINUTE);
			boolean add_day = !(c_hour < schedule.hour || (c_hour == schedule.hour && c_minute < schedule.minute));
			int c_day = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7;
			if (add_day) c_day++;

			for (int i = 0; i < schedule.days.length; ++i) {
				if (schedule.days[c_day % 7]) {
					calendar.setTimeInMillis(System.currentTimeMillis());
					calendar.set(Calendar.HOUR_OF_DAY, schedule.hour);
					calendar.set(Calendar.MINUTE, schedule.minute);
					calendar.set(Calendar.SECOND, 0);
					calendar.add(Calendar.DAY_OF_MONTH, add_day ? 1 + i : i);

					schedule.time = calendar.getTimeInMillis();
					list.add(schedule);
					break;
				}
				c_day++;
			}

			calendar.clear();
		}

		Download next_schedule = (Download) list.first();

		Intent intent = new Intent(context, ScheduledDownloadReceiver.class);
		intent.putExtra(AppCode.SCHEDULE_ID, next_schedule.id);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		alarmMgr.set(AlarmManager.RTC_WAKEUP, list.first().time, alarmIntent);

		AppSettings.printlog("[SDR] Next time scheduled:" + list.first().time);
	}

}
