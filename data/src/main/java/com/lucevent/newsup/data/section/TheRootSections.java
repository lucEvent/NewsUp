package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheRootSections extends Sections {

	public TheRootSections()
	{
		super();

		add(new Section("Homepage", "https://www.theroot.com/rss/vip", 0));
		add(new Section("Very Smart Brothas", "https://verysmartbrothas.theroot.com/rss/vip", 0));
		add(new Section("The Glow Up", "https://theglowup.theroot.com/rss/vip", 0));
		add(new Section("The Grapevine", "https://thegrapevine.theroot.com/rss/vip", 0));

	}

}
