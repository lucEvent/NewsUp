package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElEconomistaSections extends Sections {

	public ElEconomistaSections()
	{
		super();

		add(new Section("Flash del mercado", "https://www.eleconomista.es/rss/rss-flash-del-mercado.php", 0));
		add(new Section("Selecci\u00F3n elEconomista", "https://www.eleconomista.es/rss/rss-seleccion-ee.php", 0));
		add(new Section("Empresas", "https://www.eleconomista.es/rss/rss-empresas.php", 0));
		add(new Section("Mercados", "https://www.eleconomista.es/rss/rss-mercados.php", 0));
		add(new Section("Econom\u00EDa", "https://www.eleconomista.es/rss/rss-economia.php", 0));
		add(new Section("Tecnolog\u00EDa", "https://www.eleconomista.es/rss/rss-category.php?category=tecnologia", 0));
		add(new Section("Fondos", "https://www.eleconomista.es/rss/rss-fondos.php", 0));
		add(new Section("Gesti\u00F3n", "https://www.eleconomista.es/rss/rss-gestion.php", 0));
		add(new Section("Ecoley", "https://www.eleconomista.es/rss/rss-ecoley.php", 0));
		add(new Section("Emprendedores", "https://www.eleconomista.es/rss/rss-emprendedores.php", 0));
		add(new Section("Evasion", "https://www.eleconomista.es/rss/rss-evasion.php", 0));
		add(new Section("Ecoteuve", "https://www.eleconomista.es/rss/rss-ecoteuve.php", 0));

		add(new Section("Eco Diario", null, -1));
		add(new Section("Espa\u00F1a", "https://www.eleconomista.es/rss/rss-espania.php", 1));
		add(new Section("Global", "https://www.eleconomista.es/rss/rss-global.php", 1));
		add(new Section("Deportes", "https://www.eleconomista.es/rss/rss-deportes.php", 1));
		add(new Section("Cultura", "https://www.eleconomista.es/rss/rss-cultura.php", 1));
		add(new Section("Medio Ambiente", "https://www.eleconomista.es/rss/rss-medio-ambiente.php", 1));

	}

}
