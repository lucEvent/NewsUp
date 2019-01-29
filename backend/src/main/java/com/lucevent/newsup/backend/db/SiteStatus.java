package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

public class SiteStatus extends DataStoreEntity {

	public static final long STATUS_WORKING = 0;
	public static final long STATUS_NOT_WORKING = 1;
	public static final long STATUS_UNDER_REVISION = 2;
	public static final long STATUS_REMOVED = 3;
	public static final long STATUS_ON_DEVELOPMENT = 4;
	public static final long STATUS_SCHEDULED = 5;

	private static final String KIND = "SiteStatus";

	// [START keys]
	private static final String CODE = "code";                                                          // long
	private static final String NAME = "name";                                                          // String
	private static final String INFO = "info";                                                          // long
	private static final String STATUS = "status";                                                      // long
	private static final String LAST_CHECK_TIME = "last_check_time";                                    // long
	private static final String LAST_CHECK_DURATION = "last_check_duration";                            // long
	private static final String LAST_CHECK_ROUNDS = "last_check_rounds";                                // long
	private static final String CURRENT_NUM_NEWS = "current_num_news";                                  // long
	private static final String CURRENT_NUM_NEWS_WITHOUT_CONTENT = "current_num_news_without_content";  // long
	// [END keys]

	private SiteStatus(Entity e)
	{
		super(e);
	}

	public static SiteStatus makeInstance(long code, String name, long info)
	{
		Entity e = new Entity(KIND);
		e.setIndexedProperty(CODE, code);
		e.setUnindexedProperty(NAME, name);
		e.setUnindexedProperty(INFO, info);
		e.setUnindexedProperty(STATUS, STATUS_SCHEDULED);
		e.setUnindexedProperty(LAST_CHECK_TIME, 0L);
		e.setUnindexedProperty(LAST_CHECK_DURATION, 0L);
		e.setUnindexedProperty(LAST_CHECK_ROUNDS, 0L);
		e.setUnindexedProperty(CURRENT_NUM_NEWS, 0L);
		e.setUnindexedProperty(CURRENT_NUM_NEWS_WITHOUT_CONTENT, 0L);
		return new SiteStatus(e);
	}

	public static SiteStatus get(long code)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(CODE, Query.FilterOperator.EQUAL, code)
		);

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		return e == null ? null : new SiteStatus(e);
	}

	public static List<SiteStatus> getAll()
	{
		ArrayList<SiteStatus> res = new ArrayList<>();

		PreparedQuery pq = DATASTORE.prepare(new Query(KIND));
		for (Entity e : pq.asQueryResultIterable())
			res.add(new SiteStatus(e));

		return res;
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public void setStatus(long i)
	{
		mEntity.setUnindexedProperty(STATUS, i);
	}

	public void setLastCheckTime(long l)
	{
		mEntity.setUnindexedProperty(LAST_CHECK_TIME, l);
	}

	public void setLastCheckDuration(long l)
	{
		mEntity.setUnindexedProperty(LAST_CHECK_DURATION, l);
	}

	public void setLastCheckRounds(long i)
	{
		mEntity.setUnindexedProperty(LAST_CHECK_ROUNDS, i);
	}

	public void setCurrentNumNews(long i)
	{
		mEntity.setUnindexedProperty(CURRENT_NUM_NEWS, i);
	}

	public void setCurrentNumNewsWithoutContent(long i)
	{
		mEntity.setUnindexedProperty(CURRENT_NUM_NEWS_WITHOUT_CONTENT, i);
	}

	public long getStatus()
	{
		return (long) mEntity.getProperty(STATUS);
	}

	public long getLastCheckTime()
	{
		return (long) mEntity.getProperty(LAST_CHECK_TIME);
	}

	public long getLastCheckDuration()
	{
		return (long) mEntity.getProperty(LAST_CHECK_DURATION);
	}

	public long getLastCheckRounds()
	{
		return (long) mEntity.getProperty(LAST_CHECK_ROUNDS);
	}

	public long getCurrentNumNews()
	{
		return (long) mEntity.getProperty(CURRENT_NUM_NEWS);
	}

	public long getCurrentNumNewsWithoutContent()
	{
		return (long) mEntity.getProperty(CURRENT_NUM_NEWS_WITHOUT_CONTENT);
	}

}
