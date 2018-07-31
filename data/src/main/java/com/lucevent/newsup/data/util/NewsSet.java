package com.lucevent.newsup.data.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * A NewsSet is a Set which sorts News by its comparator or by the comparator provided in the constructor
 */
public class NewsSet extends TreeSet<News> {

	public NewsSet()
	{
		super();
	}

	public NewsSet(Collection<? extends News> c)
	{
		super(c);
	}

	public NewsSet(Comparator<News> comparator)
	{
		super(comparator);
	}

}

