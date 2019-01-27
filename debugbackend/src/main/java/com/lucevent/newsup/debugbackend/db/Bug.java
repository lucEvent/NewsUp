package com.lucevent.newsup.debugbackend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;
import java.util.List;

public class Bug extends DataStoreEntity {

	private static final String KIND = "Bug";

	// [START keys]
	private static final String SITE_CODE = "site_code";        // long
	private static final String TIME = "time";                  // long  #ID#
	private static final String DESCRIPTION = "description";    // Text
	private static final String LINK = "link";                  // String
	private static final String CONTENT = "content";            // Text
	// [END keys]

	private Bug(Entity e)
	{
		super(e);
	}

	public Bug(News news, String description)
	{
		super(new Entity(KIND));

		mEntity.setProperty(SITE_CODE, news.site_code);
		mEntity.setProperty(TIME, System.currentTimeMillis());
		mEntity.setProperty(DESCRIPTION, new Text(description));
		mEntity.setProperty(LINK, news.link);
		mEntity.setProperty(CONTENT, new Text(news.content));
	}

	public Bug(Site site, String description)
	{
		super(new Entity(KIND));

		mEntity.setProperty(SITE_CODE, site.code);
		mEntity.setProperty(TIME, System.currentTimeMillis());
		mEntity.setProperty(DESCRIPTION, new Text(description));
		mEntity.setProperty(LINK, "");
		mEntity.setProperty(CONTENT, new Text(""));
	}

	public static List<Bug> getAll()
	{
		ArrayList<Bug> res = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterable();
		for (Entity e : it)
			res.add(new Bug(e));

		return res;
	}

	public static boolean delete(long id)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(TIME, Query.FilterOperator.EQUAL, id)
		).setKeysOnly();

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {
			DATASTORE.delete(e.getKey());
			return true;
		}
		return false;
	}

	public long getId()
	{
		return (long) mEntity.getProperty(TIME);
	}

	public long getSiteCode()
	{
		return (long) mEntity.getProperty(SITE_CODE);
	}

	public long getTime()
	{
		return (long) mEntity.getProperty(TIME);
	}

	public String getDescription()
	{
		return ((Text) mEntity.getProperty(DESCRIPTION)).getValue();
	}

	public String getLink()
	{
		return (String) mEntity.getProperty(LINK);
	}

	public String getContent()
	{
		return ((Text) mEntity.getProperty(CONTENT)).getValue();
	}

}
