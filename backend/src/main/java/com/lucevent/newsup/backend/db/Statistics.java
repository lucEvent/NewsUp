package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Statistics extends DataStoreEntity {

	private static final String KIND = "Statistics";

	// [START keys]
	private static final String SINCE = "since";            // long
	private static final String LAST_START = "last_start";  // long
	// [END keys]

	private TimeStats timeStats;

	private MonthStats monthStats;

	private Statistics(Entity e)
	{
		super(e);
	}

	public static Statistics getInstance()
	{
		Statistics res;
		Iterator<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterator();
		if (it.hasNext()) {

			res = new Statistics(it.next());
			res.mEntity.setProperty(LAST_START, System.currentTimeMillis());

		} else {

			res = new Statistics(new Entity(KIND));
			res.initialize();

		}
		res.save();

		res.timeStats = TimeStats.getInstance();
		res.monthStats = MonthStats.getInstance();

		return res;
	}

	@Deprecated
	public static void recover(long last_start, long since)
	{
		Entity e = new Entity(KIND);
		e.setProperty(SINCE, since);
		e.setProperty(LAST_START, last_start);

		new Statistics(e)
				.save();
	}

	public void count(Site site, String ip, String version)
	{
		count(site.code, site.name, ip, version);
	}

	public void countEvent(int event_code, String ip, String version)
	{
		count(event_code, "[E] " + Event.get(event_code).getTitle(), ip, version);
	}

	private void count(int code, String label, String ip, String version)
	{
		synchronized (this) {
			SiteStats.get(code, label)
					.count(ip, version)
					.save();
		}
		timeStats.count();
		monthStats.count();
	}

	public void countReadTimes(Site site, int times)
	{
		synchronized (this) {
			SiteStats.get(site.code, site.name)
					.countReadings(times)
					.save();
		}
	}

	public ArrayList<SiteStats> getSiteStats()
	{
		return SiteStats.getAll();
	}

	public void reset()
	{
		synchronized (this) {
			initialize();
			DATASTORE.put(mEntity);
			SiteStats.deleteAll();
		}
		timeStats.reset();
	}

	public void newMonth()
	{
		monthStats.newMonth();
		SiteStats.newMonth();
	}

	public TreeSet<MonthStats> getMonthStats()
	{
		return MonthStats.getAll();
	}

	private void initialize()
	{
		long time = System.currentTimeMillis();
		mEntity.setProperty(SINCE, time);
		mEntity.setProperty(LAST_START, time);
	}

	public TimeStats getDistribution()
	{
		return timeStats;
	}

	public long getSince()
	{
		return (long) mEntity.getProperty(SINCE);
	}

	public long getLastStart()
	{
		return (long) mEntity.getProperty(LAST_START);
	}

}
