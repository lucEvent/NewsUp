package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SportSections extends Sections {

	public SportSections()
	{
		super();

		add(new Section("\u00DAltimas noticias", "https://www.sport.es/es/rss/last_news/rss.xml", 0));

		add(new Section("F\u00FAtbol", "https://www.sport.es/es/rss/futbol/rss.xml", 0));
		add(new Section("Bar\u00E7a", "https://www.sport.es/es/rss/barca/rss.xml", 1));
		add(new Section("Madrid", "https://www.sport.es/es/rss/real-madrid/rss.xml", 1));
		add(new Section("Espanyol", "https://www.sport.es/es/rss/espanyol/rss.xml", 1));
		add(new Section("La Liga", "https://www.sport.es/es/rss/laliga/rss.xml", 1));
		add(new Section("F\u00FAtbol base", "https://www.sport.es/es/rss/futbol-base/rss.xml", 1));

		add(new Section("Motor", "https://www.sport.es/es/rss/motor/rss.xml", 0));

		add(new Section("Tenis", "https://www.sport.es/es/rss/tenis/rss.xml", 0));
		add(new Section("Atletismo", "https://www.sport.es/es/rss/atletismo/rss.xml", 0));
		add(new Section("Ciclismo", "https://www.sport.es/es/rss/ciclismo/rss.xml", 0));
		add(new Section("Hockey", "https://www.sport.es/es/rss/hockey/rss.xml", 0));
		add(new Section("F\u00FAtbol sala", "https://www.sport.es/es/rss/futbol-sala/rss.xml", 0));
		add(new Section("Balonmano", "https://www.sport.es/es/rss/balonmano/rss.xml", 0));
		add(new Section("Golf", "https://www.sport.es/es/rss/golf/rss.xml", 0));

		add(new Section("Opini\u00F3n", "https://www.sport.es/es/rss/opinion/rss.xml", 0));

	}

}