package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SiberianTimesSections extends Sections {

	public SiberianTimesSections()
	{
		super();

		add(new Section("Business", "http://siberiantimes.com/business/rss/", 0));
		add(new Section("Culture", "http://siberiantimes.com/culture/rss/", 0));
		add(new Section("Ecology", "http://siberiantimes.com/ecology/rss/", 0));
		add(new Section("Health & Lifestyle", "http://siberiantimes.com/healthandlifestyle/rss/", 0));
		add(new Section("Science", "http://siberiantimes.com/science/rss/", 0));
		add(new Section("Sport", "http://siberiantimes.com/sport/rss/", 0));

	}

}
