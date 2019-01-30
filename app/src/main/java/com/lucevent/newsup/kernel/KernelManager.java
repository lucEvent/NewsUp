package com.lucevent.newsup.kernel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.io.DBManager;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.io.SDManager;
import com.lucevent.newsup.io.StorageCallback;
import com.lucevent.newsup.net.NewsReaderManager;
import com.lucevent.newsup.services.util.DownloadData;

import java.util.ArrayList;
import java.util.Locale;

public class KernelManager implements StorageCallback {

	private Context context;

	protected static DBManager dbmanager;
	protected static SDManager sdmanager;
	private static LogoManager logoManager;
	private static NewsReaderManager readerManager;

	public KernelManager(@NonNull Context context)
	{
		this.context = context;
		AppSettings.initialize(context);

		Date.setTitles(context.getResources().getStringArray(R.array.date_messages));

		if (dbmanager == null)
			dbmanager = new DBManager(context);

		if (sdmanager == null)
			sdmanager = new SDManager(context);

		if (AppData.getSites() == null) {
			Sites sites = Sites.getDefault(ProSettings.areFinnishPublicationsEnabled()
					|| Locale.getDefault().getLanguage().equals("fi"));
			sites.addAll(dbmanager.readUserSites());

			AppData.setSites(sites, dbmanager);
			dbmanager.readReadings(AppData.getSites());
		}

		if (logoManager == null)
			logoManager = LogoManager.getInstance(context, AppData.getSites().size());

		if (readerManager == null)
			readerManager = new NewsReaderManager(context, this);
	}

	public static void readContentOf(News news)
	{
		if (news.content == null || news.content.isEmpty())
			sdmanager.readNewsContent(news);
	}

	public static void fetchContentOf(News news)
	{
		if (news.content.isEmpty())
			readerManager.readNow(AppData.getSiteByCode(news.site_code), news);
	}

	public static void setNewsRead(News news)
	{
		dbmanager.setNewsRead(news);
	}

	public static long getCacheSize()
	{
		return sdmanager.getDBSize()
				+ sdmanager.getSize()
				+ sdmanager.getGlideCacheSize();
	}

	public static void cleanCache()
	{
		sdmanager.wipeData();
		dbmanager.wipeData();
	}

	public static void cleanGlideCache()
	{
		sdmanager.wipeGlideData();
	}

	public Sites getFavoriteSites()
	{
		int[] favorite_codes = AppSettings.getFavoriteSitesCodes();
		Sites res = new Sites(favorite_codes.length);
		for (int code : favorite_codes) {
			Site s = AppData.getSiteByCode(code);
			if (s == null)
				AppSettings.toggleFavorite(Site.getDummy(code), false);
			else res.add(s);
		}
		return res;
	}

	public void addSite(UserSite site)
	{
		if (dbmanager.insertUserSite(site)) {
			AppData.getSites().add(site);

			if (site.icon != null)
				logoManager.downloadLogo(site);
		}
	}

	public News getNewsById(int id)
	{
		return dbmanager.readNews(id);
	}

	public NewsReaderManager getReaderManager()
	{
		return readerManager;
	}

	public DBManager getDatabaseManager()
	{
		return dbmanager;
	}

	@Override
	public void save(News news)
	{
		try {
			dbmanager.insertNews(news);
			sdmanager.saveNewsContent(news);
		} catch (Exception e) {
			AppSettings.printerror("Error on KM.saveNews", e);
		}
	}

	@Override
	public void save(DownloadData downloadData)
	{
		try {
			dbmanager.insert(downloadData);
		} catch (Exception e) {
			AppSettings.printerror("Error on KM.save(D.D)", e);
		}
	}

	@Override
	public boolean hasNews(int news_id)
	{
		return dbmanager.hasNews(news_id);
	}

	@Override
	public NewsMap getNewsOf(Site site, int[] section_codes)
	{
		return dbmanager.readNews(site, section_codes);
	}

	@Override
	public void deleteOldNews(long timeBound)
	{
		AppSettings.printlog("Time bound: " + Date.getAge(timeBound));
		int[] delete_news_ids = dbmanager.deleteNewsSince(timeBound);
		for (int news_id : delete_news_ids)
			sdmanager.deleteNews(news_id);
	}

	public ArrayList<Pair<Integer, Integer>> getTempReadingStats()
	{
		return dbmanager.readSyncReadings();
	}

	public void clearReadingStats()
	{
		dbmanager.clearSyncReadings();
	}

}
