package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;

public class Event extends DataStoreEntity {

	private static final String KIND = "Event";

	// [START keys]
	private static final String CODE = "code";                      // long
	private static final String LAST_NEWS_TIME = "last_news_time";  // long
	private static final String REGION_CODE = "region_code";        // String
	private static final String TITLE = "title";                    // String
	private static final String IMG_SRC = "img_src";                // String
	private static final String TAGS = "tags";                      // ArrayList<String>[]
	// [END keys]

	public Event()
	{
		super(new Entity(KIND));
	}

	private Event(Entity entity)
	{
		super(entity);
	}

	public static Event get(long code)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(CODE, Query.FilterOperator.EQUAL, code)
		);

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		return (e != null) ? new Event(e) : null;
	}

	public static ArrayList<Event> getAll(long lastNewsTime)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(LAST_NEWS_TIME, Query.FilterOperator.GREATER_THAN_OR_EQUAL, lastNewsTime)
		);

		ArrayList<Event> res = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(q).asQueryResultIterable();
		for (Entity e : it)
			res.add(new Event(e));

		return res;
	}

	public static boolean delete(long code)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(CODE, Query.FilterOperator.EQUAL, code)
		).setKeysOnly();

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {
			DATASTORE.delete(e.getKey());
			return true;
		}
		return false;
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public long getCode()
	{
		return (long) mEntity.getProperty(CODE);
	}

	public long getLastNewsTime()
	{
		return (long) mEntity.getProperty(LAST_NEWS_TIME);
	}

	public String getRegionCode()
	{
		return (String) mEntity.getProperty(REGION_CODE);
	}

	public String getTitle()
	{
		return (String) mEntity.getProperty(TITLE);
	}

	public String getImgSrc()
	{
		return (String) mEntity.getProperty(IMG_SRC);
	}

	public ArrayList<String> getTags()
	{
		return (ArrayList<String>) mEntity.getProperty(TAGS);
	}

	public void setCode(long i)
	{
		mEntity.setIndexedProperty(CODE, i);
	}

	public void setLastNewsTime(long l)
	{
		mEntity.setIndexedProperty(LAST_NEWS_TIME, l);
	}

	public void setRegionCode(String s)
	{
		mEntity.setUnindexedProperty(REGION_CODE, s);
	}

	public void setTitle(String s)
	{
		mEntity.setUnindexedProperty(TITLE, s);
	}

	public void setImgSrc(String s)
	{
		mEntity.setUnindexedProperty(IMG_SRC, s);
	}

	public void setTags(ArrayList<String> a)
	{
		mEntity.setUnindexedProperty(TAGS, a);
	}

}