package com.lucevent.newsup.debugbackend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

public class PartialTestResult extends DataStoreEntity {

	private static final String KIND = "PartialTestResult";

	// [START keys]
	private static final String TASK_ID = "task_id";            // long
	private static final String SITE_CODE = "site_code";        // long
	private static final String NUM_NEWS = "num_news";          // long
	private static final String TEST_RESULTS = "test_results";  // ArrayList<long>
	// [END keys]

	public PartialTestResult()
	{
		super(new Entity(KIND));
	}

	private PartialTestResult(Entity e)
	{
		super(e);
	}

	public static List<PartialTestResult> getAll(long task_id)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(TASK_ID, Query.FilterOperator.EQUAL, task_id)
		);

		ArrayList<PartialTestResult> res = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(q).asQueryResultIterable();
		for (Entity e : it)
			res.add(new PartialTestResult(e));

		return res;
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public long getSiteCode()
	{
		return (long) mEntity.getProperty(SITE_CODE);
	}

	public ArrayList<Long> getTestResults()
	{
		return (ArrayList<Long>) mEntity.getProperty(TEST_RESULTS);
	}

	public void setTaskId(long task_id)
	{
		mEntity.setProperty(TASK_ID, task_id);
	}

	public void setSiteCode(long site_code)
	{
		mEntity.setProperty(SITE_CODE, site_code);
	}

	public void setNumNews(long num_news)
	{
		mEntity.setProperty(NUM_NEWS, num_news);
	}

	public void setTestResults(ArrayList<Long> test_results)
	{
		mEntity.setProperty(TEST_RESULTS, test_results);
	}

}

