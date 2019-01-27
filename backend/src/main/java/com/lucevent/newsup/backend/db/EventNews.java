package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;

import java.util.ArrayList;
import java.util.List;

public class EventNews extends DataStoreEntity implements Comparable<EventNews> {

	private static final String KIND = "EventNews";

	// [START keys]
	private static final String ID = "id";                      // long
	private static final String EVENT_CODE = "event_code";      // long
	private static final String SITE_CODE = "site_code";        // long
	private static final String LINK = "link";                  // String
	private static final String DATE = "date";                  // long
	private static final String SECTION_CODE = "section_code";  // long
	private static final String TAGS = "tags";                  // ArrayList<String>
	private static final String TITLE = "title";                // String
	private static final String DESCRIPTION = "description";    // Text
	private static final String CONTENT = "content";            // Text
	private static final String IMG_SRC = "imgSrc";              // String
	// [END keys]

	public EventNews(long id, long event_code, long site_code, String link, String title, String dscr,
	                 String content, String imgSrc, long date, long section_code, ArrayList<String> tags)
	{
		super(new Entity(KIND));
		mEntity.setUnindexedProperty(ID, id);
		mEntity.setIndexedProperty(EVENT_CODE, event_code);
		mEntity.setIndexedProperty(SITE_CODE, site_code);
		mEntity.setIndexedProperty(LINK, link);
		mEntity.setUnindexedProperty(TITLE, title);
		mEntity.setUnindexedProperty(DESCRIPTION, new Text(dscr));
		mEntity.setUnindexedProperty(CONTENT, new Text(content));
		mEntity.setUnindexedProperty(IMG_SRC, imgSrc);
		mEntity.setUnindexedProperty(DATE, date);
		mEntity.setUnindexedProperty(SECTION_CODE, section_code);
		mEntity.setUnindexedProperty(TAGS, tags);
		save();
	}

	private EventNews(Entity e)
	{
		super(e);
	}

	public static List<EventNews> get(long event_code)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(EVENT_CODE, Query.FilterOperator.EQUAL, event_code)
		);

		ArrayList<EventNews> res = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(q).asQueryResultIterable();
		for (Entity e : it)
			res.add(new EventNews(e));

		return res;
	}

	@Deprecated
	/**
	 * @deprecated Should call {@link #get(long event_code)}
	 */
	public static List<EventNews> get(long event_code, long site_code)
	{
		Query q = new Query(KIND).setFilter(
				Query.CompositeFilterOperator.and(
						new Query.FilterPredicate(EVENT_CODE, Query.FilterOperator.EQUAL, event_code),
						new Query.FilterPredicate(SITE_CODE, Query.FilterOperator.EQUAL, site_code)
				)
		);

		ArrayList<EventNews> res = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(q).asQueryResultIterable();
		for (Entity e : it)
			res.add(new EventNews(e));

		return res;
	}

	public static boolean contains(long event_code, long site_code, String link)
	{
		Query q = new Query(KIND).setFilter(
				Query.CompositeFilterOperator.and(
						new Query.FilterPredicate(EVENT_CODE, Query.FilterOperator.EQUAL, event_code),
						new Query.FilterPredicate(SITE_CODE, Query.FilterOperator.EQUAL, site_code),
						new Query.FilterPredicate(LINK, Query.FilterOperator.EQUAL, link)
				)
		).setKeysOnly();

		return DATASTORE.prepare(q).asSingleEntity() != null;
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	@Override
	public int compareTo(EventNews o)
	{
		int comparison = Long.compare(this.getDate(), o.getDate());
		return comparison != 0 ?
				-comparison :
				Long.compare(this.getId(), o.getId());
	}

	public long getId()
	{
		return (long) mEntity.getProperty(ID);
	}

	public long getEventCode()
	{
		return (long) mEntity.getProperty(EVENT_CODE);
	}

	public long getSiteCode()
	{
		return (long) mEntity.getProperty(SITE_CODE);
	}

	public String getLink()
	{
		return (String) mEntity.getProperty(LINK);
	}

	public long getDate()
	{
		return (long) mEntity.getProperty(DATE);
	}

	public long getSectionCode()
	{
		return (long) mEntity.getProperty(SECTION_CODE);
	}

	public ArrayList<String> getTags()
	{
		return (ArrayList<String>) mEntity.getProperty(TAGS);
	}

	public String getTitle()
	{
		return (String) mEntity.getProperty(TITLE);
	}

	public String getDescription()
	{
		return ((Text) mEntity.getProperty(DESCRIPTION)).getValue();
	}

	public String getContent()
	{
		return ((Text) mEntity.getProperty(CONTENT)).getValue();
	}

	public String getImgSrc()
	{
		return (String) mEntity.getProperty(IMG_SRC);
	}

}
