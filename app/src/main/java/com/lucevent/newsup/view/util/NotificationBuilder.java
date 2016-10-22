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

    public static Notification build(Context context, Intent intent, String[] headlines)
    {
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        for (String headline : headlines)
            style.addLine(headline);

        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("News Up")
                .setContentText(context.getString(R.string.expand_to_see_headlines))
                .setAutoCancel(true)
                .setColor(0xff8BC34A)
                .setLights(Color.GREEN, 1000, 2000)
                .setVibrate(new long[]{500})
                .setStyle(style)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(resultPendingIntent)
                .build();
    }

    public static void notifyUser(Context context, Notification notification)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), notification);
    }

}
