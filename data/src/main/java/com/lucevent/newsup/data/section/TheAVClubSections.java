package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheAVClubSections extends Sections {

	public TheAVClubSections()
	{
		super();

		add(new Section("Homepage", "https://www.avclub.com/rss/vip", 0));
		add(new Section("Film", "https://film.avclub.com/rss/vip", 0));
		add(new Section("TV Club", "https://tv.avclub.com/rss/vip", 0));
		add(new Section("Music", "https://music.avclub.com/rss/vip", 0));
		add(new Section("Games", "https://games.avclub.com/rss/vip", 0));
		add(new Section("Aux", "https://aux.avclub.com/rss/vip", 0));
		add(new Section("News", "https://news.avclub.com/rss/vip", 0));

	}

}
