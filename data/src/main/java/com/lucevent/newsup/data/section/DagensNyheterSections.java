package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DagensNyheterSections extends Sections {

	public DagensNyheterSections()
	{
		super();

		add(new Section("Senaste nytt", "https://www.dn.se/rss/senaste-nytt/", 0));
		add(new Section("Goda Nyheter", "https://www.dn.se/rss/goda-nyheter/", 0));

		add(new Section("Stockholm", "https://www.dn.se/rss/sthlm/", 0));
		add(new Section("Sverige", "https://www.dn.se/rss/nyheter/sverige/", 0));
		add(new Section("V\u00E4rlden", "https://www.dn.se/rss/nyheter/varlden/", 0));

		add(new Section("Ekonomi", "https://www.dn.se/rss/ekonomi/", 0));
		add(new Section("Politik", "https://www.dn.se/rss/nyheter/politik/", 0));
		add(new Section("Vetenskap", "https://www.dn.se/rss/nyheter/vetenskap/", 0));
		add(new Section("Motor", "https://www.dn.se/rss/ekonomi/motor/", 0));

		add(new Section("Sport", "https://www.dn.se/rss/sport/", 0));
		add(new Section("Ishockey", "https://www.dn.se/rss/sport/ishockey/", 1));

		add(new Section("Kultur", "https://www.dn.se/rss/kultur-noje/", 0));
		add(new Section("Bok", "https://www.dn.se/rss/dnbok/", 1));
		add(new Section("Kulturdebatt", "https://www.dn.se/rss/kultur-noje/kulturdebatt/", 1));
		add(new Section("Film - TV", "https://www.dn.se/rss/kultur-noje/film-tv/", 1));
		add(new Section("Musik", "https://www.dn.se/rss/kultur-noje/musik/", 1));
		add(new Section("Scen", "https://www.dn.se/rss/kultur-noje/scen/", 1));

	}

}
