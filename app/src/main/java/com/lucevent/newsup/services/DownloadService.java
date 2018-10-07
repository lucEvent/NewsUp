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
import com.lucevent.newsup.services.util.DownloadNotification;
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

			doWork(getApplicationContext(), intent.getExtras().getInt(AppCode.SCHEDULE_ID));

		} catch (Exception e) {
			AppSettings.printerror("[SDS] Error executing service", e);
		}
	}

	private void doWork(Context context, int schedule_id)
	{
		AppSettings.initialize(context);
		ScheduleManager dataManager = new ScheduleManager(context);

		Download task = dataManager.getSchedule(schedule_id);
		if (!task.repeat) {
			Calendar calendar = Calendar.getInstance();
			int day = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7;

			task.days[day] = false;

			boolean maintain = false;
			for (boolean b : task.days)
				if (b) {
					maintain = true;
					dataManager.updateSchedule(task);
					break;
				}

			if (!maintain)
				dataManager.deleteSchedule(task);

		}
		ScheduledDownloadReceiver.scheduleDownloads(context, dataManager.getSchedule());

		DownloadNotification notificationData;
		if (task.isEvent()) {
			notificationData = dataManager.getReaderManager().read(-task.id, task);
		} else
			notificationData = dataManager.getReaderManager().read(task);

		if (task.notify && notificationData != null && !notificationData.isEmpty()) {
			String title;
			String[] headlines = new String[notificationData.headlines.size()];
			if (notificationData.headlines.size() == 1) {
				Site s = AppData.getSiteByCode(notificationData.headlines.keyAt(0));
				title = (s == null ? "" : s.name) + " [via News Up]";
				headlines[0] = notificationData.headlines.valueAt(0);
			} else {
				title = "News Up";
				for (int i = 0; i < notificationData.headlines.size(); i++) {
					Site s = AppData.getSiteByCode(notificationData.headlines.keyAt(i));
					String siteName = s == null ? "" : s.name;
					headlines[i] = siteName + ": " + notificationData.headlines.valueAt(i);
				}
			}

			// build notification
			Intent notificationIntent = new Intent(this, Main.class);
			notificationIntent.putExtra(AppCode.NOTIFICATION, true);
			notificationIntent.putExtra(AppCode.TIME, notificationData.time);

			NotificationBuilder.notifyUser(this,
					NotificationBuilder.build(this, notificationIntent, title, headlines));

		}
		if (notificationData == null) {
			//todo
		}
	}

}