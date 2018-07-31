package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElImparcialSections extends Sections {

	public ElImparcialSections()
	{
		super();

		add(new Section("Portada ed. en l\u00EDnea", "http://www.elimparcial.com/rss/rss.xml", 0));
		add(new Section("Policiaca", "http://www.elimparcial.com/rss/rsspoliciaca.xml", 0));
		add(new Section("Local", "http://www.elimparcial.com/rss/rsslocal.xml", 0));
		add(new Section("Obreg\u00F3n", "http://www.elimparcial.com/rss/rssobregon.xml", 0));
		add(new Section("Nogales", "http://www.elimparcial.com/rss/rssnogales.xml", 0));
		add(new Section("Sonora", "http://www.elimparcial.com/rss/rsssonora.xml", 0));
		add(new Section("Nacional", "http://www.elimparcial.com/rss/rssnacional.xml", 0));
		add(new Section("Internacional", "http://www.elimparcial.com/rss/rssinternacional.xml", 0));
		add(new Section("Deportes Local", "http://www.elimparcial.com/rss/rssdeporteslocal.xml", 0));
		add(new Section("Deportes", "http://www.elimparcial.com/rss/rssdeportes.xml", 0));
		add(new Section("Entretenimiento", "http://www.elimparcial.com/rss/rssEntretenimiento.xml", 0));
		add(new Section("Ciencia y Tecnolog\u00EDa", "http://www.elimparcial.com/rss/rsscienciaytecnologia.xml", 0));
		add(new Section("Vida y Estilo", "http://www.elimparcial.com/rss/rssvidayestilo.xml", 0));

	}

}
