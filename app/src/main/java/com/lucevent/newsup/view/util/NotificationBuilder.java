package com.lucevent.newsup.view.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.lucevent.newsup.R;

public class NotificationBuilder {

	/**
	 * @return Notification for download schedule
	 */
	public static Notification build(Context context, Intent intent, String title, String[] headlines)
	{
		PendingIntent resultPendingIntent =
				PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle(title)
				.setAutoCancel(true)
				.setColor(0xff8BC34A)
				.setLights(Color.GREEN, 1000, 2000)
				.setVibrate(new long[]{500})
				.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				.setContentIntent(resultPendingIntent);

		if (headlines.length == 1) {
			builder.setStyle(new NotificationCompat.BigTextStyle().bigText(headlines[0]));

		} else {
			NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
			for (String headline : headlines)
				style.addLine(headline);

			builder.setContentText(context.getString(R.string.expand_to_see_headlines))
					.setStyle(style);
		}

		return builder.build();
	}

	public static void notifyUser(Context context, Notification notification)
	{
		((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
				.notify((int) System.currentTimeMillis(), notification);
	}

}
