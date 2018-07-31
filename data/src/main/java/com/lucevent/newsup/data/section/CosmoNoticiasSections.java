package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class CosmoNoticiasSections extends Sections {

	public CosmoNoticiasSections()
	{
		super();

		add(new Section("Principal", "http://www.cosmonoticias.org/feed/", 0));
		add(new Section("Actividades y eventos", "http://www.cosmonoticias.org/category/actividades-eventos/feed/", 0));
		add(new Section("Astrobiolog\u00EDa", "http://www.cosmonoticias.org/category/astrobiologia/feed/", 0));
		add(new Section("Astronom\u00EDa general", "http://www.cosmonoticias.org/category/astronomia-general/feed/", 0));
		add(new Section("Cosmolog\u00EDa", "http://www.cosmonoticias.org/category/cosmologia/feed/", 0));
		add(new Section("Exoplanetas y exolunas", "http://www.cosmonoticias.org/category/exoplanetas-exolunas/feed/", 0));
		add(new Section("Misiones y Sondas", "http://www.cosmonoticias.org/category/misiones-sondas/feed/", 0));
		add(new Section("Otros", "http://www.cosmonoticias.org/category/otros/feed/", 0));

	}

}
