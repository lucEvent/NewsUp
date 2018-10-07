package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.lucevent.newsup.data.util.News;

import java.util.ArrayList;

@Entity
public class EventNews implements Comparable<News> {

	@Id
	public long id;

	@Index
	public long event_code;

	@Index
	public int site_code;

	@Index
	public String link;

	public long date;
	public int section_code;
	public ArrayList<String> tags;
	public String title, description, content, imgSrc;

	public EventNews()
	{
	}

	@Override
	public int compareTo(News o)
	{
		int comparison = Long.compare(this.date, o.date);
		return comparison != 0 ? -comparison : Long.compare(this.id, o.id);
	}

}
