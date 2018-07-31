package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;

public class HuffingtonPostCanadaSections extends com.lucevent.newsup.data.util.Sections {

	public HuffingtonPostCanadaSections()
	{
		super();

		add(new Section("Main news", "https://www.huffingtonpost.ca/feeds/index.xml", 0));
		add(new Section("Alberta", "https://www.huffingtonpost.ca/feeds/verticals/canada-alberta/index.xml", 1));
		add(new Section("British Columbia", "https://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/index.xml", 1));
		add(new Section("Business", "https://www.huffingtonpost.ca/feeds/verticals/canada-business/index.xml", 0));

	}

}
