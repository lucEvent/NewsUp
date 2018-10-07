package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LifeHackerSections extends Sections {

	public LifeHackerSections()
	{
		super();

		add(new Section("Homepage", "https://lifehacker.com/rss/vip", 0));
		add(new Section("Skillet", "https://skillet.lifehacker.com/rss/vip", 0));
		add(new Section("Two Cents", "https://twocents.lifehacker.com/rss/vip", 0));
		add(new Section("Vitals", "https://vitals.lifehacker.com/rss/vip", 0));
		add(new Section("Offspring", "https://offspring.lifehacker.com/rss/vip", 0));
		add(new Section("The Upgrade", "https://lifehacker.com/tag/theupgrade/rss/vip", 0));

	}

}
