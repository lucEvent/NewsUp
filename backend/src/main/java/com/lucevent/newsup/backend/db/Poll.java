package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;

public class Poll extends DataStoreEntity {

	private static final String KIND = "Poll";

	// [START keys]
	private static final String ID = "id";                             // long
	private static final String ANSWER_1_CODE = "answer_1_code";       // long
	private static final String ANSWER_2_CODE = "answer_2_code";       // long
	private static final String ANSWER_3_CODE = "answer_3_code";       // long
	private static final String ANSWER_1_COUNTER = "answer_1_counter"; // long
	private static final String ANSWER_2_COUNTER = "answer_2_counter"; // long
	private static final String ANSWER_3_COUNTER = "answer_3_counter"; // long
	// [END keys]

	public Poll()
	{
		super(new Entity(KIND));
	}

	private Poll(Entity e)
	{
		super(e);
	}

	public static Poll get(long id)
	{
		Query q = new Query(KIND).setFilter(
				new Query.FilterPredicate(ID, Query.FilterOperator.EQUAL, id)
		);

		Entity e = DATASTORE.prepare(q).asSingleEntity();
		return e != null ? new Poll(e) : null;
	}

	public static ArrayList<Poll> getAll()
	{
		Query q = new Query(KIND);

		ArrayList<Poll> res = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(q).asQueryResultIterable();
		for (Entity e : it)
			res.add(new Poll(e));

		return res;
	}

	public static void deleteAll()
	{
		deleteAll(KIND);
	}

	public void init(long id, long answer_1_code, long answer_2_code, long answer_3_code)
	{
		mEntity.setIndexedProperty(ID, id);
		mEntity.setUnindexedProperty(ANSWER_1_CODE, answer_1_code);
		mEntity.setUnindexedProperty(ANSWER_2_CODE, answer_2_code);
		mEntity.setUnindexedProperty(ANSWER_3_CODE, answer_3_code);
		mEntity.setUnindexedProperty(ANSWER_1_COUNTER, 0);
		mEntity.setUnindexedProperty(ANSWER_2_COUNTER, 0);
		mEntity.setUnindexedProperty(ANSWER_3_COUNTER, 0);
	}

	public long getId()
	{
		return (long) mEntity.getProperty(ID);
	}

	public long getAnswer1Code()
	{
		return (long) mEntity.getProperty(ANSWER_1_CODE);
	}

	public long getAnswer2Code()
	{
		return (long) mEntity.getProperty(ANSWER_2_CODE);
	}

	public long getAnswer3Code()
	{
		return (long) mEntity.getProperty(ANSWER_3_CODE);
	}

	public long getAnswer1Counter()
	{
		return (long) mEntity.getProperty(ANSWER_1_COUNTER);
	}

	public long getAnswer2Counter()
	{
		return (long) mEntity.getProperty(ANSWER_2_COUNTER);
	}

	public long getAnswer3Counter()
	{
		return (long) mEntity.getProperty(ANSWER_3_COUNTER);
	}

	public void incAnswer1Counter()
	{
		mEntity.setUnindexedProperty(ANSWER_1_COUNTER, getAnswer1Counter() + 1);
	}

	public void incAnswer2Counter()
	{
		mEntity.setUnindexedProperty(ANSWER_2_COUNTER, getAnswer2Counter() + 1);
	}

	public void incAnswer3Counter()
	{
		mEntity.setUnindexedProperty(ANSWER_3_COUNTER, getAnswer3Counter() + 1);
	}

}
