package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheLocalSections extends Sections {

	public TheLocalSections()
	{
		super();

		add(new Section("Austria", "https://www.thelocal.at/feeds/rss.php", 0));
		add(new Section("Denmark", "https://www.thelocal.dk/feeds/rss.php", 0));
		add(new Section("France", "https://www.thelocal.fr/feeds/rss.php", 0));
		add(new Section("Germany", "https://www.thelocal.de/feeds/rss.php", 0));
		add(new Section("Italy", "https://www.thelocal.it/feeds/rss.php", 0));
		add(new Section("Norway", "https://www.thelocal.no/feeds/rss.php", 0));
		add(new Section("Spain", "https://www.thelocal.es/feeds/rss.php", 0));
		add(new Section("Sweden", "https://www.thelocal.se/feeds/rss.php", 0));
		add(new Section("Switzerland", "https://www.thelocal.ch/feeds/rss.php", 0));

	}

}
