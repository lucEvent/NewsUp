package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LaRazonSections extends Sections {

	public LaRazonSections()
	{
		super();

		add(new Section("Espa\u00F1a", "https://www.larazon.es/rss/espana.xml", 0));
		add(new Section("Andaluc\u00EDa", "https://www.larazon.es/rss/local/andalucia.xml", 1));
		add(new Section("Catalu\u00F1a", "https://www.larazon.es/rss/local/cataluna.xml", 1));
		add(new Section("Castilla y Le\u00F3n", "https://www.larazon.es/rss/local/castilla-y-leon.xml", 1));
		add(new Section("Comunidad Valenciana", "https://www.larazon.es/rss/local/comunidad-valenciana.xml", 1));
		add(new Section("Madrid", "https://www.larazon.es/rss/local/madrid.xml", 1));

		add(new Section("Deportes", "https://www.larazon.es/rss/deportes.xml", 0));
		add(new Section("F\u00FAtbol", "https://www.larazon.es/rss/deportes/futbol.xml", 1));
		add(new Section("Tenis", "https://www.larazon.es/rss/deportes/tenis.xml", 1));
		add(new Section("Golf", "https://www.larazon.es/rss/deportes/golf.xml", 1));
		add(new Section("Motociclismo", "https://www.larazon.es/rss/deportes/motociclismo.xml", 1));

		add(new Section("Internacional", "https://www.larazon.es/rss/internacional.xml", 0));
		add(new Section("Econom\u00EDa", "https://www.larazon.es/rss/economia.xml", 0));
		add(new Section("Lifestyle", "https://www.larazon.es/rss/lifestyle.xml", 0));
		add(new Section("Religi\u00F3n", "https://www.larazon.es/rss/religion.xml", 0));
		add(new Section("Viajes", "https://www.larazon.es/rss/viajes.xml", 0));

		add(new Section("Sociedad", "https://www.larazon.es/rss/sociedad.xml", 0));
		add(new Section("Ciencia", "https://www.larazon.es/rss/sociedad/ciencia.xml", 1));
		add(new Section("Educaci\u00F3n", "https://www.larazon.es/rss/sociedad/educacion.xml", 1));
		add(new Section("Medio Ambiente", "https://www.larazon.es/rss/sociedad/medio-ambiente.xml", 1));

		add(new Section("Cultura", "https://www.larazon.es/rss/cultura.xml", 0));
		add(new Section("Cine", "https://www.larazon.es/rss/cultura/cine.xml", 1));
		add(new Section("Libros", "https://www.larazon.es/rss/cultura/libros.xml", 1));
		add(new Section("M\u00FAsica", "https://www.larazon.es/rss/cultura/musica.xml", 1));
		add(new Section("Teatro", "https://www.larazon.es/rss/cultura/teatro.xml", 1));

	}

}