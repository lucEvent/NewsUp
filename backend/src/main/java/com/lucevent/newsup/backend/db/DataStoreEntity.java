package com.lucevent.newsup.backend.db;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;

public abstract class DataStoreEntity {

	protected static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

	protected Entity mEntity;

	public DataStoreEntity(Entity e)
	{
		mEntity = e;
	}

	public final void save()
	{
		DATASTORE.put(mEntity);
	}

	static void deleteAll(String kind)
	{
		FetchOptions options = FetchOptions.Builder
				.withLimit(1000);

		ArrayList<Key> keys = new ArrayList<>();
		Iterable<Entity> it = DATASTORE.prepare(new Query(kind).setKeysOnly()).asQueryResultIterable(options);
		for (Entity e : it)
			keys.add(e.getKey());

		DATASTORE.delete(keys);
	}

}
