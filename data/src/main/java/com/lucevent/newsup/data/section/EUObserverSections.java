package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class EUObserverSections extends Sections {

	public EUObserverSections()
	{
		super();

		add(new Section("Main", "https://xml.euobserver.com/rss.xml", 0));

	}

}
