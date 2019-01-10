package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.RegionalSection;
import com.lucevent.newsup.data.util.Sections;

public class TheLocalSections extends Sections {

	public TheLocalSections()
	{
		super();

		add(new RegionalSection("Austria", "https://www.thelocal.at/feeds/rss.php", 0, "en", "at"));
		add(new RegionalSection("Denmark", "https://www.thelocal.dk/feeds/rss.php", 0, "en", "dk"));
		add(new RegionalSection("France", "https://www.thelocal.fr/feeds/rss.php", 0, "en", "fr"));
		add(new RegionalSection("Germany", "https://www.thelocal.de/feeds/rss.php", 0, "en", "de"));
		add(new RegionalSection("Italy", "https://www.thelocal.it/feeds/rss.php", 0, "en", "it"));
		add(new RegionalSection("Norway", "https://www.thelocal.no/feeds/rss.php", 0, "en", "no"));
		add(new RegionalSection("Spain", "https://www.thelocal.es/feeds/rss.php", 0, "en", "es"));
		add(new RegionalSection("Sweden", "https://www.thelocal.se/feeds/rss.php", 0, "en", "se"));
		add(new RegionalSection("Switzerland", "https://www.thelocal.ch/feeds/rss.php", 0, "en", "ch"));

	}

	@Override
	public boolean areRegional()
	{
		return true;
	}



}
