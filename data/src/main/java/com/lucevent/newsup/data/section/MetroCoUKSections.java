package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MetroCoUKSections extends Sections {

	public MetroCoUKSections()
	{
		super();

		add(new Section("Metro UK", "http://metro.co.uk/feed/", 0));

		add(new Section("News", "http://metro.co.uk/news/feed/", 0));
		add(new Section("UK News", "http://metro.co.uk/news/uk/feed/", 1));
		add(new Section("World News", "http://metro.co.uk/news/world/feed/", 1));
		add(new Section("Weird News", "http://metro.co.uk/news/weird/feed/", 1));

		add(new Section("Sport", "http://metro.co.uk/sport/feed/", 0));
		add(new Section("Football", "http://metro.co.uk/sport/football/feed/", 1));
		add(new Section("Cricket", "http://metro.co.uk/sport/cricket/feed/", 1));
		add(new Section("Tennis", "http://metro.co.uk/sport/tennis/feed/", 1));
		add(new Section("F1", "http://metro.co.uk/sport/f1/feed/", 1));
		add(new Section("Golf", "http://metro.co.uk/sport/golf/feed/", 1));
		add(new Section("Boxing", "http://metro.co.uk/sport/boxing/feed/", 1));

		add(new Section("Ents and Tech", "http://metro.co.uk/entertainment/feed/", 0));
		add(new Section("TV", "http://metro.co.uk/entertainment/tv/feed/", 1));
		add(new Section("Film", "http://metro.co.uk/entertainment/film/feed/", 1));
		add(new Section("Music", "http://metro.co.uk/entertainment/music/feed/", 1));
		add(new Section("Gaming", "http://metro.co.uk/entertainment/gaming/feed/", 1));

		add(new Section("Life and Style", "http://metro.co.uk/lifestyle/feed/", 0));
		add(new Section("Fashion", "http://metro.co.uk/lifestyle/fashion/feed/", 1));
		add(new Section("Travel", "http://metro.co.uk/lifestyle/travel/feed/", 1));
		add(new Section("Food", "http://metro.co.uk/lifestyle/food/feed/", 1));
		add(new Section("Books", "http://metro.co.uk/lifestyle/books/feed/", 1));
		add(new Section("Sex", "http://metro.co.uk/lifestyle/sex/feed/", 1));
		add(new Section("Arts", "http://metro.co.uk/lifestyle/arts/feed/", 1));

	}

}
