package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeStats extends DataStoreEntity {

	private static final String KIND = "TimeStats";

	// [START keys]
	private static final String VALUES = "values";            // ArrayList<Long> -> long[][]
	// [END keys]

	private TimeStats(Entity e)
	{
		super(e);
	}

	public static TimeStats getInstance()
	{
		Query q = new Query(KIND);

		TimeStats res;
		Entity e = DATASTORE.prepare(q).asSingleEntity();
		if (e != null) {

			res = new TimeStats(e);

		} else {

			res = new TimeStats(new Entity(KIND));
			res.initialize();
			res.save();

		}
		return res;
	}

	@Deprecated
	public static void recover(ArrayList<Long> lvalues)
	{
		Entity e = new Entity(KIND);
		e.setUnindexedProperty(VALUES, lvalues);

		new TimeStats(e)
				.save();
	}

	public void count()
	{
		synchronized (this) {
			Calendar calendar = new GregorianCalendar();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);

			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int dayOfTheWeek = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7;

			int pos = (dayOfTheWeek * 24) + hour;
			ArrayList<Long> values = (ArrayList<Long>) mEntity.getProperty(VALUES);
			values.set(pos, values.get(pos) + 1);
			mEntity.setProperty(VALUES, values);

			save();
		}
	}

	public void reset()
	{
		synchronized (this) {
			initialize();
			save();
		}
	}

	private void initialize()
	{
		int length = 7 * 24;
		ArrayList<Long> tmp = new ArrayList<>(length);
		for (int i = 0; i < length; i++)
			tmp.add(0L);

		mEntity.setUnindexedProperty(VALUES, tmp);
	}

	@Override
	public String toString()
	{
		ArrayList<Long> values = (ArrayList<Long>) mEntity.getProperty(VALUES);
		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0; ; i++) {
			b.append(values.get(i));
			if (i == values.size() - 1)
				return b.append(']').toString();
			b.append(", ");
		}
	}

}
