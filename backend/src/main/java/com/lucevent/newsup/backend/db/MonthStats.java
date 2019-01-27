package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;

public class MonthStats extends DataStoreEntity implements Comparable<MonthStats> {

	public static final String[] MONTH_TO_STRING = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	private static final String KIND = "MonthStats";

	// [START keys]
	private static final String MONTH = "month";     // long
	private static final String YEAR = "year";       // long
	private static final String COUNTER = "counter"; // long
	// [END keys]

	private MonthStats(Entity entity)
	{
		super(entity);
	}

	public static MonthStats getInstance()
	{
		Calendar calendar = new GregorianCalendar();

		Query q = new Query(KIND).setFilter(
				CompositeFilterOperator.and(
						new FilterPredicate(MONTH, FilterOperator.EQUAL, calendar.get(Calendar.MONTH) + 1),
						new FilterPredicate(YEAR, FilterOperator.EQUAL, calendar.get(Calendar.YEAR))
				)
		);

		MonthStats res;
		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {

			res = new MonthStats(e);

		} else {

			e = new Entity(KIND);  // Key will be assigned once written
			e.setIndexedProperty(MONTH, calendar.get(Calendar.MONTH) + 1);
			e.setIndexedProperty(YEAR, calendar.get(Calendar.YEAR));
			e.setUnindexedProperty(COUNTER, 0L);

			res = new MonthStats(e);
			res.save();
		}

		return res;
	}

	@Deprecated
	public static void recover(long month, long year, long counter)
	{
		Entity e = new Entity(KIND);  // Key will be assigned once written
		e.setIndexedProperty(MONTH, month);
		e.setIndexedProperty(YEAR, year);
		e.setUnindexedProperty(COUNTER, counter);
		new MonthStats(e)
				.save();
	}

	public static TreeSet<MonthStats> getAll()
	{
		TreeSet<MonthStats> res = new TreeSet<>();

		Iterable<Entity> it = DATASTORE.prepare(new Query(KIND)).asQueryResultIterable();
		for (Entity e : it)
			res.add(new MonthStats(e));

		return res;
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public void count()
	{
		mEntity.setUnindexedProperty(COUNTER, (long) mEntity.getProperty(COUNTER) + 1);
		save();
	}

	public void newMonth()
	{
		mEntity = MonthStats.getInstance().mEntity;
	}

	public String getId()
	{
		return getYear() + "-" + MONTH_TO_STRING[((int) getMonth()) - 1];
	}

	public long getCounter()
	{
		return (long) mEntity.getProperty(COUNTER);
	}

	private long getMonth()
	{
		return (long) mEntity.getProperty(MONTH);
	}

	private long getYear()
	{
		return (long) mEntity.getProperty(YEAR);
	}

	@Override
	public int compareTo(MonthStats ms2)
	{
		int yearComparison = Long.compare(getYear(), ms2.getYear());
		return yearComparison != 0 ?
				-yearComparison :
				-Long.compare(getMonth(), ms2.getMonth());
	}

}
