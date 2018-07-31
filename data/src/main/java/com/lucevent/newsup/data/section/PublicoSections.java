package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PublicoSections extends Sections {

	public PublicoSections()
	{
		super();

		add(new Section("Portada", "http://www.publico.es/rss/", 0));

	}

}
