package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class HelsinkiTimesSections extends Sections {

	public HelsinkiTimesSections()
	{
		super();

		add(new Section("News", "http://www.helsinkitimes.fi/?format=feed&type=rss", 0));
		add(new Section("Domestic", "http://www.helsinkitimes.fi/finland/finland-news/domestic.feed?type=rss", 1));
		add(new Section("Politics", "http://www.helsinkitimes.fi/finland/finland-news/politics.feed?type=rss", 1));
		add(new Section("Business", "http://www.helsinkitimes.fi/business.feed?type=rss", 0));
		add(new Section("Columns", "http://www.helsinkitimes.fi/columns.feed?type=rss", 0));

	}

}