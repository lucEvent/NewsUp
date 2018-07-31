package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LaVanguardiaSections extends Sections {

	public LaVanguardiaSections()
	{
		super();

		add(new Section("Principal", "http://www.lavanguardia.com/feed/rss/home", 0));
		add(new Section("Internacional", "http://www.lavanguardia.com/feed/rss/internacional", 0));
		add(new Section("Pol\u00EDtica", "http://www.lavanguardia.com/feed/rss/politica", 0));
		add(new Section("Vida", "http://www.lavanguardia.com/feed/rss/vida", 0));
		add(new Section("Deportes", "http://www.lavanguardia.com/feed/rss/deportes", 0));
		add(new Section("Econom\u00EDa", "http://www.lavanguardia.com/feed/rss/economia", 0));
		add(new Section("Opini\u00F3n", "http://www.lavanguardia.com/feed/rss/opinion", 0));
		add(new Section("Cultura", "http://www.lavanguardia.com/feed/rss/cultura", 0));
		add(new Section("Gente", "http://www.lavanguardia.com/feed/rss/gente", 0));
		add(new Section("Participaci\u00F3n", "http://www.lavanguardia.com/feed/rss/participacion", 0));

		add(new Section("Canales", null, -1));
		add(new Section("Natural", "http://www.lavanguardia.com/feed/rss/vida/natural", 1));
		add(new Section("Big Vang", "http://www.lavanguardia.com/feed/rss/ciencia", 1));
		add(new Section("Salud", "http://www.lavanguardia.com/feed/rss/vida/salud", 1));
		add(new Section("Tecnolog\u00EDa", "http://www.lavanguardia.com/feed/rss/tecnologia", 1));
		add(new Section("Televisi\u00F3n", "http://www.lavanguardia.com/feed/rss/ocio/television", 1));
		add(new Section("Series", "http://www.lavanguardia.com/feed/rss/ocio/series", 1));
		add(new Section("Motor", "http://www.lavanguardia.com/feed/rss/ocio/motor", 1));
		add(new Section("De Moda", "http://www.lavanguardia.com/feed/rss/de-moda", 1));
		add(new Section("Vivo", "http://www.lavanguardia.com/feed/rss/vivo", 1));

		add(new Section("Ediciones Locales", null, -1));
		add(new Section("Barcelona", "http://www.lavanguardia.com/feed/rss/local/barcelona", 1));
		add(new Section("Tarragona", "http://www.lavanguardia.com/feed/rss/local/tarragona", 1));
		add(new Section("Lleida", "http://www.lavanguardia.com/feed/rss/local/lleida", 1));
		add(new Section("Girona", "http://www.lavanguardia.com/feed/rss/local/girona", 1));
		add(new Section("Madrid", "http://www.lavanguardia.com/feed/rss/local/madrid", 1));
		add(new Section("Andaluc\u00EDa", "http://www.lavanguardia.com/feed/rss/local/sevilla", 1));
		add(new Section("Comunidad Valenciana", "http://www.lavanguardia.com/feed/rss/local/valencia", 1));
		add(new Section("Pa\u00EDs Vasco", "http://www.lavanguardia.com/feed/rss/local/paisvasco", 1));

		add(new Section("Blogs", null, -1));
		add(new Section("El otro esca\u00F1o", "http://feeds.feedburner.com/lavanguardia/elotroescano", 1));

	}

}
