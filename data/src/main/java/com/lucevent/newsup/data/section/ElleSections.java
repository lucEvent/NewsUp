package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElleSections extends Sections {

	public ElleSections()
	{
		super();

		add(new Section("Todo", "https://www.elle.com/es/rss/all.xml/", 0));
		add(new Section("Moda", "https://www.elle.com/es/rss/moda.xml/", 0));
		add(new Section("Belleza", "https://www.elle.com/es/rss/belleza.xml/", 0));
		add(new Section("Star Style", "https://www.elle.com/es/rss/star-style.xml/", 0));
		add(new Section("Living", "https://www.elle.com/es/rss/living.xml/", 0));
		add(new Section("Extra Elle", "https://www.elle.com/es/rss/extra-elle.xml/", 0));

		add(new Section("Blogs", null, -1));
		add(new Section("Ana Albadalejo", "http://anaalbadalejo.blogs.elle.es/feed/", 1));
		add(new Section("Ana Fernandez", "http://ana-fernandez.blogs.elle.es/feed/", 1));
		add(new Section("Art manias", "http://art-manias.blogs.elle.es/feed/", 1));
		add(new Section("Boda a la vista", "http://boda-a-la-vista.blogs.elle.es/feed/", 1));
		add(new Section("Diario de estilo", "http://diario-de-estilo.blogs.elle.es/feed/", 1));
		add(new Section("Diario de una estudiante", "http://diario-de-una-estudiante.blogs.elle.es/feed/", 1));
		add(new Section("Honeydressing", "http://honeydressing.blogs.elle.es/feed/", 1));
		add(new Section("Mammamia", "http://mammamia.blogs.elle.es/feed/", 1));
		add(new Section("Paula Echevarria", "http://paula-echevarria.blogs.elle.es/feed/", 1));
		add(new Section("Raquel del rosario", "http://raquel-del-rosario.blogs.elle.es/feed/", 1));
		add(new Section("Sara Carbonero", "http://sara-carbonero.blogs.elle.es/feed/", 1));
		add(new Section("The eye of the Iger", "http://the-eye-of-the-iger.blogs.elle.es/feed/", 1));
		add(new Section("The list", "http://the-list.blogs.elle.es/feed/", 1));
		add(new Section("Time for fashion", "http://time-for-fashion.blogs.elle.es/feed/", 1));
		add(new Section("Twist and shot", "http://twistandshot.blogs.elle.es/feed/", 1));
		add(new Section("Vicky Berrocal", "http://vicky-berrocal.blogs.elle.es/feed/", 1));

	}

}
