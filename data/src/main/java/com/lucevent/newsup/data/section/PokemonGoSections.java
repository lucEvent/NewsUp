package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.RegionalSection;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PokemonGoSections extends Sections {

	public PokemonGoSections()
	{
		super();

		add(new RegionalSection("Deutsche", "http://pokemongolive.com/de/post", 0, "de", ""));
		add(new RegionalSection("English", "http://pokemongolive.com/en/post", 0, "en", ""));
		add(new RegionalSection("Espa\u00F1ol", "http://pokemongolive.com/es/post", 0, "es", ""));
		add(new RegionalSection("Fran\u00E7ais", "http://pokemongolive.com/fr/post", 0, "fr", ""));
		add(new RegionalSection("Italiano", "http://pokemongolive.com/it/post", 0, "it", ""));
		add(new RegionalSection("\u65E5\u672C\u8A9E", "http://pokemongolive.com/ja/post", 0, "ja", ""));
		add(new RegionalSection("\uD55C\uAD6D\uC5B4", "http://pokemongolive.com/ko/post", 0, "ko", ""));
		add(new RegionalSection("Portugu\u00EAs (Brasil)", "https://pokemongolive.com/pt_br/post/", 0, "pt", ""));
		add(new RegionalSection("\u4E2D\u6587 (\u7E41\u9AD4)", "https://pokemongolive.com/zh_hant/post/", 0, "zh", ""));

	}

	@Override
	public boolean areRegional()
	{
		return true;
	}

	@Override
	protected Section getRegionalDefault()
	{
		return getRegional("en", "");
	}

}
