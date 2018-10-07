package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AbcSections extends Sections {

	public AbcSections()
	{
		super();

		add(new Section("\u00DAltima hora", "https://www.abc.es/rss/feeds/abc_ultima.xml", 0));

		add(new Section("Espa\u00F1a", "https://www.abc.es/rss/feeds/abc_EspanaEspana.xml", 0));
		add(new Section("Casa Real", "https://www.abc.es/rss/feeds/abc_CasasReales.xml", 1));
		add(new Section("Madrid", "https://www.abc.es/rss/feeds/abc_Madrid.xml", 1));
		add(new Section("Sevilla", "https://sevilla.abc.es/rss/feeds/Sevilla_Sevilla.xml", 1));
		add(new Section("Arag\u00F3n", "https://www.abc.es/rss/feeds/abc_Aragon.xml", 1));
		add(new Section("Canarias", "https://www.abc.es/rss/feeds/abc_Canarias.xml", 1));
		add(new Section("Castilla y Le\u00F3n", "https://www.abc.es/rss/feeds/abc_CastillaLeon.xml", 1));
		add(new Section("Catalu\u00F1a", "https://www.abc.es/rss/feeds/abc_Catalunya.xml", 1));
		add(new Section("C. Valenciana", "https://www.abc.es/rss/feeds/abc_Valencia.xml", 1));
		add(new Section("Galicia", "https://www.abc.es/rss/feeds/abc_Galicia.xml", 1));
		add(new Section("Navarra", "https://www.abc.es/rss/feeds/abc_Navarra.xml", 1));
		add(new Section("Pa\u00EDs Vasco", "https://www.abc.es/rss/feeds/abc_PaisVasco.xml", 1));
		add(new Section("Toledo", "https://www.abc.es/rss/feeds/abc_Toledo.xml", 1));

		add(new Section("Internacional", "https://www.abc.es/rss/feeds/abc_Internacional.xml", 0));
		add(new Section("Econom\u00EDa", "https://www.abc.es/rss/feeds/abc_Economia.xml", 0));

		add(new Section("Opini\u00F3n", "https://www.abc.es/rss/feeds/abc_opinioncompleto.xml", 0));
		add(new Section("Blogs de actualidad", "https://www.abc.es/rss/feeds/blogs-actualidad.xml", 1));

		add(new Section("Deportes", "https://www.abc.es/rss/feeds/abc_Deportes.xml", 0));
		add(new Section("Real Madrid", "https://www.abc.es/rss/feeds/abc_RealMadrid.xml", 1));
		add(new Section("Atl\u00E9tico Madrid", "https://www.abc.es/rss/feeds/abc_AtleticoMadrid.xml", 1));
		add(new Section("Baloncesto", "https://www.abc.es/rss/feeds/abc_Baloncesto.xml", 1));
		add(new Section("Tenis", "https://www.abc.es/rss/feeds/abc_Tenis.xml", 1));
		add(new Section("F\u00F3rmula 1", "https://www.abc.es/rss/feeds/abc_Automovilismo.xml", 1));
		add(new Section("Motos", "https://www.abc.es/rss/feeds/abc_Motociclismo.xml", 1));
		add(new Section("Na\u00FAtica", "https://www.abc.es/rss/feeds/abc_Vela.xml", 1));

		add(new Section("Conocer", "https://www.abc.es/rss/feeds/abc_Conocer.xml", 0));
		add(new Section("Ciencia", "https://www.abc.es/rss/feeds/abc_Ciencia.xml", 1));
		add(new Section("Salud", "https://www.abc.es/rss/feeds/abc_SociedadSalud.xml", 1));
		add(new Section("Familia", "https://www.abc.es/rss/feeds/abc_Familia.xml", 1));
		add(new Section("Viajar", "https://www.abc.es/rss/feeds/abc_Viajar.xml", 1));
		add(new Section("Tecnolog\u00EDa", "https://www.abc.es/rss/feeds/abc_Tecnologia.xml", 1));
		add(new Section("El Recreo", "https://www.abc.es/rss/feeds/abc_Recreo.xml", 1));
		add(new Section("Natural", "https://www.abc.es/rss/feeds/abc_Natural.xml", 1));
		add(new Section("Motor", "https://www.abc.es/rss/feeds/abc_Motor.xml", 1));

		add(new Section("Gente & Estilo", "https://www.abc.es/rss/feeds/abc_Estilo.xml", 0));
		add(new Section("Moda", "https://www.abc.es/rss/feeds/abc_Moda.xml", 1));

		add(new Section("Cultura & Ocio", "https://www.abc.es/rss/feeds/abc_Cultura.xml", 0));
		add(new Section("Libros", "https://www.abc.es/rss/feeds/abc_Libros.xml", 1));
		add(new Section("Teatro", "https://www.abc.es/rss/feeds/abc_Teatro.xml", 1));
		add(new Section("Arte", "https://www.abc.es/rss/feeds/abc_Arte.xml", 1));
		add(new Section("ABC Cultural", "https://www.abc.es/rss/feeds/abc_ABCCultural.xml", 1));
		add(new Section("Eurovisi\u00F3n", "https://www.abc.es/rss/feeds/abc_Eurovision.xml", 1));

	}

}
