package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class NationalGeographicEsSections extends Sections {

	public NationalGeographicEsSections()
	{
		super();

		add(new Section("Principal", "https://www.ngenespanol.com/feed/", 0));
		add(new Section("Naturaleza", "https://www.ngenespanol.com/naturaleza/feed/", 0));
		add(new Section("El Mundo", "https://www.ngenespanol.com/el-mundo/feed/", 0));
		add(new Section("El Espacio", "https://www.ngenespanol.com/el-espacio/feed/", 0));
		add(new Section("Ciencia", "https://www.ngenespanol.com/ciencia/feed/", 0));

	}

}
