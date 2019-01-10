package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.RegionalSection;
import com.lucevent.newsup.data.util.Sections;

public class IGNSections extends Sections {

	public IGNSections()
	{
		super();

		add(new RegionalSection("English", "http://feeds.ign.com/ign/news", 0, "en", ""));
		add(new RegionalSection("Adria", "http://adria.ign.com/feed.xml", 0, "adria", "adria"));
		add(new RegionalSection("Africa", "https://za.ign.com/feed.xml", 0, "en", "za"));
		add(new RegionalSection("Dutch (Benelux)", "https://nl.ign.com/feed.xml", 0, "nl", "benelux"));
		add(new RegionalSection("Brasil", "https://br.ign.com/feed.xml", 0, "pt", "br"));
		add(new RegionalSection("\u4e2d\u56FD (China)", "http://www.ign.xn--fiqs8s/feed.xml", 0, "zh", "cn"));
		add(new RegionalSection("Czech / Slovakia", "https://cz.ign.com/feed.xml", 0, "cs", "cz"));
		add(new RegionalSection("France", "https://fr.ign.com/feed.xml", 0, "fr", "fr"));
		add(new RegionalSection("Deutschland (Germany)", "https://de.ign.com/feed.xml", 0, "de", "de"));
		add(new RegionalSection("\u0395\u03BB\u03BB\u03AC\u03B4\u03B1 (Greece)", "https://gr.ign.com/feed.xml", 0, "el", "gr"));
		add(new RegionalSection("Hungary", "https://hu.ign.com/feed.xml", 0, "hu", "hu"));
		add(new RegionalSection("India", "https://in.ign.com/feed.xml", 0, "en", "in"));
		add(new RegionalSection("Israel", "https://il.ign.com/feed.xml", 0, "iw", "il"));
		add(new RegionalSection("Italia", "https://it.ign.com/feed.xml", 0, "it", "it"));
		add(new RegionalSection("\u65E5\u672C (Japan)", "https://jp.ign.com/feed.xml", 0, "ja", "jp"));
		add(new RegionalSection("Latino Am\u00E9rica", "https://latam.ign.com/feed.xml", 0, "es", "latam"));
		add(new RegionalSection("\u0627\u0644\u0634\u0631\u0642 \u0627\u0644\u0623\u0648\u0633\u0637\u202C\u200E", "https://me.ign.com/ar/feed.xml", 0, "ar", "middle east"));
		add(new RegionalSection("Middle East (English)", "https://me.ign.com/en/feed.xml", 0, "en", "middle east"));
		add(new RegionalSection("Nordic", "https://nordic.ign.com/feed.xml", 0, "en", "nordic"));
		add(new RegionalSection("Polska (Poland)", "https://pl.ign.com/feed.xml", 0, "pl", "pl"));
		add(new RegionalSection("Portugal", "https://pt.ign.com/feed.xml", 0, "pt", "pt"));
		add(new RegionalSection("Rom\u00E2nia", "https://ro.ign.com/feed.xml", 0, "ro", "ro"));
		add(new RegionalSection("\u0420\u043E\u0441\u0441\u0438\u044F (Russia)", "https://ru.ign.com/feed.xml", 0, "ru", "ru"));
		add(new RegionalSection("Southeast Asia", "https://sea.ign.com/feed.xml", 0, "en", "asia"));
		add(new RegionalSection("Espa\u00F1a (Spain)", "https://es.ign.com/feed.xml", 0, "es", "es"));
		add(new RegionalSection("T\u00FCrkiye (Turkey)", "https://tr.ign.com/feed.xml", 0, "tr", "tr"));

	}

	@Override
	public boolean areRegional()
	{
		return true;
	}

}
