package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheBolivarCommercialSections extends Sections {

	public TheBolivarCommercialSections()
	{
		super();

		add(new Section("News", "http://www.bolivarcommercial.com/news/?format=feed&type=atom", 0));
		add(new Section("Sports", "http://www.bolivarcommercial.com/sports/?format=feed&type=atom", 0));
		add(new Section("Lifestyles", "http://www.bolivarcommercial.com/news/lifestyles/?format=feed&type=atom", 0));
		add(new Section("Opinion", "http://www.bolivarcommercial.com/news/opinion/?format=feed&type=atom", 0));

	}

}
