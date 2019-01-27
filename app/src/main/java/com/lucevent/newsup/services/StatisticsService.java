package com.lucevent.newsup.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.kernel.stats.SiteStat;
import com.lucevent.newsup.kernel.stats.SiteStats;
import com.lucevent.newsup.kernel.stats.Statistics;
import com.lucevent.newsup.net.BackendNames;
import com.lucevent.newsup.view.fragment.StatisticsFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class StatisticsService extends Service implements BackendNames {

	public static final int REQ_GET = 0;
	public static final int REQ_RESET = 1;
	public static final int REQ_SEND = 2;
	public static final int REQ_EVENT = 3;

	private final IBinder binder = new Binder();

	/**
	 * Class used for the client Binder.  Because we know this service always
	 * runs in the same process as its clients, we don't need to deal with IPC.
	 */
	public class Binder extends android.os.Binder {
		public StatisticsService getService()
		{
			return StatisticsService.this;
		}
	}

	private ConnectivityManager connectivityManager;

	@Override
	public void onCreate()
	{
		super.onCreate();
		connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		switch (intent.getExtras().getInt(AppCode.REQUEST_CODE)) {
			case REQ_GET:
				getStatistics(null, StatisticsFragment.SortOrder.SORT_BY_NAME);
			case REQ_RESET:
				resetStatistics(null, "");
				break;
			case REQ_SEND:
				sendUpdate();
				break;
			case REQ_EVENT:
				sendEvent(intent.getExtras().getInt(AppCode.EVENT_CODE));
				break;
		}
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return binder;
	}

	public boolean isInternetAvailable()
	{
		NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
		return ni != null && ni.isConnected() && ni.isAvailable();
	}

	public void getStatistics(final Handler handler, final StatisticsFragment.SortOrder order)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				String url = DEVELOPER_APP_SERVER + "?stats&options=";
				switch (order) {
					case SORT_BY_NAME:
						url += "s";
						break;
					case SORT_BY_TOTAL_REQUESTS:
						url += "n";
						break;
					case SORT_BY_MONTH_REQUESTS:
						url += "m";
						break;
					case SORT_BY_READINGS:
						url += "r";
						break;
					case SORT_BY_TIME:
						url += "t";
				}

				try {
					JSONObject json = new JSONObject(fetch(url));
					json = json.getJSONObject("stats");

					long since = json.getLong("since");
					long lastStart = json.getLong("last");
					JSONArray jsonSiteStats = json.getJSONArray("sites");
					SiteStats siteStats = new SiteStats();

					for (int i = 0; i < jsonSiteStats.length(); i++) {
						JSONObject jsonStat = (JSONObject) jsonSiteStats.get(i);
						SiteStat stat = new SiteStat();

						stat.siteName = jsonStat.getString("n");
						stat.siteCode = jsonStat.getInt("c");
						stat.totalRequests = jsonStat.getInt("a");
						stat.monthRequests = jsonStat.getInt("m");
						stat.readings = jsonStat.getInt("r");
						stat.lastRequest = jsonStat.getLong("l");
						stat.version = jsonStat.getString("v");

						siteStats.add(stat);
					}

					Statistics statistics = new Statistics(since, lastStart, siteStats);
					handler.obtainMessage(AppCode.STATISTICS, statistics).sendToTarget();

				} catch (Exception e) {
					AppSettings.printerror("[SS] Problem fetching Statistics:" + url, e);
				}
			}
		}).start();
	}

	public void resetStatistics(final Handler handler, final String resetPassword)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					JSONObject json = new JSONObject(fetch(DEVELOPER_APP_SERVER + "?stats&reset&pass=" + resetPassword));
					json = json.getJSONObject("stats");

					long since = json.getLong("since");
					long lastStart = json.getLong("last");


					Statistics statistics = new Statistics(since, lastStart, new SiteStats());
					handler.obtainMessage(AppCode.STATISTICS, statistics).sendToTarget();
				} catch (Exception e) {
					AppSettings.printerror("[SR] Exception reseting Statistics", e);
				}
			}
		}).start();
	}

	private String fetch(String url) throws Exception
	{
		URL oracle = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		char[] buffer = new char[4096];
		StringBuilder sb = new StringBuilder(1000);
		int len;
		while ((len = in.read(buffer, 0, buffer.length)) > 0)
			sb.append(buffer, 0, len);
		in.close();
		return sb.toString();
	}

	public void sendUpdate()
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Thread.sleep(60000);    // sendUpdate after 1 minute

					KernelManager manager = new KernelManager(StatisticsService.this);
					ArrayList<Pair<Integer, Integer>> readingStats = manager.getTempReadingStats();

					if (!readingStats.isEmpty()) {
						StringBuilder data = new StringBuilder();
						for (Pair<Integer, Integer> pair : readingStats)
							if (pair.second > 0)
								data.append(pair.first).append(",")
										.append(pair.second).append(",");

						if (data.length() > 0) {

							new URL(MAIN_APP_SERVER + "?notify&values=" + data.substring(0, data.length() - 1))
									.openStream()
									.close();

							manager.clearReadingStats();
						}
					}
				} catch (Exception ignored) {
				}
			}
		}).start();
	}

	private void sendEvent(final int event_code)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				String url = MAIN_APP_SERVER + "?notifyevent=" + event_code + "&v=" + getString(R.string.app_version);
				try {
					URL request = new URL(url);
					request.openStream().close();
				} catch (Exception ignored) {
				}
			}
		}).start();
	}

}


