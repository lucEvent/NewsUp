package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheBolivarCommercialSections extends Sections {

	public TheBolivarCommercialSections()
	{
		super();

		add(new Section("Homepage", "https://bolivarcommercial.com/feed/", 0));
		add(new Section("News", "https://bolivarcommercial.com/category/news/feed/", 0));
		add(new Section("Schools", "https://bolivarcommercial.com/category/schools/feed/", 0));
		add(new Section("Business", "https://bolivarcommercial.com/category/business/feed/", 0));
		add(new Section("Lifestyles", "https://bolivarcommercial.com/category/lifestyles/feed/", 0));
		add(new Section("Opinion", "https://bolivarcommercial.com/category/opinion/feed/", 0));
		add(new Section("Sports", "https://bolivarcommercial.com/category/sports/feed/", 0));

	}

}
