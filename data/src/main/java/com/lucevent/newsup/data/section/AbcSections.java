package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AbcSections extends Sections {

	public AbcSections()
	{
		super();

		add(new Section("\u00DAltima hora", "http://www.abc.es/rss/feeds/abc_ultima.xml", 0));

		add(new Section("Espa\u00F1a", "http://www.abc.es/rss/feeds/abc_EspanaEspana.xml", 0));
		add(new Section("Casa Real", "http://www.abc.es/rss/feeds/abc_CasasReales.xml", 1));
		add(new Section("Madrid", "http://www.abc.es/rss/feeds/abc_Madrid.xml", 1));
		add(new Section("Sevilla", "http://sevilla.abc.es/rss/feeds/Sevilla_Sevilla.xml", 1));
		add(new Section("Arag\u00F3n", "http://www.abc.es/rss/feeds/abc_Aragon.xml", 1));
		add(new Section("Canarias", "http://www.abc.es/rss/feeds/abc_Canarias.xml", 1));
		add(new Section("Castilla y Le\u00F3n", "http://www.abc.es/rss/feeds/abc_CastillaLeon.xml", 1));
		add(new Section("Catalu\u00F1a", "http://www.abc.es/rss/feeds/abc_Catalunya.xml", 1));
		add(new Section("C. Valenciana", "http://www.abc.es/rss/feeds/abc_Valencia.xml", 1));
		add(new Section("Galicia", "http://www.abc.es/rss/feeds/abc_Galicia.xml", 1));
		add(new Section("Navarra", "http://www.abc.es/rss/feeds/abc_Navarra.xml", 1));
		add(new Section("Pa\u00EDs Vasco", "http://www.abc.es/rss/feeds/abc_PaisVasco.xml", 1));
		add(new Section("Toledo", "http://www.abc.es/rss/feeds/abc_Toledo.xml", 1));

		add(new Section("Internacional", "http://www.abc.es/rss/feeds/abc_Internacional.xml", 0));
		add(new Section("Econom\u00EDa", "http://www.abc.es/rss/feeds/abc_Economia.xml", 0));

		add(new Section("Opini\u00F3n", "http://www.abc.es/rss/feeds/abc_opinioncompleto.xml", 0));
		add(new Section("Blogs de actualidad", "http://www.abc.es/rss/feeds/blogs-actualidad.xml", 1));

		add(new Section("Deportes", "http://www.abc.es/rss/feeds/abc_Deportes.xml", 0));
		add(new Section("Real Madrid", "http://www.abc.es/rss/feeds/abc_RealMadrid.xml", 1));
		add(new Section("Atl\u00E9tico Madrid", "http://www.abc.es/rss/feeds/abc_AtleticoMadrid.xml", 1));
		add(new Section("Baloncesto", "http://www.abc.es/rss/feeds/abc_Baloncesto.xml", 1));
		add(new Section("Tenis", "http://www.abc.es/rss/feeds/abc_Tenis.xml", 1));
		add(new Section("F\u00F3rmula 1", "http://www.abc.es/rss/feeds/abc_Automovilismo.xml", 1));
		add(new Section("Motos", "http://www.abc.es/rss/feeds/abc_Motociclismo.xml", 1));
		add(new Section("Na\u00FAtica", "http://www.abc.es/rss/feeds/abc_Vela.xml", 1));

		add(new Section("Conocer", "http://www.abc.es/rss/feeds/abc_Conocer.xml", 0));
		add(new Section("Ciencia", "http://www.abc.es/rss/feeds/abc_Ciencia.xml", 1));
		add(new Section("Salud", "http://www.abc.es/rss/feeds/abc_SociedadSalud.xml", 1));
		add(new Section("Familia", "http://www.abc.es/rss/feeds/abc_Familia.xml", 1));
		add(new Section("Viajar", "http://www.abc.es/rss/feeds/abc_Viajar.xml", 1));
		add(new Section("Tecnolog\u00EDa", "http://www.abc.es/rss/feeds/abc_Tecnologia.xml", 1));
		add(new Section("El Recreo", "http://www.abc.es/rss/feeds/abc_Recreo.xml", 1));
		add(new Section("Natural", "http://www.abc.es/rss/feeds/abc_Natural.xml", 1));
		add(new Section("Motor", "http://www.abc.es/rss/feeds/abc_Motor.xml", 1));

		add(new Section("Gente & Estilo", "http://www.abc.es/rss/feeds/abc_Estilo.xml", 0));
		add(new Section("Moda", "http://www.abc.es/rss/feeds/abc_Moda.xml", 1));

		add(new Section("Cultura & Ocio", "http://www.abc.es/rss/feeds/abc_Cultura.xml", 0));
		add(new Section("Libros", "http://www.abc.es/rss/feeds/abc_Libros.xml", 1));
		add(new Section("Teatro", "http://www.abc.es/rss/feeds/abc_Teatro.xml", 1));
		add(new Section("Arte", "http://www.abc.es/rss/feeds/abc_Arte.xml", 1));
		add(new Section("ABC Cultural", "http://www.abc.es/rss/feeds/abc_ABCCultural.xml", 1));
		add(new Section("Eurovisi\u00F3n", "http://www.abc.es/rss/feeds/abc_Eurovision.xml", 1));

	}

}
