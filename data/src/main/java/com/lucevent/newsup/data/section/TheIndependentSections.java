package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheIndependentSections extends Sections {

	public TheIndependentSections()
	{
		super();

		add(new Section("Homepage", "http://www.independent.co.uk/rss", 0));

		add(new Section("News", "http://www.independent.co.uk/news/rss", 0));
		add(new Section("UK", "http://www.independent.co.uk/news/uk/rss", 1));
		add(new Section("US", "http://www.independent.co.uk/news/world/americas/rss", 1));
		add(new Section("World", "http://www.independent.co.uk/news/world/rss", 1));
		add(new Section("Politics", "http://www.independent.co.uk/news/uk/politics/rss", 1));
		add(new Section("Business", "http://www.independent.co.uk/news/business/rss", 1));

		add(new Section("Indy/Life", "http://www.independent.co.uk/life-style/rss", 0));
		add(new Section("Travel", "http://www.independent.co.uk/travel/rss", 1));
		add(new Section("Food", "http://www.independent.co.uk/life-style/food-and-drink/rss", 1));

		add(new Section("Sport", "http://www.independent.co.uk/sport/rss", 0));
		add(new Section("Football", "http://www.independent.co.uk/sport/football/rss", 1));
		add(new Section("Rugby", "http://www.independent.co.uk/sport/rugby/rss", 1));

		add(new Section("Tech", "http://www.independent.co.uk/life-style/gadgets-and-tech/rss", 0));
		add(new Section("Tech news", "http://www.independent.co.uk/life-style/gadgets-and-tech/news/rss", 1));

		add(new Section("Culture", "http://www.independent.co.uk/arts-entertainment/rss", 0));
		add(new Section("TV + radio", "http://www.independent.co.uk/arts-entertainment/tv/rss", 1));
		add(new Section("Film", "http://www.independent.co.uk/arts-entertainment/films/rss", 1));
		add(new Section("Music", "http://www.independent.co.uk/arts-entertainment/music/rss", 1));

		add(new Section("Brexit", "http://www.independent.co.uk/topic/brexit/rss", 0));
		add(new Section("Voices", "http://www.independent.co.uk/voices/rss", 0));

	}

}
