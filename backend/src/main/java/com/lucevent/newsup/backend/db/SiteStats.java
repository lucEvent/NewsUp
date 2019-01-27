package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

public class SiteStats extends DataStoreEntity {

	private static final String KIND = "SiteStats";

	// [START keys]
	private static final String SITE_CODE = "site_code";            // long
	private static final String TOTAL_REQUESTS = "total_requests";  // long
	private static final String MONTH_REQUESTS = "month_requests";  // long
	private static final String READINGS = "readings";              // long
	private static final String LAST_REQUEST = "last_request";      // long
	private static final String SITE_NAME = "site_name";            // String
	private static final String LAST_IP = "last_ip";                // String
	private static final String FROM_VERSION = "from_version";      // String
	// [END keys]

	private SiteStats(Entity e)
	{
		super(e);
	}

	public static SiteStats get(long code, String name)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(SITE_CODE, Query.FilterOperator.EQUAL, code)
		);

		SiteStats res;
		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {

			res = new SiteStats(e);

		} else {

			e = new Entity(KIND);  // Key will be assigned once written
			e.setIndexedProperty(SITE_CODE, code);
			e.setUnindexedProperty(SITE_NAME, name);
			e.setUnindexedProperty(TOTAL_REQUESTS, 0L);
			e.setIndexedProperty(MONTH_REQUESTS, 0L);
			e.setUnindexedProperty(READINGS, 0L);
			e.setUnindexedProperty(LAST_REQUEST, 0L);
			e.setUnindexedProperty(LAST_IP, "");
			e.setUnindexedProperty(FROM_VERSION, "");

			res = new SiteStats(e);
			res.save();

		}
		return res;
	}

	@Deprecated
	public static void recover(long siteCode, String siteName, long totalRequests, long monthRequests,
	                           long readings, long lastRequest, String lastIp, String fromVersion)
	{
		Entity e = new Entity(KIND);
		e.setIndexedProperty(SITE_CODE, siteCode);
		e.setUnindexedProperty(SITE_NAME, siteName);
		e.setUnindexedProperty(TOTAL_REQUESTS, totalRequests);
		e.setIndexedProperty(MONTH_REQUESTS, monthRequests);
		e.setUnindexedProperty(READINGS, readings);
		e.setUnindexedProperty(LAST_REQUEST, lastRequest);
		e.setUnindexedProperty(LAST_IP, lastIp);
		e.setUnindexedProperty(FROM_VERSION, fromVersion);

		new SiteStats(e)
				.save();
	}

	public static ArrayList<SiteStats> getAll()
	{
		ArrayList<SiteStats> res = new ArrayList<>();

		Iterable<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterable();
		for (Entity e : it)
			res.add(new SiteStats(e));

		return res;
	}

	public static void newMonth()
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(MONTH_REQUESTS, Query.FilterOperator.GREATER_THAN, 0)
		);

		List<Entity> entities = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(q).asQueryResultIterable();
		for (Entity e : it) {
			e.setIndexedProperty(MONTH_REQUESTS, 0L);
			entities.add(e);
		}

		DATASTORE.put(entities);
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public SiteStats count(String ip, String version)
	{
		mEntity.setIndexedProperty(MONTH_REQUESTS, getMonthRequests() + 1);
		mEntity.setUnindexedProperty(TOTAL_REQUESTS, getTotalRequests() + 1);
		mEntity.setUnindexedProperty(LAST_REQUEST, System.currentTimeMillis());
		mEntity.setUnindexedProperty(LAST_IP, ip);
		mEntity.setUnindexedProperty(FROM_VERSION, version);
		return this;
	}

	public SiteStats countReadings(int readings)
	{
		mEntity.setUnindexedProperty(READINGS, getReadings() + readings);
		return this;
	}

	public long getSiteCode()
	{
		return (long) mEntity.getProperty(SITE_CODE);
	}

	public long getTotalRequests()
	{
		return (long) mEntity.getProperty(TOTAL_REQUESTS);
	}

	public long getMonthRequests()
	{
		return (long) mEntity.getProperty(MONTH_REQUESTS);
	}

	public long getReadings()
	{
		return (long) mEntity.getProperty(READINGS);
	}

	public long getLastRequest()
	{
		return (long) mEntity.getProperty(LAST_REQUEST);
	}

	public String getSiteName()
	{
		return (String) mEntity.getProperty(SITE_NAME);
	}

	public String getLastIp()
	{
		return (String) mEntity.getProperty(LAST_IP);
	}

	public String getFromVersion()
	{
		return (String) mEntity.getProperty(FROM_VERSION);
	}

}
