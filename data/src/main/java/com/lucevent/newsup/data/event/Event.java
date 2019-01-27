package com.lucevent.newsup.data.event;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;

import java.util.ArrayList;
import java.util.Collection;

public class Event {

	public int code;

	public String title;

	public String imgSrc;

	public ArrayList<Source> sources;

	public ArrayList<String> keyWords;

	public Event()
	{
	}

	public NewsArray filter(Collection<News> c)
	{
		NewsArray r = new NewsArray(c.size());
		for (News n : c)
			if (n.hasKeyWords(keyWords))
				r.add(n);

		return r;
	}

}
