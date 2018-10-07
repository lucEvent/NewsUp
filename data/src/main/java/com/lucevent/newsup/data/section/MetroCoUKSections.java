package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MetroCoUKSections extends Sections {

	public MetroCoUKSections()
	{
		super();

		add(new Section("Metro UK", "https://metro.co.uk/feed/", 0));

		add(new Section("News", "https://metro.co.uk/news/feed/", 0));
		add(new Section("UK News", "https://metro.co.uk/news/uk/feed/", 1));
		add(new Section("World News", "https://metro.co.uk/news/world/feed/", 1));
		add(new Section("Weird News", "https://metro.co.uk/news/weird/feed/", 1));

		add(new Section("Sport", "https://metro.co.uk/sport/feed/", 0));
		add(new Section("Football", "https://metro.co.uk/sport/football/feed/", 1));
		add(new Section("Cricket", "https://metro.co.uk/sport/cricket/feed/", 1));
		add(new Section("Tennis", "https://metro.co.uk/sport/tennis/feed/", 1));
		add(new Section("F1", "https://metro.co.uk/sport/f1/feed/", 1));
		add(new Section("Golf", "https://metro.co.uk/sport/golf/feed/", 1));
		add(new Section("Boxing", "https://metro.co.uk/sport/boxing/feed/", 1));

		add(new Section("Ents and Tech", "https://metro.co.uk/entertainment/feed/", 0));
		add(new Section("TV", "https://metro.co.uk/entertainment/tv/feed/", 1));
		add(new Section("Film", "https://metro.co.uk/entertainment/film/feed/", 1));
		add(new Section("Music", "https://metro.co.uk/entertainment/music/feed/", 1));
		add(new Section("Gaming", "https://metro.co.uk/entertainment/gaming/feed/", 1));

		add(new Section("Life and Style", "https://metro.co.uk/lifestyle/feed/", 0));
		add(new Section("Fashion", "https://metro.co.uk/lifestyle/fashion/feed/", 1));
		add(new Section("Travel", "https://metro.co.uk/lifestyle/travel/feed/", 1));
		add(new Section("Food", "https://metro.co.uk/lifestyle/food/feed/", 1));
		add(new Section("Books", "https://metro.co.uk/lifestyle/books/feed/", 1));
		add(new Section("Sex", "https://metro.co.uk/lifestyle/sex/feed/", 1));
		add(new Section("Arts", "https://metro.co.uk/lifestyle/arts/feed/", 1));

	}

}
