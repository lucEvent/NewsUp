package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class KotakuSections extends Sections {

	public KotakuSections()
	{
		super();

		add(new Section("Homepage", "https://kotaku.com/rss/vip", 0));
		add(new Section("The Bests", "https://thebests.kotaku.com/rss/vip", 0));
		add(new Section("Steamed", "https://steamed.kotaku.com/rss/vip", 0));
		add(new Section("Cosplay", "https://cosplay.kotaku.com/rss/vip", 0));

	}

}
