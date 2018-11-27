package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class IGNEsSections extends Sections {

	public IGNEsSections()
	{
		super();

		add(new Section("Principal", "https://es.ign.com/feed.xml", 0));
		add(new Section("PS4", "https://es.ign.com/ps4.xml", 0));
		add(new Section("Xbox One", "https://es.ign.com/xbox-one.xml", 0));
		add(new Section("PC", "https://es.ign.com/pc.xml", 0));
		add(new Section("Switch", "https://es.ign.com/nintendo-switch.xml", 0));
		add(new Section("3DS", "https://es.ign.com/3ds.xml", 0));
		add(new Section("Vita", "https://es.ign.com/vita.xml", 0));
		add(new Section("Indie", "https://es.ign.com/indie.xml", 0));
		add(new Section("Retro", "https://es.ign.com/retro.xml", 0));
		add(new Section("Cine", "https://es.ign.com/movies.xml", 0));
		add(new Section("TV", "https://es.ign.com/tv.xml", 0));
		add(new Section("Tech", "https://es.ign.com/tech.xml", 0));
		add(new Section("M\u00F3viles", "https://es.ign.com/mobile.xml", 0));
		add(new Section("Comics", "https://es.ign.com/comics.xml", 0));
		add(new Section("eSports", "https://es.ign.com/esports.xml", 0));

	}

}
