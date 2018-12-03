package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class FinlandTodaySections extends Sections {

	public FinlandTodaySections()
	{
		super();

		add(new Section("Main", "https://finlandtoday.fi/feed/", 0));
		add(new Section("News", "https://finlandtoday.fi/category/news/feed/", 0));
		add(new Section("Politics", "https://finlandtoday.fi/category/politics/feed/", 0));
		add(new Section("Culture", "https://finlandtoday.fi/category/culture/feed/", 0));
		add(new Section("Music", "https://finlandtoday.fi/category/music/feed/", 0));
		add(new Section("Business", "https://finlandtoday.fi/category/business/feed/", 0));
		add(new Section("Lifestyle", "https://finlandtoday.fi/category/lifestyle/feed/", 0));

	}

}
