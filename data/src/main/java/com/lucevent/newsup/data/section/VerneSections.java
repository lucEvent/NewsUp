package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VerneSections extends Sections {

	public VerneSections()
	{
		super();

		add(new Section("Espa\u00F1a", "https://verne.elpais.com/rss/verne/portada.xml", 0));
		add(new Section("M\u00E9xico", "https://verne.elpais.com/rss/verne/portada_mexico.xml", 0));
	}

}
