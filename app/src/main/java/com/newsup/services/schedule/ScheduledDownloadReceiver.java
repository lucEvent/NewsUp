package com.newsup.services.schedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.newsup.services.Schedule;
import com.newsup.settings.DownloadScheduleSetting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

public class ScheduledDownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String dataCompacted = intent.getExtras().getString(DownloadScheduleSetting.DOWNLOAD_SCHEDULE);

        Intent service = new Intent(context, ScheduledDownloadService.class);
        service.putExtra(DownloadScheduleSetting.DOWNLOAD_SCHEDULE, dataCompacted);

        context.startService(service);

    }

    public static void scheduleDownload(Context context, ArrayList<DownloadScheduleSetting> schedules) {
        System.out.println("Scheduling");
        if (schedules.isEmpty()) {
            Intent intent = new Intent(context, ScheduledDownloadReceiver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmMgr.cancel(alarmIntent);
            return;
        }

        TreeSet<Schedule> list = new TreeSet<Schedule>();

        for (DownloadScheduleSetting schedule : schedules) {
            Schedule item = new Schedule();
            item.object = schedule;

            Calendar calendar = Calendar.getInstance();

            int c_hour = calendar.get(Calendar.HOUR_OF_DAY);
            int c_minute = calendar.get(Calendar.MINUTE);
            boolean add_day = !(c_hour < schedule.hour || (c_hour == schedule.hour && c_minute < schedule.minute));
            System.out.println(" boolean add_day = !(" + c_hour + " < " + schedule.hour + " || (" + c_hour + " == " + schedule.hour + " && " + c_minute + " < " + schedule.minute + "));");
            int c_day = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
            if (add_day) c_day++;

            innerloop:
            for (int i = 0; i < schedule.days.length; ++i) {

                if (schedule.days[c_day % 7]) {

                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, schedule.hour);
                    calendar.set(Calendar.MINUTE, schedule.minute);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.add(Calendar.DAY_OF_MONTH, add_day ? 1 + i : i);

                    item.time = calendar.getTimeInMillis();
                    list.add(item);
                    break innerloop;
                }
                c_day++;
            }
        }

        DownloadScheduleSetting next_schedule = (DownloadScheduleSetting) list.first().object;

        Intent intent = new Intent(context, ScheduledDownloadReceiver.class);
        intent.putExtra(DownloadScheduleSetting.DOWNLOAD_SCHEDULE, next_schedule.toString());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, list.first().time, alarmIntent);

        System.out.println("Time:" + list.first().time);
    }

    private void debug(String text) {
        android.util.Log.d("##NewsDataCenter##", text);
    }

}
