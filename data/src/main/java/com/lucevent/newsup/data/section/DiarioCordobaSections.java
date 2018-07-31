package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DiarioCordobaSections extends Sections {

	public DiarioCordobaSections()
	{
		super();

		add(new Section("Portada", "http://zetaestaticos.com/cordoba/rss/portada_es.xml", 0));
		add(new Section("\u00DAltima hora", "http://zetaestaticos.com/cordoba/rss/ultimahora_es.xml", 0));
		add(new Section("Andaluc\u00EDa", "http://zetaestaticos.com/cordoba/rss/1_es.xml", 0));
		add(new Section("Espa\u00F1a", "http://zetaestaticos.com/cordoba/rss/7_es.xml", 0));
		add(new Section("Internacional", "http://zetaestaticos.com/cordoba/rss/6_es.xml", 0));

		add(new Section("Deportes", "http://zetaestaticos.com/cordoba/rss/4_es.xml", 0));
		add(new Section("\u00C1rea Blanquiverde", "http://zetaestaticos.com/cordoba/rss/8_es.xml", 1));
		add(new Section("Atletismo", "http://zetaestaticos.com/cordoba/rss/262_es.xml", 1));
		add(new Section("Baloncesto", "http://zetaestaticos.com/cordoba/rss/264_es.xml", 1));
		add(new Section("Balonmano", "http://zetaestaticos.com/cordoba/rss/263_es.xml", 1));
		add(new Section("H\u00EDpica", "http://zetaestaticos.com/cordoba/rss/242_es.xml", 1));
		add(new Section("Polideportivo", "http://zetaestaticos.com/cordoba/rss/265_es.xml", 1));

		add(new Section("Cultura", "http://zetaestaticos.com/cordoba/rss/3_es.xml", 0));
		add(new Section("Cofrad\u00EDas", "http://zetaestaticos.com/cordoba/rss/246_es.xml", 1));

		add(new Section("Econom\u00EDa", "http://zetaestaticos.com/cordoba/rss/5_es.xml", 0));

		add(new Section("Provincia", "http://zetaestaticos.com/cordoba/rss/102_es.xml", 0));
		add(new Section("Alto Guadalquivir", "http://zetaestaticos.com/cordoba/rss/233_es.xml", 1));
		add(new Section("Local", "http://zetaestaticos.com/cordoba/rss/101_es.xml", 1));
		add(new Section("Lucena", "http://zetaestaticos.com/cordoba/rss/235_es.xml", 1));
		add(new Section("Montilla", "http://zetaestaticos.com/cordoba/rss/236_es.xml", 1));
		add(new Section("Pozoblanco", "http://zetaestaticos.com/cordoba/rss/238_es.xml", 1));

		add(new Section("Gente", "http://zetaestaticos.com/cordoba/rss/204_es.xml", 0));
		add(new Section("Sociedad", "http://zetaestaticos.com/cordoba/rss/103_es.xml", 0));
		add(new Section("Televisi\u00F3n", "http://zetaestaticos.com/cordoba/rss/104_es.xml", 0));

		add(new Section("Tema del d\u00EDa", "http://zetaestaticos.com/cordoba/rss/106_es.xml", 0));
		add(new Section("Opini\u00F3n", "http://zetaestaticos.com/cordoba/rss/100_es.xml", 0));

		add(new Section("UCOniversitas", "http://zetaestaticos.com/cordoba/rss/261_es.xml", 0));

	}

}