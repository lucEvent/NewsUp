package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PokemonGoSections extends Sections {

	public PokemonGoSections()
	{
		super();

		add(new Section("English", "http://pokemongolive.com/en/post", 0));
		add(new Section("Deutch", "http://pokemongolive.com/de/post", 0));
		add(new Section("Espa\u00F1ol", "http://pokemongolive.com/es/post", 0));
		add(new Section("Fran\u00E7ais", "http://pokemongolive.com/fr/post", 0));
		add(new Section("Italiano", "http://pokemongolive.com/it/post", 0));
		add(new Section("\u65E5\u672C\u8A9E", "http://pokemongolive.com/ja/post", 0));
		add(new Section("\uD55C\uAD6D\uC5B4", "http://pokemongolive.com/ko/post", 0));
		add(new Section("Portugu\u00EAs (Brasil)", "https://pokemongolive.com/pt_br/post/", 0));
		add(new Section("\u4E2D\u6587 (\u7E41\u9AD4)", "https://pokemongolive.com/zh_hant/post/", 0));

	}

}
