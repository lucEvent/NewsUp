package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPeriodicoEsSections extends Sections {

	public ElPeriodicoEsSections()
	{
		super();

		add(new Section("Portada", "https://www.elperiodico.com/es/rss/rss_portada.xml", 0));
		add(new Section("Opini\u00F3n", "https://www.elperiodico.com/es/rss/opinion/rss.xml", 0));
		add(new Section("Internacional", "https://www.elperiodico.com/es/rss/internacional/rss.xml", 0));
		add(new Section("Pol\u00EDtica", "https://www.elperiodico.com/es/rss/politica/rss.xml", 0));
		add(new Section("Sociedad", "https://www.elperiodico.com/es/rss/sociedad/rss.xml", 0));
		add(new Section("Econom\u00EDa", "https://www.elperiodico.com/es/rss/economia/rss.xml", 0));
		add(new Section("Tecnolog\u00EDa", "https://www.elperiodico.com/es/rss/tecnologia/rss.xml", 0));
		add(new Section("Deportes", "https://www.elperiodico.com/es/rss/deportes/rss.xml", 0));
		add(new Section("Ciencia", "https://www.elperiodico.com/es/rss/ciencia/rss.xml", 0));
		add(new Section("Medio ambiente", "https://www.elperiodico.com/es/rss/medio-ambiente/rss.xml", 0));
		add(new Section("Sanidad", "https://www.elperiodico.com/es/rss/sanidad/rss.xml", 0));
		add(new Section("Gente y TV", "https://www.elperiodico.com/es/rss/gente/rss.xml", 0));
		add(new Section("eXtra", "https://www.elperiodico.com/es/rss/extra/rss.xml", 0));

		add(new Section("Ciudades", null, -1));
		add(new Section("Barcelona", "https://www.elperiodico.com/es/rss/barcelona/rss.xml", 1));
		add(new Section("Santa Coloma", "https://www.elperiodico.com/es/rss/santa-coloma/rss.xml", 1));

		add(new Section("Blogs", null, -1));
		add(new Section("Bloglobal", "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.214333143.1954951623.1458825034", 1));
		add(new Section("Destinos", "http://www.visitdestinos.com/feed/", 1));

	}

}
