package com.lucevent.newsup.debugbackend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public class TestCounter extends DataStoreEntity {

	private static final String KIND = "TestCounter";

	// [START keys]
	private static final String COUNTER = "counter";    // long
	// [END keys]

	public TestCounter(Entity e)
	{
		super(e);
	}

	public static TestCounter getInstance()
	{
		Entity e = DATASTORE.prepare(new Query(KIND)).asSingleEntity();
		if (e != null)
			return new TestCounter(e);

		e = new Entity(KIND);
		e.setUnindexedProperty(COUNTER, 0L);

		TestCounter res = new TestCounter(e);
		res.save();
		return res;
	}

	public long getCounter()
	{
		return (long) mEntity.getProperty(COUNTER);
	}

	public void count()
	{
		mEntity.setProperty(COUNTER, ((long) mEntity.getProperty(COUNTER)) + 1);
	}

}
