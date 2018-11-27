package com.lucevent.newsup.development.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class NationalGeographicSections extends Sections {

	public NationalGeographicSections()
	{
		super();

		add(new Section("Principal", "http://news.nationalgeographic.com/rss/index.rss", 0));

	}

}
