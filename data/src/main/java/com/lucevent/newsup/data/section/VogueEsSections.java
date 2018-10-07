package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VogueEsSections extends Sections {

	public VogueEsSections()
	{
		super();

		add(new Section("Portada", "https://www.vogue.es/home.xml", 0));

		add(new Section("Moda", "https://www.vogue.es/moda.xml", 0));
		add(new Section("News", "https://www.vogue.es/moda/news.xml", 1));
		add(new Section("Trends", "https://www.vogue.es/moda/tendencias.xml", 1));
		add(new Section("Streetstyle", "https://www.vogue.es/moda/streetstyle.xml", 1));
		add(new Section("7 d\u00EDas, 7 looks", "https://www.vogue.es/moda/tendencias/7-dias-7-looks.xml", 1));
		add(new Section("En el estudio de...", "https://www.vogue.es/moda/tendencias/en-el-estudio-de.xml", 1));

		add(new Section("Suzy Menkes", "https://www.vogue.es/suzy-menkes.xml", 0));

		add(new Section("Belleza", "https://www.vogue.es/belleza.xml", 0));
		add(new Section("Pelo", "https://www.vogue.es/belleza/pelo.xml", 1));
		add(new Section("Nutrici\u00F3n", "https://www.vogue.es/belleza/nutricion-dietas.xml", 1));
		add(new Section("Maquillaje", "https://www.vogue.es/belleza/maquillaje.xml", 1));
		add(new Section("Fitness", "https://www.vogue.es/belleza/fitness.xml", 1));
		add(new Section("Tendencias", "https://www.vogue.es/belleza/tendencias.xml", 1));
		add(new Section("Bienestar", "https://www.vogue.es/belleza/bienestar.xml", 1));

		add(new Section("Celebrities", "https://www.vogue.es/celebrities.xml", 0));
		add(new Section("Cannes", "https://www.vogue.es/celebrities/cannes.xml", 1));
		add(new Section("Gala Met", "https://www.vogue.es/celebrities/gala-met.xml", 1));
		add(new Section("Oscar", "https://www.vogue.es/celebrities/oscar.xml", 1));
		add(new Section("Festivales", "https://www.vogue.es/celebrities/festivales-musica.xml", 1));

		add(new Section("Living", "https://www.vogue.es/living.xml", 0));
		add(new Section("Novias", "https://www.vogue.es/novias.xml", 0));
		add(new Section("VFNO", "https://www.vogue.es/fashionsnightout.xml", 0));

	}

}
