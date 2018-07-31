package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GoogleEarthBlogSections extends Sections {

	public GoogleEarthBlogSections()
	{
		super();

		add(new Section("Main", "https://www.gearthblog.com/feed", 0));
		add(new Section("Tips", "https://www.gearthblog.com/category/google_earth_tips/feed", 0));

	}

}
