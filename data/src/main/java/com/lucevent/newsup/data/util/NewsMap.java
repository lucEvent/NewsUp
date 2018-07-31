package com.lucevent.newsup.data.util;

import java.util.TreeMap;

/**
 * A NewsMap is a Map which sorts News by its id
 */
public class NewsMap extends TreeMap<Integer, News> {

	public NewsMap()
	{
		super();
	}

	public void add(News news)
	{
		put(news.id, news);
	}

	public void addAll(NewsArray news)
	{
		for (News n : news)
			put(n.id, n);
	}

}
