package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LifeHackerSections extends Sections {

	public LifeHackerSections()
	{
		super();

		add(new Section("Homepage", "http://lifehacker.com/rss/vip", 0));
		add(new Section("Skillet", "http://skillet.lifehacker.com/rss/vip", 0));
		add(new Section("Two cents", "http://twocents.lifehacker.com/rss/vip", 0));
		add(new Section("Vitals", "http://vitals.lifehacker.com/rss/vip", 0));
		add(new Section("Gear", "http://gear.lifehacker.com/rss/vip", 0));

	}

}
