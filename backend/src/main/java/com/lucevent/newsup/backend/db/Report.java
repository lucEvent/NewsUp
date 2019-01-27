package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;

import java.util.ArrayList;
import java.util.List;

public class Report extends DataStoreEntity {

	private static final String KIND = "Report";

	// [START keys]
	private static final String TIME = "time";              // long     #ID#
	private static final String APP_VERSION = "app_version";// String
	private static final String IP = "ip";                  // String
	private static final String EMAIL = "email";            // String
	private static final String MESSAGE = "message";        // Text
	private static final String ID = TIME;                  // long
	// [END keys]

	public Report(long time, String app_version, String ip, String email, String message)
	{
		super(new Entity(KIND));
		mEntity.setIndexedProperty(TIME, time);
		mEntity.setUnindexedProperty(APP_VERSION, app_version);
		mEntity.setUnindexedProperty(IP, ip);
		mEntity.setUnindexedProperty(EMAIL, email);
		mEntity.setUnindexedProperty(MESSAGE, new Text(message));
	}

	private Report(Entity e)
	{
		super(e);
	}

	public static List<Report> getAll()
	{
		ArrayList<Report> res = new ArrayList<>();

		Iterable<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterable();
		for (Entity e : it)
			res.add(new Report(e));

		return res;
	}

	public static void delete(long id)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(ID, Query.FilterOperator.EQUAL, id)
		).setKeysOnly();

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null)
			DATASTORE.delete(e.getKey());
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public long getId()
	{
		return (long) mEntity.getProperty(TIME);
	}

	public long getTime()
	{
		return (long) mEntity.getProperty(TIME);
	}

	public String getAppVersion()
	{
		return (String) mEntity.getProperty(APP_VERSION);
	}

	public String getIP()
	{
		return (String) mEntity.getProperty(IP);
	}

	public String getEmail()
	{
		return (String) mEntity.getProperty(EMAIL);
	}

	public String getMessage()
	{
		return ((Text) mEntity.getProperty(MESSAGE)).getValue();
	}

}
