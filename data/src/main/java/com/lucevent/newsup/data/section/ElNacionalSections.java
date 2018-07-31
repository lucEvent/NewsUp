package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElNacionalSections extends Sections {

	public ElNacionalSections()
	{
		super();

		add(new Section("Not\u00EDcies", "https://www.elnacional.cat/uploads/feeds/feed_ca.xml", 0));
		add(new Section("En espa\u00F1ol", "https://www.elnacional.cat/uploads/feeds/feed_es.xml", 0));
		add(new Section("In English", "https://www.elnacional.cat/uploads/feeds/feed_english_en.xml", 0));

	}

}
