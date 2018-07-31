package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class IltalehtiSections extends Sections {

	public IltalehtiSections()
	{
		super();

		add(new Section("Uutiset", "https://www.iltalehti.fi/rss/uutiset.xml", 0));
		add(new Section("Kotimaan uutiset", "https://www.iltalehti.fi/rss/kotimaa.xml", 0));
		add(new Section("Ulkomaan uutiset", "https://www.iltalehti.fi/rss/ulkomaat.xml", 0));

		add(new Section("Urheilu-uutiset", "https://www.iltalehti.fi/rss/urheilu.xml", 0));
		add(new Section("Formula 1", "https://www.iltalehti.fi/rss/formulat.xml", 1));
		add(new Section("Jalkapallo", "https://www.iltalehti.fi/rss/jalkapallo.xml", 1));
		add(new Section("J\u00E4\u00E4kiekko", "https://www.iltalehti.fi/rss/jaakiekko.xml", 1));
		add(new Section("Ralli", "https://www.iltalehti.fi/rss/ralli.xml", 1));

		add(new Section("Viihde", "https://www.iltalehti.fi/rss/viihde.xml", 0));
		add(new Section("Musiikki", "https://www.iltalehti.fi/rss/musiikki.xml", 1));
		add(new Section("Kuninkaalliset", "https://www.iltalehti.fi/rss/kuninkaalliset.xml", 1));

		add(new Section("Autot", "https://www.iltalehti.fi/rss/autot.xml", 0));
		add(new Section("Digi", "https://www.iltalehti.fi/rss/digi.xml", 0));
		add(new Section("Terveys", "https://www.iltalehti.fi/rss/terveys.xml", 0));
		add(new Section("Tyyli.com", "https://www.iltalehti.fi/rss/tyylicom.xml", 0));
		add(new Section("Asuminen", "https://www.iltalehti.fi/rss/asuminen.xml", 0));
		add(new Section("Matkailu", "https://www.iltalehti.fi/rss/matkailu.xml", 0));

	}

}
