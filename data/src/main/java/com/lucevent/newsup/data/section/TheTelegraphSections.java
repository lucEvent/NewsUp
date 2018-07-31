package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheTelegraphSections extends Sections {

	public TheTelegraphSections()
	{
		super();

		add(new Section("News", "https://www.telegraph.co.uk/news/rss.xml", 0));
		add(new Section("Science", "https://www.telegraph.co.uk/science/rss.xml", 0));
		add(new Section("Business", "https://www.telegraph.co.uk/business/rss.xml", 0));
		add(new Section("Property", "https://www.telegraph.co.uk/property/rss.xml", 0));
		add(new Section("Technology", "https://www.telegraph.co.uk/technology/rss.xml", 0));
		add(new Section("Cars", "https://www.telegraph.co.uk/cars/rss.xml", 0));
		add(new Section("Travel", "https://www.telegraph.co.uk/travel/rss.xml", 0));

		add(new Section("Sport", "https://www.telegraph.co.uk/sport/rss.xml", 0));
		add(new Section("Football", "https://www.telegraph.co.uk/football/rss.xml", 1));
		add(new Section("Cricket", "https://www.telegraph.co.uk/cricket/rss.xml", 1));

		add(new Section("Culture", null, -1));
		add(new Section("Art", "https://www.telegraph.co.uk/art/rss.xml", 1));
		add(new Section("Books", "https://www.telegraph.co.uk/books/rss.xml", 1));
		add(new Section("Music", "https://www.telegraph.co.uk/music/rss.xml", 1));
		add(new Section("TV", "https://www.telegraph.co.uk/tv/rss.xml", 1));
		add(new Section("Radio", "https://www.telegraph.co.uk/radio/rss.xml", 1));

		add(new Section("Food and Drink", "https://www.telegraph.co.uk/food-and-drink/rss.xml", 0));

	}

}
