package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheOnionSections extends Sections {

	public TheOnionSections()
	{
		super();

		add(new Section("Homepage", "https://www.theonion.com/rss/vip", 0));
		add(new Section("Politics", "https://politics.theonion.com/rss/vip", 0));
		add(new Section("Sports", "https://sports.theonion.com/rss/vip", 0));
		add(new Section("Local", "https://local.theonion.com/rss/vip", 0));
		add(new Section("Entertainment", "https://entertainment.theonion.com/rss/vip", 0));

	}

}
