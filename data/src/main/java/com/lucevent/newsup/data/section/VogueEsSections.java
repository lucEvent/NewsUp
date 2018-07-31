package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VogueEsSections extends Sections {

	public VogueEsSections()
	{
		super();

		add(new Section("Portada", "http://www.vogue.es/home.xml", 0));

		add(new Section("Moda", "http://www.vogue.es/moda.xml", 0));
		add(new Section("News", "http://www.vogue.es/moda/news.xml", 1));
		add(new Section("Trends", "http://www.vogue.es/moda/tendencias.xml", 1));
		add(new Section("Streetstyle", "http://www.vogue.es/moda/streetstyle.xml", 1));
		add(new Section("7 d\u00EDas, 7 looks", "http://www.vogue.es/moda/tendencias/7-dias-7-looks.xml", 1));
		add(new Section("En el estudio de...", "http://www.vogue.es/moda/tendencias/en-el-estudio-de.xml", 1));

		add(new Section("Suzy Menkes", "http://www.vogue.es/suzy-menkes.xml", 0));

		add(new Section("Belleza", "http://www.vogue.es/belleza.xml", 0));
		add(new Section("Pelo", "http://www.vogue.es/belleza/pelo.xml", 1));
		add(new Section("Nutrici\u00F3n", "http://www.vogue.es/belleza/nutricion-dietas.xml", 1));
		add(new Section("Maquillaje", "http://www.vogue.es/belleza/maquillaje.xml", 1));
		add(new Section("Fitness", "http://www.vogue.es/belleza/fitness.xml", 1));
		add(new Section("Tendencias", "http://www.vogue.es/belleza/tendencias.xml", 1));
		add(new Section("Bienestar", "http://www.vogue.es/belleza/bienestar.xml", 1));

		add(new Section("Celebrities", "http://www.vogue.es/celebrities.xml", 0));
		add(new Section("Cannes", "http://www.vogue.es/celebrities/cannes.xml", 1));
		add(new Section("Gala Met", "http://www.vogue.es/celebrities/gala-met.xml", 1));
		add(new Section("Oscar", "http://www.vogue.es/celebrities/oscar.xml", 1));
		add(new Section("Festivales", "http://www.vogue.es/celebrities/festivales-musica.xml", 1));

		add(new Section("Living", "http://www.vogue.es/living.xml", 0));
		add(new Section("Novias", "http://www.vogue.es/novias.xml", 0));
		add(new Section("VFNO", "http://www.vogue.es/fashionsnightout.xml", 0));

	}

}
