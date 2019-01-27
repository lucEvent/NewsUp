package com.lucevent.newsup.debugbackend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.TreeSet;

public class Task extends DataStoreEntity implements Comparable<Task> {

	private static final String KIND = "Task";

	// [START keys]
	private static final String START_TIME = "start_time";  // long #ID#
	private static final String END_TIME = "end_time";      // long
	private static final String ROUNDS = "rounds";          // long
	private static final String CURRENT_EVALUATING_SITE = "current_evaluating_site";        // long
	private static final String CURRENT_EVALUATING_SECTION = "current_evaluating_section";  // long
	private static final String TOTAL_NUM_NEWS = "total_num_news";                          // long
	private static final String TOTAL_TEST_RESULTS = "total_test_results";                  // ArrayList<long>
	private static final String SITE_NUM_NEWS = "site_num_news";                            // long
	private static final String SITE_TEST_RESULTS = "site_test_results";                    // ArrayList<long>
	// [END keys]

	private Task(Entity e)
	{
		super(e);
	}

	public static Task getCurrent(int default_test_results_size)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(END_TIME, Query.FilterOperator.EQUAL, -1)
		);

		Task res;
		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {

			res = new Task(e);

		} else {

			e = new Entity(KIND);  // Key will be assigned once written
			e.setIndexedProperty(START_TIME, System.currentTimeMillis());
			e.setIndexedProperty(END_TIME, -1L);
			e.setUnindexedProperty(ROUNDS, 0L);
			e.setUnindexedProperty(CURRENT_EVALUATING_SITE, 0L);
			e.setUnindexedProperty(CURRENT_EVALUATING_SECTION, 0L);
			e.setUnindexedProperty(TOTAL_NUM_NEWS, 0L);
			e.setUnindexedProperty(SITE_NUM_NEWS, 0L);

			ArrayList<Long> totalTestResults = new ArrayList<>(default_test_results_size);
			ArrayList<Long> siteTestResults = new ArrayList<>(default_test_results_size);
			for (int i = 0; i < default_test_results_size; i++) {
				totalTestResults.add(0L);
				siteTestResults.add(0L);
			}
			e.setUnindexedProperty(TOTAL_TEST_RESULTS, totalTestResults);
			e.setUnindexedProperty(SITE_TEST_RESULTS, siteTestResults);

			res = new Task(e);
			res.save();
		}

		return res;
	}

	public static TreeSet<Task> getAll()
	{
		TreeSet<Task> res = new TreeSet<>();
		Iterable<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterable();
		for (Entity e : it)
			res.add(new Task(e));

		return res;
	}

	public static boolean delete(long id)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(START_TIME, Query.FilterOperator.EQUAL, id)
		).setKeysOnly();

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {
			DATASTORE.delete(e.getKey());
			return true;
		}
		return false;
	}

	public void newRound()
	{
		mEntity.setProperty(ROUNDS, ((long) mEntity.getProperty(ROUNDS)) + 1);
		save();
	}

	public void end()
	{
		mEntity.setIndexedProperty(END_TIME, System.currentTimeMillis());
		save();
	}

	public long getId()
	{
		return (long) mEntity.getProperty(START_TIME);
	}

	public long getStartTime()
	{
		return (long) mEntity.getProperty(START_TIME);
	}

	public long getEndTime()
	{
		return (long) mEntity.getProperty(END_TIME);
	}

	public long getRounds()
	{
		return (long) mEntity.getProperty(ROUNDS);
	}

	public long getCurrentEvaluatingSite()
	{
		return (long) mEntity.getProperty(CURRENT_EVALUATING_SITE);
	}

	public long getCurrentEvaluatingSection()
	{
		return (long) mEntity.getProperty(CURRENT_EVALUATING_SECTION);
	}

	public long getTotalNumNews()
	{
		return (long) mEntity.getProperty(TOTAL_NUM_NEWS);
	}

	public long getSiteNumNews()
	{
		return (long) mEntity.getProperty(SITE_NUM_NEWS);
	}

	public ArrayList<Long> getTotalTestResults()
	{
		return (ArrayList<Long>) mEntity.getProperty(TOTAL_TEST_RESULTS);
	}

	public ArrayList<Long> getSiteTestResults()
	{
		return (ArrayList<Long>) mEntity.getProperty(SITE_TEST_RESULTS);
	}

	//

	public void currentEvaluatingSiteEnd()
	{
		mEntity.setProperty(CURRENT_EVALUATING_SITE, getCurrentEvaluatingSite() + 1);
	}

	public void setCurrentEvaluatingSection(long i)
	{
		mEntity.setProperty(CURRENT_EVALUATING_SECTION, i);
	}

	public void currentEvaluatingSectionEnd()
	{
		mEntity.setProperty(CURRENT_EVALUATING_SECTION, getCurrentEvaluatingSection() + 1);
	}

	public void setSiteNumNews(long i)
	{
		mEntity.setProperty(SITE_NUM_NEWS, i);
	}

	public void setTotalTestResults(ArrayList<Long> a)
	{
		mEntity.setProperty(TOTAL_TEST_RESULTS, a);
	}

	public void setSiteTestResults(ArrayList<Long> a)
	{
		mEntity.setProperty(SITE_TEST_RESULTS, a);
	}

	public void addSectionResults(int num_news, int[] values)
	{
		mEntity.setProperty(SITE_NUM_NEWS, ((long) mEntity.getProperty(SITE_NUM_NEWS)) + num_news);

		ArrayList<Long> siteTestResults = getSiteTestResults();
		ArrayList<Long> newResults = new ArrayList<>(siteTestResults.size());
		for (int i = 0; i < siteTestResults.size(); i++) {
			newResults.add(siteTestResults.get(i) + values[i]);
		}
		setSiteTestResults(newResults);
	}

	public void addSiteResults(long num_news, ArrayList<Long> results)
	{
		mEntity.setProperty(TOTAL_NUM_NEWS, ((long) mEntity.getProperty(TOTAL_NUM_NEWS)) + num_news);

		ArrayList<Long> totalTestResults = getTotalTestResults();
		ArrayList<Long> siteTestResults = new ArrayList<>(totalTestResults.size());
		ArrayList<Long> newResults = new ArrayList<>(totalTestResults.size());
		for (int i = 0; i < totalTestResults.size(); i++) {
			newResults.add(totalTestResults.get(i) + results.get(i));
			siteTestResults.add(0L);
		}
		setSiteTestResults(siteTestResults);
		setTotalTestResults(newResults);
	}

	@Override
	public int compareTo(Task task)
	{
		return -Long.compare(this.getStartTime(), task.getStartTime());
	}

}
