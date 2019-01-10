package com.lucevent.newsup.kernel;

import android.Manifest;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Events;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.DBManager;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.services.util.DownloadData;
import com.lucevent.newsup.view.util.NewsAdapterList;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public final class AppData {

	public interface OnNewsListChange {
		void onChange(NewsAdapterList currentNewsList);
	}

	public static final String[] STORAGE_PERMISSIONS = new String[]{
			Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
	private static final int DATA_REVISION_N = 6;
	private static Sites mSites;

	private static Events mEvents;

	private static OnNewsListChange mOnNewsListChange;
	private static NewsAdapterList mCurrentNewsList;

	public static Sites getSites()
	{
		return mSites;
	}

	public static void setSites(Sites sites, DBManager dbManager)
	{
		AppData.mSites = sites;

		int last_revision_n = AppSettings.getIntValue(AppSettings.LAST_DATA_REVISION_KEY, 0);
		if (last_revision_n < DATA_REVISION_N) {
			AppSettings.setValue(AppSettings.LAST_DATA_REVISION_KEY, DATA_REVISION_N);

			// Updating settings values
			for (Site s : sites) {
				Set<String> newMainSections = correctSections(s, AppSettings.getMainSectionsString(s));
				if (newMainSections != null)
					AppSettings.setMainSections(s, newMainSections);

				Set<String> newDownloadSections = correctSections(s, AppSettings.getDownloadSectionsString(s));
				if (newDownloadSections != null)
					AppSettings.setDownloadSections(s, newDownloadSections);
			}

			// Removing settings of sites no longer supported
			switch (last_revision_n) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
					removeSettingsOf(new int[]{330  /*Metro Sverige*/, 885  /*The Geek Hammer*/, 1710 /*The Berry*/, 1800/*Full Músculo*/}, dbManager);
				case 5:
					removeSettingsOf(new int[]{1300  /*Meristation*/}, dbManager);
			}
		}
	}

	public static Site getSiteByCode(int code)
	{
		return mSites.getSiteByCode(code);
	}

	public static Event getEvent(int code)
	{
		for (Event e : mEvents)
			if (e.code == code)
				return e;

		return null;
	}

	public static Sites getSites(int[] codes)
	{
		Sites res = new Sites();
		for (int code : codes) {
			Site s = mSites.getSiteByCode(code);
			if (s != null)
				res.add(s);
		}

		return res;
	}

	public static void setEvents(Events events)
	{
		AppData.mEvents = events;
	}

	private static Set<String> correctSections(Site site, Set<String> section_indexes)
	{
		if (section_indexes == null)
			return null;

		boolean corrected = false;
		String[] indexes_array = section_indexes.toArray(new String[section_indexes.size()]);
		Sections sections = site.getSections();

		for (int i = 0; i < indexes_array.length; i++) {
			int index = Integer.parseInt(indexes_array[i]);
			if (index > sections.size() - 1 || sections.get(index).url == null) {
				corrected = true;
				section_indexes.remove(indexes_array[i]);
			}
		}

		if (corrected) {
			if (section_indexes.isEmpty())
				section_indexes.add("0");

			return section_indexes;
		}
		return null;
	}

	private static void removeSettingsOf(int[] site_codes, DBManager dbManager)
	{
		Pair<Boolean, Set<String>> result = removeCodes(AppSettings.getMainSitesCodes(), site_codes);
		if (result.first)
			AppSettings.setMainSitesCodes(result.second);

		result = removeCodes(AppSettings.getFavoriteSitesCodes(), site_codes);
		if (result.first)
			AppSettings.setFavoriteSitesCodes(result.second);

		ArrayList<Download> downloads = dbManager.readDownloadSchedules();
		for (Download d : downloads) {
			if (!d.isEvent()) {
				if (d.sites_codes.length == 1) {
					for (int i = 0; i < site_codes.length; i++) {
						if (d.sites_codes[0] == site_codes[i]) {
							dbManager.deleteDownloadSchedule(d);
							break;
						}
					}
				} else {
					Pair<Boolean, int[]> result2 = removeCodes2(d.sites_codes, site_codes);
					if (result2.first) {
						d.sites_codes = result2.second;
						if (result2.second.length > 0)
							dbManager.updateDownloadSchedule(d);
						else
							dbManager.deleteDownloadSchedule(d);

					}
				}
			}
		}
		ArrayList<DownloadData> notifications = dbManager.readNotifications();
		for (DownloadData n : notifications)
			if (removeCodes2(n.site_codes, site_codes).first)
				dbManager.delete(n);

		dbManager.deleteDataOf(site_codes);
	}

	private static Pair<Boolean, Set<String>> removeCodes(int[] from, int[] codes)
	{
		boolean update = false;
		Set<String> new_code_set = new TreeSet<>();

		mainloop:
		for (int i = 0; i < from.length; i++) {
			for (int code : codes) {
				if (from[i] == code) {
					update = true;
					continue mainloop;
				}
			}
			new_code_set.add(Integer.toString(from[i]));
		}

		return new Pair<>(update, new_code_set);
	}

	private static Pair<Boolean, int[]> removeCodes2(int[] from, int[] codes)
	{
		int current = 0;
		int[] new_codes = new int[from.length];

		mainloop:
		for (int i = 0; i < from.length; i++) {
			for (int code : codes)
				if (from[i] == code)
					continue mainloop;

			new_codes[current++] = from[i];
		}

		if (current < new_codes.length) {
			int[] res = new int[current];
			System.arraycopy(new_codes, 0, res, 0, current);
			return new Pair<>(true, res);
		}
		return new Pair<>(false, null);
	}

	public static void setOnNewsListChange(OnNewsListChange l)
	{
		AppData.mOnNewsListChange = l;
	}

	public static NewsAdapterList getCurrentNewsList()
	{
		return mCurrentNewsList;
	}

	public static void notifyCurrentNewsListChanged(NewsAdapterList l)
	{
		mCurrentNewsList = l;
		if (mOnNewsListChange != null)
			mOnNewsListChange.onChange(mCurrentNewsList);
	}

	public static void setCurrentNewsList(NewsAdapterList l)
	{
		mCurrentNewsList = l;
	}

}
