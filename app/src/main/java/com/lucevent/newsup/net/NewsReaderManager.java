package com.lucevent.newsup.net;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Source;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.io.StorageCallback;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.services.util.DownloadData;
import com.lucevent.newsup.services.util.DownloadNotification;

import java.util.ArrayList;

public class NewsReaderManager {

	private class NewsPetition {

		public Site site;
		public int[] sections;

	}

	private class EventNewsPetition extends NewsPetition {
		public Event event;
	}

	private static ConnectivityManager connectivityManager;

	private NewsReader newsReader;
	private AlertReader alertReader;
	private Handler uiCallback;
	private StorageCallback mDB;

	/* Threads */
	private static final int NUM_SLAVE_THREADS = NewsReader.NUM_SERVERS;
	private final Thread masterThread;
	private final Thread slaveThreads[];
	private static boolean checkAlerts = true;

	private final ArrayList<NewsPetition> petitionQueue;
	private final NewsSet newsQueue;

	public NewsReaderManager(@NonNull Context context, @Nullable StorageCallback storageCallback)
	{
		mDB = storageCallback;

		String appVersion = context.getString(R.string.app_version);
		newsReader = new NewsReader(appVersion);
		alertReader = new AlertReader(appVersion);
		connectivityManager = new ConnectivityManager(context);

		petitionQueue = new ArrayList<>();
		newsQueue = new NewsSet();
		masterThread = new Thread(masterRunnable);
		masterThread.start();
		slaveThreads = new Thread[NUM_SLAVE_THREADS];
		for (int i = 0; i < NUM_SLAVE_THREADS; i++) {
			slaveThreads[i] = new Thread(slaveRunnable);
			slaveThreads[i].start();
		}
	}

	public void read(@NonNull Site site, @Nullable int[] sections, Handler handler)
	{
		uiCallback = handler;
		addPetition(site, sections, true);
	}

	public void read(@NonNull Sites sites, Handler handler)
	{
		uiCallback = handler;
		for (Site site : sites)
			addPetition(site, null, false);
	}

	public void read(Event event, Handler handler)
	{
		uiCallback = handler;
		EventNewsPetition petition = new EventNewsPetition();
		petition.site = null;
		petition.sections = null;
		petition.event = event;
		synchronized (petitionQueue) {
			petitionQueue.add(petition);
			petitionQueue.notify();
		}
	}

	public DownloadNotification read(Download download)
	{
		if (!connectivityManager.isInternetAvailable())
			return null;

		DownloadNotification notification = new DownloadNotification();
		notification.time = System.currentTimeMillis();
		int server = 0;
		ArrayList<Integer> news_ids = new ArrayList<>();
		for (int sCode : download.sites_codes) {
			try {
				Site site = AppData.getSiteByCode(sCode);
				int[] section_codes = sectionIndexesToCodes(site, AppSettings.getDownloadSections(site));

				NewsArray news = readHeaders(site, section_codes);
				if (news != null && !news.isEmpty()) {
					for (News n : news) {
						if (!mDB.hasNews(n.id)) {
							if (n.content == null || n.content.isEmpty())
								readContent(server++ % NewsReader.NUM_SERVERS, site, n);

							if (!n.content.isEmpty())
								mDB.save(n);
						}
						news_ids.add(n.id);
					}
					notification.add(site.code, news.get(0));
				}
			} catch (Exception e) {
				AppSettings.printerror("[NRM] Error on read(download) :(", e);
			}
		}

		if (!news_ids.isEmpty()) {
			int[] i_news_ids = new int[news_ids.size()];
			for (int i = 0; i < i_news_ids.length; i++)
				i_news_ids[i] = news_ids.get(i);

			DownloadData data = new DownloadData(
					notification.time,
					download.sites_codes,
					i_news_ids
			);
			mDB.save(data);
		}
		return notification;
	}

