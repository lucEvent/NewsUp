package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AftonbladetSections extends Sections {

	public AftonbladetSections()
	{
		super();

		add(new Section("Startsidan", "https://www.aftonbladet.se/rss.xml", 0));
		add(new Section("Senaste Nytt", "https://www.aftonbladet.se/senastenytt/rss.xml", 0));
		add(new Section("Nyheter", "https://www.aftonbladet.se/nyheter/rss.xml", 0));
		add(new Section("Sportbladet", "https://www.aftonbladet.se/sportbladet/rss.xml", 0));
		add(new Section("Fotboll", "https://www.aftonbladet.se/sportbladet/fotboll/rss.xml", 1));
		add(new Section("Hockey", "https://www.aftonbladet.se/sportbladet/hockey/rss.xml", 1));
		add(new Section("N\u00F6jesbladet", "https://www.aftonbladet.se/nojesbladet/rss.xml", 0));
		add(new Section("Ledare", "https://www.aftonbladet.se/ledare/rss.xml", 0));
		add(new Section("Kultur", "https://www.aftonbladet.se/kultur/rss.xml", 0));
		add(new Section("Bil", "https://www.aftonbladet.se/bil/rss.xml", 0));
		add(new Section("Kolumnister", "https://www.aftonbladet.se/nyheter/kolumnister/rss.xml", 0));

	}

}
