package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GizmodoSections extends Sections {

	public GizmodoSections()
	{
		super();

		add(new Section("Homepage", "http://gizmodo.com/rss/vip", 0));
		add(new Section("Sploid", "http://sploid.gizmodo.com/rss/vip", 0));
		add(new Section("Paleofuture", "http://paleofuture.gizmodo.com/rss/vip", 0));
		add(new Section("io9", "http://io9.gizmodo.com/rss/vip", 0));
		add(new Section("Earther", "http://earther.gizmodo.com/rss/vip", 0));

	}

}
