package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class EuropaPressSections extends Sections {

	public EuropaPressSections()
	{
		super();

		add(new Section("Portada", "http://www.europapress.es/rss/rss.aspx", 0));
		add(new Section("Nacional", "http://www.europapress.es/rss/rss.aspx?ch=66", 0));
		add(new Section("Internacional", "http://www.europapress.es/rss/rss.aspx?ch=69", 0));
		add(new Section("Econom\u00EDa", "http://www.europapress.es/rss/rss.aspx?ch=136", 0));
		add(new Section("EP Social", "http://www.europapress.es/rss/rss.aspx?ch=313", 0));
		add(new Section("Deportes", "http://www.europapress.es/rss/rss.aspx?ch=67", 0));
		add(new Section("Chance", "http://www.europapress.es/rss/rss.aspx?ch=549", 0));
		add(new Section("Cultura", "http://www.europapress.es/rss/rss.aspx?ch=126", 0));
		add(new Section("Sociedad", "http://www.europapress.es/rss/rss.aspx?ch=73", 0));
		add(new Section("Motor", "http://www.europapress.es/rss/rss.aspx?ch=435", 0));
		add(new Section("Comunicados", "http://www.europapress.es/rss/rss.aspx?ch=137", 0));

		add(new Section("Autonom\u00EDas", null, -1));
		add(new Section("Andaluc\u00EDa", "http://www.europapress.es/rss/rss.aspx?ch=279", 1));
		add(new Section("Arag\u00F3n", "http://www.europapress.es/rss/rss.aspx?ch=280", 1));
		add(new Section("Asturias", "http://www.europapress.es/rss/rss.aspx?ch=294", 1));
		add(new Section("Cantabria", "http://www.europapress.es/rss/rss.aspx?ch=281", 1));
		add(new Section("Castilla-La Mancha", "http://www.europapress.es/rss/rss.aspx?ch=282", 1));
		add(new Section("Castilla y Le\u00F3n", "http://www.europapress.es/rss/rss.aspx?ch=283", 1));
		add(new Section("Catalu\u00F1a", "http://www.europapress.es/rss/rss.aspx?ch=284", 1));
		add(new Section("Ceuta y Melilla", "http://www.europapress.es/rss/rss.aspx?ch=310", 1));
		add(new Section("Extremadura", "http://www.europapress.es/rss/rss.aspx?ch=285", 1));
		add(new Section("Galicia", "http://www.europapress.es/rss/rss.aspx?ch=286", 1));
		add(new Section("Islas Baleares", "http://www.europapress.es/rss/rss.aspx?ch=288", 1));
		add(new Section("Islas Canarias", "http://www.europapress.es/rss/rss.aspx?ch=287", 1));
		add(new Section("La Rioja", "http://www.europapress.es/rss/rss.aspx?ch=291", 1));
		add(new Section("Madrid", "http://www.europapress.es/rss/rss.aspx?ch=289", 1));
		add(new Section("Murcia", "http://www.europapress.es/rss/rss.aspx?ch=295", 1));
		add(new Section("Navarra", "http://www.europapress.es/rss/rss.aspx?ch=293", 1));
		add(new Section("Pa\u00EDs Vasco", "http://www.europapress.es/rss/rss.aspx?ch=290", 1));

		add(new Section("Lenguas", "http://www.europapress.es/rss/rss.aspx?ch=56", 0));
		add(new Section("Euskera", "http://www.europapress.es/rss/rss.aspx?ch=58", 1));
		add(new Section("Galego", "http://www.europapress.es/rss/rss.aspx?ch=57", 1));
		add(new Section("Valenci\u00E0", "http://www.europapress.es/rss/rss.aspx?ch=60", 1));
		add(new Section("Asturianu", "http://www.europapress.es/rss/rss.aspx?ch=395", 1));

	}

}