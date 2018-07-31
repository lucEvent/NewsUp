package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElJuevesSections extends Sections {

	public ElJuevesSections()
	{
		super();

		add(new Section("Principal", "http://www.eljueves.es/feeds/rss.html", 0));

	}

}