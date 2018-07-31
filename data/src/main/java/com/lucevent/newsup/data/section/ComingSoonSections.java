package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ComingSoonSections extends Sections {

	public ComingSoonSections()
	{
		super();

		add(new Section("Homepage", "http://www.comingsoon.net/feed", 0));
		add(new Section("Movies", "http://www.comingsoon.net/category/movies/feed", 0));
		add(new Section("TV", "http://www.comingsoon.net/category/tv/feed", 0));
		add(new Section("Games", "http://www.comingsoon.net/category/games/feed", 0));
		add(new Section("DVD", "http://www.comingsoon.net/category/dvd/feed", 0));
		add(new Section("Horror", "http://www.comingsoon.net/category/horror/feed", 0));

	}

}
