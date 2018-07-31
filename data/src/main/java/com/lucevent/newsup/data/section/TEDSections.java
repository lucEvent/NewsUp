package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TEDSections extends Sections {

	public TEDSections()
	{
		super();

		add(new Section("TED Talks", "https://pa.tedcdn.com/feeds/talks.rss", 0));
		add(new Section("Blog", "https://blog.ted.com/feed/", 0));

		add(new Section("Ideas", "https://ideas.ted.com/feed/", 0));
		add(new Section("Tech", "https://ideas.ted.com/category/tech/feed/", 1));
		add(new Section("Business", "https://ideas.ted.com/category/business/feed/", 1));
		add(new Section("Arts & Design", "https://ideas.ted.com/category/arts-design/feed/", 1));
		add(new Section("Science", "https://ideas.ted.com/category/science/feed/", 1));
		add(new Section("We humans", "https://ideas.ted.com/category/we-humans/feed/", 1));

	}

}

