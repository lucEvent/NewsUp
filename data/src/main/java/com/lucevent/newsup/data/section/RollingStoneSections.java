package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class RollingStoneSections extends Sections {

	public RollingStoneSections()
	{
		super();

		add(new Section("Main", "https://www.rollingstone.com/feed/", 0));

		add(new Section("Music", "https://www.rollingstone.com/music/feed/", 0));
		add(new Section("Album Reviews", "https://www.rollingstone.com/music/music-album-reviews/feed/", 1));
		add(new Section("Live Reviews", "https://www.rollingstone.com/music/music-live-reviews/feed/", 1));
		add(new Section("Country Music", "https://www.rollingstone.com/music/music-country/feed/", 1));

		add(new Section("TV", "https://www.rollingstone.com/tv/feed/", 0));
		add(new Section("Recaps", "https://www.rollingstone.com/tv/tv-recaps/feed/", 1));
		add(new Section("TV Reviews", "https://www.rollingstone.com/tv/tv-reviews/feed/", 1));

		add(new Section("Movies", "https://www.rollingstone.com/movies/feed/", 0));
		add(new Section("Movie Reviews", "https://www.rollingstone.com/movies/movie-reviews/feed/", 1));

		add(new Section("Politics", "https://www.rollingstone.com/politics/feed/", 0));

		add(new Section("Culture", "https://www.rollingstone.com/culture/feed/", 0));
		add(new Section("Sports", "https://www.rollingstone.com/culture/culture-sports/feed/", 1));

	}

}
