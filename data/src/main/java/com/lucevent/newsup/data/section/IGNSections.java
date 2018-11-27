package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class IGNSections extends Sections {

	public IGNSections()
	{
		super();

		add(new Section("English", "http://feeds.ign.com/ign/news", 0));
		add(new Section("Adria", "http://adria.ign.com/feed.xml", 0));
		add(new Section("Africa", "https://za.ign.com/feed.xml", 0));
		add(new Section("Dutch (Benelux)", "https://nl.ign.com/feed.xml", 0));
		add(new Section("Brasil", "https://br.ign.com/feed.xml", 0));
		add(new Section("\u4e2d\u56FD (China)", "http://www.ign.xn--fiqs8s/feed.xml", 0));
		add(new Section("Czech / Slovakia", "https://cz.ign.com/feed.xml", 0));
		add(new Section("France", "https://fr.ign.com/feed.xml", 0));
		add(new Section("Deutschland (Germany)", "https://de.ign.com/feed.xml", 0));
		add(new Section("\u0395\u03BB\u03BB\u03AC\u03B4\u03B1 (Greece)", "https://gr.ign.com/feed.xml", 0));
		add(new Section("Hungary", "https://hu.ign.com/feed.xml", 0));
		add(new Section("India", "https://in.ign.com/feed.xml", 0));
		add(new Section("Israel", "https://il.ign.com/feed.xml", 0));
		add(new Section("Italia", "https://it.ign.com/feed.xml", 0));
		add(new Section("\u65E5\u672C (Japan)", "https://jp.ign.com/feed.xml", 0));
		add(new Section("Latino Am\u00E9rica", "https://latam.ign.com/feed.xml", 0));
		add(new Section("\u0627\u0644\u0634\u0631\u0642 \u0627\u0644\u0623\u0648\u0633\u0637\u202C\u200E", "https://me.ign.com/ar/feed.xml", 0));
		add(new Section("Middle East (English)", "https://me.ign.com/en/feed.xml", 0));
		add(new Section("Nordic", "https://nordic.ign.com/feed.xml", 0));
		add(new Section("Poland", "https://pl.ign.com/feed.xml", 0));
		add(new Section("Portugal", "https://pt.ign.com/feed.xml", 0));
		add(new Section("Romania", "https://ro.ign.com/feed.xml", 0));
		add(new Section("\u0420\u043E\u0441\u0441\u0438\u044F (Russia)", "https://ru.ign.com/feed.xml", 0));
		add(new Section("Southeast Asia", "https://sea.ign.com/feed.xml", 0));
		add(new Section("Espa\u00F1a (Spain)", "https://es.ign.com/feed.xml", 0));
		add(new Section("T\u00FCrkiye (Turkey)", "https://tr.ign.com/feed.xml", 0));

	}

}
