package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.RegionalSection;
import com.lucevent.newsup.data.util.Sections;

public class ElNacionalSections extends Sections {

	public ElNacionalSections()
	{
		super();

		add(new RegionalSection("Not\u00EDcies", "https://www.elnacional.cat/uploads/feeds/feed_ca.xml", 0, "ca", ""));
		add(new RegionalSection("En espa\u00F1ol", "https://www.elnacional.cat/uploads/feeds/feed_es.xml", 0, "es", ""));
		add(new RegionalSection("In English", "https://www.elnacional.cat/uploads/feeds/feed_english_en.xml", 0, "en", ""));

	}

	@Override
	public boolean areRegional()
	{
		return true;
	}

}
