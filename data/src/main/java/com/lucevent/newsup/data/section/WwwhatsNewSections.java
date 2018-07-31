package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class WwwhatsNewSections extends Sections {

	public WwwhatsNewSections()
	{
		super();

		add(new Section("Principal", "https://wwwhatsnew.com/feed", 0));

		add(new Section("Comunicaci\u00F3n", "https://wwwhatsnew.com/category/comunicacion/feed/", 0));
		add(new Section("Internet", "https://wwwhatsnew.com/category/internet/feed", 0));
		add(new Section("Marketing", "https://wwwhatsnew.com/category/campanas-de-marketing/feed", 0));
		add(new Section("Multimedia", "https://wwwhatsnew.com/category/multimedia/feed", 0));
		add(new Section("Ocio", "https://wwwhatsnew.com/category/ocio/feed", 0));
		add(new Section("Productividad", "https://wwwhatsnew.com/category/productividad/feed", 0));
		add(new Section("Programaci\u00F3n y Dise\u00F1o", "https://wwwhatsnew.com/category/programacion-y-diseno/feed", 0));
		add(new Section("Web Social", "https://wwwhatsnew.com/category/web-social/feed", 0));
		add(new Section("Proyectos Educativos", "https://wwwhatsnew.com/category/proyectos-educativos/feed", 0));

	}

}
