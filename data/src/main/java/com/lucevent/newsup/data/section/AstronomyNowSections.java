package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AstronomyNowSections extends Sections {

	public AstronomyNowSections()
	{
		super();

		add(new Section("Homepage", "https://astronomynow.com/feed/", 0));
		add(new Section("News", "https://astronomynow.com/category/news/feed/", 0));
		add(new Section("Observing", "https://astronomynow.com/category/observing/feed/", 0));
		add(new Section("Equipment", "https://astronomynow.com/category/equipment/feed/", 0));

	}

}
