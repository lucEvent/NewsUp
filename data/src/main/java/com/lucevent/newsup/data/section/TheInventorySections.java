package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheInventorySections extends Sections {

	public TheInventorySections()
	{
		super();

		add(new Section("Homepage", "https://theinventory.com/rss/vip", 0));
		add(new Section("News", "https://news.theinventory.com/rss/vip", 0));
		add(new Section("Points", "https://points.theinventory.com/rss/vip", 0));
		add(new Section("Co-op", "https://co-op.theinventory.com/rss/vip", 0));
		add(new Section("Kinja Deals", "https://kinjadeals.theinventory.com/rss/vip", 0));

	}

}
