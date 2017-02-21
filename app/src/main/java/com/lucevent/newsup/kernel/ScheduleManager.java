package com.lucevent.newsup.kernel;

import android.content.Context;
import android.content.res.Resources;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.DownloadSchedule;

import java.util.ArrayList;

public class ScheduleManager extends KernelManager {

    private static ArrayList<DownloadSchedule> downloadSchedules;

    public ScheduleManager(Context context)
    {
        super(context);
        if (DownloadSchedule.s_days == null) {
            String[] vector = new String[7];
            Resources res = context.getResources();
            vector[0] = res.getText(R.string.mon).toString();
            vector[1] = res.getText(R.string.tue).toString();
            vector[2] = res.getText(R.string.wed).toString();
            vector[3] = res.getText(R.string.thu).toString();
            vector[4] = res.getText(R.string.fri).toString();
            vector[5] = res.getText(R.string.sat).toString();
            vector[6] = res.getText(R.string.sun).toString();
            DownloadSchedule.s_days = vector;
        }
    }

    public ArrayList<DownloadSchedule> getDownloadSchedules()
    {
        if (downloadSchedules == null)
            downloadSchedules = dbmanager.readDownloadSchedules();
        return downloadSchedules;
    }

    public DownloadSchedule createDownloadSchedule(int hour, int minute, boolean notify,
                                                   boolean repeat, boolean[] days, int[] sites_codes)
    {
        DownloadSchedule schedule = dbmanager.insertDownloadSchedule(hour, minute, notify, repeat, days, sites_codes);
        getDownloadSchedules().add(schedule);
        return schedule;
    }

    public void updateDownloadSchedule(DownloadSchedule schedule)
    {
        dbmanager.updateDownloadSchedule(schedule);
        downloadSchedules.clear();
        downloadSchedules.addAll(dbmanager.readDownloadSchedules());
    }

    public void deleteDownloadSchedule(DownloadSchedule schedule)
    {
        dbmanager.deleteDownloadSchedule(schedule);
        if (downloadSchedules != null)
            downloadSchedules.remove(schedule);
    }

}