	public DownloadNotification read(int eventCode, Download download)
	{
		if (!connectivityManager.isInternetAvailable())
			return null;

		DownloadNotification notification = new DownloadNotification();
		notification.time = System.currentTimeMillis();
		int server = 0;
		ArrayList<Integer> news_ids = new ArrayList<>();
		try {
			NewsArray news = newsReader.readEventHeaders(eventCode);
			if (news != null) {
				for (News n : news) {
					if (!mDB.hasNews(n.id)) {
						if (n.content == null || n.content.isEmpty())
							readContent(server++ % NewsReader.NUM_SERVERS, AppData.getSiteByCode(n.site_code), n);

						if (!n.content.isEmpty())
							mDB.save(n);
					}

					news_ids.add(n.id);
					if (!notification.hasHeadline(n.site_code))
						notification.add(n.site_code, n);
				}
			}
		} catch (Exception ignored) {
		}

		if (!news_ids.isEmpty()) {
			int[] i_news_ids = new int[news_ids.size()];
			for (int i = 0; i < i_news_ids.length; i++)
				i_news_ids[i] = news_ids.get(i);

			DownloadData data = new DownloadData(
					notification.time,
					download.sites_codes,
					i_news_ids
			);
			mDB.save(data);
		}
		return notification;
	}

	public void cancelAll()
	{
		uiCallback = null;
		masterThread.interrupt();
		synchronized (petitionQueue) {
			petitionQueue.clear();
		}
		synchronized (newsQueue) {
			newsQueue.clear();
		}
	}

	private void addPetition(Site site, int[] sections, boolean urgent)
	{
		NewsPetition petition = new NewsPetition();
		petition.site = site;
		petition.sections = sections == null ? AppSettings.getMainSections(site) : sections;
		synchronized (petitionQueue) {
			if (urgent)
				petitionQueue.add(0, petition);
			else
				petitionQueue.add(petition);
			petitionQueue.notify();
		}
	}

	private void addEventPetition(Event event, Site site, int[] sections)
	{
		EventNewsPetition petition = new EventNewsPetition();
		petition.site = site;
		petition.sections = sections;
		petition.event = event;
		synchronized (petitionQueue) {
			petitionQueue.add(petition);
			petitionQueue.notify();
		}
	}

	private Runnable masterRunnable = new Runnable() {
		@Override
		public void run()
		{
			while (true) {
				if (checkAlerts && uiCallback != null) {
					checkAlerts = false;

					Alert alert = alertReader.fetch();
					if (uiCallback != null && alert != null)
						uiCallback.obtainMessage(AppCode.ALERT, alert).sendToTarget();
				}

				NewsPetition petition = null;
				synchronized (petitionQueue) {
					if (!petitionQueue.isEmpty())
						petition = petitionQueue.remove(0);
				}
				if (petition == null) {
					if (uiCallback != null) {
						// Notify UI that news are loaded
						uiCallback.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();

						// Delete news already in db whose date<timeBound
						long timeBound = System.currentTimeMillis() - AppSettings.getKeepTime() * 1000;
						mDB.deleteOldNews(timeBound);
					}
					try {
						synchronized (petitionQueue) {
							petitionQueue.wait();
						}
					} catch (InterruptedException ignore) {
					}
					continue;
				}

				if (connectivityManager.isInternetAvailable()) {

					NewsArray news = petition instanceof EventNewsPetition ?
							readEventHeaders(((EventNewsPetition) petition).event, petition.site, petition.sections) :
							readHeaders(petition.site, sectionIndexesToCodes(petition.site, petition.sections));

					if (news != null) {

						// Send news to Kernel
						if (uiCallback != null)
							uiCallback.obtainMessage(AppCode.NEWS_COLLECTION, news).sendToTarget();

						if (petition.site instanceof UserSite)
							continue;

						// Add news to newsQueue
						synchronized (newsQueue) {
							newsQueue.addAll(news);
							newsQueue.notifyAll();
						}
					}

				} else
					onNoConnectionAvailable(petition);

			}
		}
	};

