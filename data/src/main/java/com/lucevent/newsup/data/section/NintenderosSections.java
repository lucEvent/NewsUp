package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class NintenderosSections extends Sections {

	public NintenderosSections()
	{
		super();

		add(new Section("Principal", "http://www.nintenderos.com/feed/", 0));

		add(new Section("Switch", "http://www.nintenderos.com/category/switch/feed/", 0));
		add(new Section("3DS", "http://www.nintenderos.com/category/3ds/feed/", 0));
		add(new Section("M\u00F3vil", "http://www.nintenderos.com/category/movil/feed/", 0));
		add(new Section("Wii U", "http://www.nintenderos.com/category/wii-u/feed/", 0));
		add(new Section("Wii", "http://www.nintenderos.com/category/wii/feed/", 0));
		add(new Section("DS", "http://www.nintenderos.com/category/ds/feed/", 0));
		add(new Section("Game Cube", "http://www.nintenderos.com/category/game-cube/feed/", 0));
		add(new Section("Game Boy Series", "http://www.nintenderos.com/category/game-boy-series/feed/", 0));
		add(new Section("Nintendo 64", "http://www.nintenderos.com/category/nintendo-64/feed/", 0));
		add(new Section("Super Nintendo", "http://www.nintenderos.com/category/super-nintendo/feed/", 0));
		add(new Section("NES", "http://www.nintenderos.com/category/nes/feed/", 0));

		add(new Section("Mario", "http://www.nintenderos.com/tag/mario/feed/", 0));
		add(new Section("Zelda", "http://www.nintenderos.com/tag/zelda/feed/", 0));
		add(new Section("Pok\u00E9mon", "http://www.nintenderos.com/tag/pokemon/feed/", 0));
	}

}
