package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class HelsinkiSanomatSections extends Sections {

	public HelsinkiSanomatSections()
	{
		super();

		add(new Section("Uusimmat", "https://www.hs.fi/rss/tuoreimmat.xml", 0));
		add(new Section("Etusivun uutiset", "https://www.hs.fi/rss/teasers/etusivu.xml", 0));

		add(new Section("Uutiset", null, -1));
		add(new Section("Kotimaa", "https://www.hs.fi/rss/kotimaa.xml", 1));
		add(new Section("Ulkomaat", "https://www.hs.fi/rss/ulkomaat.xml", 1));
		add(new Section("Talous", "https://www.hs.fi/rss/talous.xml", 1));
		add(new Section("Politiikka", "https://www.hs.fi/rss/politiikka.xml", 1));
		add(new Section("Kaupunki", "https://www.hs.fi/rss/kaupunki.xml", 1));
		add(new Section("Urheilu", "https://www.hs.fi/rss/urheilu.xml", 1));
		add(new Section("Kulttuuri", "https://www.hs.fi/rss/kulttuuri.xml", 1));
		add(new Section("P\u00E4\u00E4kirjoitukset", "https://www.hs.fi/rss/paakirjoitukset.xml", 1));
		add(new Section("Lasten uutiset", "https://www.hs.fi/rss/lastenuutiset.xml", 1));

		add(new Section("Lifestyle", null, -1));
		add(new Section("Ruoka", "https://www.hs.fi/rss/ruoka.xml", 1));
		add(new Section("Koti", "https://www.hs.fi/rss/koti.xml", 1));
		add(new Section("El\u00E4m\u00E4 & Hyvinvointi", "https://www.hs.fi/rss/elamahyvinvointi.xml", 1));
		add(new Section("Ura", "https://www.hs.fi/rss/ura.xml", 1));
		add(new Section("Auto, Tiede & Tekniikka", "https://www.hs.fi/rss/autotiede.xml", 1));
		add(new Section("Matka", "https://www.hs.fi/rss/matka.xml", 1));
		add(new Section("Historia", "https://www.hs.fi/rss/historia.xml", 1));

		add(new Section("Muut", null, -1));
		add(new Section("Nyt", "https://www.hs.fi/rss/nyt.xml", 1));
		add(new Section("Kuukausiliite", "https://www.hs.fi/rss/kuukausiliite.xml", 1));

	}

}