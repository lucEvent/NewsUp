package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheInterceptSections extends Sections {

	public TheInterceptSections()
	{
		super();

		add(new Section("All", "https://theintercept.com/feed/?rss", 0));
		add(new Section("English", "https://theintercept.com/feed/?lang=en", 1));
		add(new Section("Portuguese", "https://theintercept.com/feed/?lang=pt", 1));
		add(new Section("Features", "https://theintercept.com/feed/?mk=fl_is_on_feature_page&mv=1", 0));

		add(new Section("Staff", null, -1));
		add(new Section("Ryan Devereaux", "https://theintercept.com/staff/ryan-devereaux/feed/?rss", 1));
		add(new Section("Ryan Gallagher", "https://theintercept.com/staff/ryan-gallagher/feed/?rss", 1));
		add(new Section("Glenn Greenwald", "https://theintercept.com/staff/glenn-greenwald/feed/?rss", 1));
		add(new Section("Murtaza Hussain", "https://theintercept.com/staff/murtaza-hussain/feed/?rss", 1));
		add(new Section("Micah Lee", "https://theintercept.com/staff/micah-lee/feed/?rss", 1));
		add(new Section("Peter Maass", "https://theintercept.com/staff/peter-maass/feed/?rss", 1));
		add(new Section("Jeremy Scahill", "https://theintercept.com/staff/jeremy-scahill/feed/?rss", 1));
		add(new Section("Liliana Segura", "https://theintercept.com/staff/liliana-segura/feed/?rss", 1));

	}

}
