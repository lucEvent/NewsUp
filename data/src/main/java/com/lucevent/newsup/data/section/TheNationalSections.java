package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheNationalSections extends Sections {

	public TheNationalSections()
	{
		super();

		add(new Section("News", "http://www.thenational.scot/news/rss/", 0));
		add(new Section("Politics", "http://www.thenational.scot/politics/rss/", 0));
		add(new Section("Sport", "http://www.thenational.scot/sport/rss/", 0));
		add(new Section("Business", "http://www.thenational.scot/business/rss/", 0));
		add(new Section("Culture", "http://www.thenational.scot/culture/rss/", 0));
		add(new Section("World", "http://www.thenational.scot/world/rss/", 0));
		add(new Section("Comment", "http://www.thenational.scot/comment/rss/", 0));
		add(new Section("Community", "http://www.thenational.scot/community/rss/", 0));
		add(new Section("Library", "http://www.thenational.scot/library/rss/", 0));

	}

}
