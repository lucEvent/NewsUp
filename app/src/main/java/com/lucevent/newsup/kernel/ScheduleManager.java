package com.lucevent.newsup.kernel;

import android.content.Context;
import android.content.res.Resources;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.Download;

import java.util.ArrayList;

public class ScheduleManager extends KernelManager {

	private static ArrayList<Download> mSchedule;

	public ScheduleManager(Context context)
	{
		super(context);
		if (Download.s_days == null) {
			String[] vector = new String[7];
			Resources res = context.getResources();
			vector[0] = res.getText(R.string.mon).toString();
			vector[1] = res.getText(R.string.tue).toString();
			vector[2] = res.getText(R.string.wed).toString();
			vector[3] = res.getText(R.string.thu).toString();
			vector[4] = res.getText(R.string.fri).toString();
			vector[5] = res.getText(R.string.sat).toString();
			vector[6] = res.getText(R.string.sun).toString();
			Download.s_days = vector;
		}
	}

	public ArrayList<Download> getSchedule()
	{
		if (mSchedule == null)
			mSchedule = dbmanager.readDownloadSchedules();
		return mSchedule;
	}

	public Download getSchedule(int id)
	{
		if (mSchedule == null)
			return dbmanager.readDownload(id);

		for (Download d : mSchedule)
			if (d.id == id)
				return d;

		return null;
	}

	public Download getSpecialSchedule(int id)
	{
		getSchedule();
		for (Download s : mSchedule)
			if (s.id == -id)
				return s;
		return null;
	}

	public void createSchedule(int hour, int minute, boolean notify, boolean repeat,
	                           boolean[] days, int[] sites_codes)
	{
		getSchedule();
		mSchedule.add(
				dbmanager.insertDownloadSchedule(hour, minute, notify, repeat, days, sites_codes)
		);
	}

	public void createSpecialSchedule(int id, int hour, int minute, boolean notify, boolean repeat,
	                                  boolean[] days, int[] sites_codes)
	{
		getSchedule();
		mSchedule.add(
				dbmanager.insertDownloadScheduleSpec(-id, hour, minute, notify, repeat, days, sites_codes)
		);
	}

	public void updateSchedule(Download schedule)
	{
		dbmanager.updateDownloadSchedule(schedule);
		mSchedule.clear();
		mSchedule.addAll(dbmanager.readDownloadSchedules());
	}

	public void deleteSchedule(Download schedule)
	{
		dbmanager.deleteDownloadSchedule(schedule);
		if (mSchedule != null)
			mSchedule.remove(schedule);
	}

}