	private Runnable slaveRunnable = new Runnable() {
		@Override
		public void run()
		{
			while (true) {
				News news = null;
				synchronized (newsQueue) {
					if (!newsQueue.isEmpty()) {
						news = newsQueue.pollFirst();
					}
				}
				if (news == null) {
					try {
						synchronized (newsQueue) {
							newsQueue.wait();
						}
					} catch (InterruptedException ignore) {
					}
					continue;
				}

				Site site = AppData.getSiteByCode(news.site_code);

				if (!mDB.hasNews(news.id)) {

					if (news.content.isEmpty())
						readContent((int) (Thread.currentThread().getId()) % NewsReader.NUM_SERVERS, site, news);

					if (!news.content.isEmpty())
						mDB.save(news);
				}
			}
		}
	};

	private void onNoConnectionAvailable(NewsPetition petition)
	{
		if (uiCallback == null)
			return;

		uiCallback.obtainMessage(AppCode.NO_INTERNET, null).sendToTarget();
		if (petition.site != null) {

			int[] section_codes = sectionIndexesToCodes(petition.site, petition.sections);

			NewsMap history = mDB.getNewsOf(petition.site, section_codes);

			if (petition instanceof EventNewsPetition) {
				NewsMap eventHistory = new NewsMap();
				for (News n : history.values())
					if (n.hasKeyWords(((EventNewsPetition) petition).event.keyWords))
						eventHistory.add(n);

				history = eventHistory;
			}

			if (uiCallback != null)
				uiCallback.obtainMessage(AppCode.NEWS_COLLECTION, history.values()).sendToTarget();

		} else {

			for (int siteCode : AppSettings.getMainSitesCodes()) {
				Site site = AppData.getSiteByCode(siteCode);
				int[] section_codes = sectionIndexesToCodes(petition.site, AppSettings.getMainSections(site));

				NewsMap history = mDB.getNewsOf(site, section_codes);
				if (uiCallback != null)
					uiCallback.obtainMessage(AppCode.NEWS_COLLECTION, history.values()).sendToTarget();
			}
		}

		if (uiCallback != null)
			uiCallback.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
	}

	private NewsArray readHeaders(Site site, int[] section_codes)
	{
		NewsArray news = newsReader.readNewsHeaders(site.code, section_codes);
		if (news == null) {
			try {
				news = site.readNewsHeaders(section_codes);
			} catch (Exception e) {
				AppSettings.printerror("Error reading header of " + site.name + ", " + section_codes.length + " sections", e);
			}
		}
		return news;
	}

	private NewsArray readEventHeaders(Event event, Site site, int[] section_codes)
	{
		NewsArray news = null;
		if (site == null) {

			news = newsReader.readEventHeaders(event.code);

			if (news == null)
				for (Source source : event.sources)
					addEventPetition(event, source.site, source.sections);

		} else {

			try {

				news = event.filter(
						site.readNewsHeaders(section_codes)
				);

			} catch (Exception e) {
				AppSettings.printerror("Error reading header of " + site.name, e);
			}
		}

		return news;
	}

	private void readContent(int server, Site site, News news)
	{
		newsReader.readNewsContent(server, news);

		if (news.content.isEmpty()) {
			try {
				String content = site.readNewsContent(news.link);

				if (content != null)
					news.content = content;
			} catch (Exception e) {
				AppSettings.printerror("Error reading content (in device) of " + news.link, e);
			}
		}

		if (!news.content.isEmpty() && news.imgSrc == null)
			news.forceImage();
	}

	public void readNow(Site site, News news)
	{
		String content = site.readNewsContent(news.link);
		if (content != null)
			news.content = content;

		if (!news.content.isEmpty()) {
			if (news.imgSrc == null)
				news.forceImage();

			mDB.save(news);
		}
	}

	private int[] sectionIndexesToCodes(Site site, int[] indexes)
	{
		if (indexes == null)
			return new int[]{site.getSections().getDefault().code};

		int[] codes = new int[indexes.length];

		Sections sections = site.getSections();
		for (int i = 0; i < indexes.length; i++)
			if (indexes[i] < sections.size())
				codes[i] = sections.get(indexes[i]).code;
			else
				codes[i] = -1;

		return codes;
	}

}