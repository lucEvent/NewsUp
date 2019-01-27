package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

public class RequestedSite extends DataStoreEntity {

	private static final String KIND = "RequestedSite";

	// [START keys]
	private static final String CODE = "code";                          // long
	private static final String ORIGINAL_REQUEST = "original_request";  // String
	private static final String NAME = "name";                          // String
	private static final String URL = "url";                            // String
	private static final String RSS_URL = "rss_url";                    // String
	private static final String ICON_URL = "icon_url";                  // String
	private static final String INFO = "info";                          // long
	private static final String COLOR = "color";                        // long
	private static final String REQUEST_COUNTER = "req_counter";        // long
	// [END keys]

	private RequestedSite(Entity e)
	{
		super(e);
	}

	public RequestedSite(String request, String name, String url, String rss_url, String icon_url, long info, long color)
	{
		super(new Entity(KIND));
		mEntity.setIndexedProperty(CODE, toCode(url));
		mEntity.setUnindexedProperty(ORIGINAL_REQUEST, request);
		mEntity.setUnindexedProperty(NAME, name);
		mEntity.setUnindexedProperty(URL, url);
		mEntity.setUnindexedProperty(RSS_URL, rss_url.startsWith("/") ? url + rss_url : rss_url);
		mEntity.setUnindexedProperty(ICON_URL, icon_url.startsWith("/") ? url + icon_url : icon_url);
		mEntity.setUnindexedProperty(INFO, info);
		mEntity.setUnindexedProperty(COLOR, color);
		mEntity.setUnindexedProperty(REQUEST_COUNTER, 1L);
		save();
	}

	@Deprecated
	public static void recover(long code, String original_request, String name, String url, String rss_url, String icon_url, long info, long color)
	{
		RequestedSite rs = new RequestedSite(new Entity(KIND));
		rs.mEntity.setIndexedProperty(CODE, code);
		rs.mEntity.setUnindexedProperty(ORIGINAL_REQUEST, original_request);
		rs.mEntity.setUnindexedProperty(NAME, name);
		rs.mEntity.setUnindexedProperty(URL, url);
		rs.mEntity.setUnindexedProperty(RSS_URL, rss_url);
		rs.mEntity.setUnindexedProperty(ICON_URL, icon_url);
		rs.mEntity.setUnindexedProperty(INFO, info);
		rs.mEntity.setUnindexedProperty(COLOR, color);
		rs.mEntity.setUnindexedProperty(REQUEST_COUNTER, 1L);
		rs.save();
	}

	public static List<RequestedSite> getAll()
	{
		ArrayList<RequestedSite> res = new ArrayList<>();

		Iterable<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterable();
		for (Entity e : it)
			res.add(new RequestedSite(e));

		return res;
	}

	public static RequestedSite get(long code)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(CODE, Query.FilterOperator.EQUAL, code)
		);

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		return e == null ? null : new RequestedSite(e);
	}

	public static RequestedSite get(String url)
	{
		return get(toCode(url));
	}

	public long getCode()
	{
		return (long) mEntity.getProperty(CODE);
	}

	public String getOriginalRequest()
	{
		return (String) mEntity.getProperty(ORIGINAL_REQUEST);
	}

	public String getName()
	{
		return (String) mEntity.getProperty(NAME);
	}

	public String getUrl()
	{
		return (String) mEntity.getProperty(URL);
	}

	public String getRssUrl()
	{
		return (String) mEntity.getProperty(RSS_URL);
	}

	public String getIconUrl()
	{
		return (String) mEntity.getProperty(ICON_URL);
	}

	public long getInfo()
	{
		return (long) mEntity.getProperty(INFO);
	}

	public long getColor()
	{
		return (long) mEntity.getProperty(COLOR);
	}

	public long getRequestCounter()
	{
		return (long) mEntity.getProperty(REQUEST_COUNTER);
	}

	public void countRequest()
	{
		mEntity.setUnindexedProperty(REQUEST_COUNTER, ((long) mEntity.getProperty(REQUEST_COUNTER)) + 1);
		save();
	}

	private static long toCode(String url)
	{
		return (long) Math.abs(url.hashCode());
	}

}
