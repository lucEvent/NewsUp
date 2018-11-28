package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheJapanTimesSections extends Sections {

	public TheJapanTimesSections()
	{
		super();

		add(new Section("Top stories", "https://www.japantimes.co.jp/feed/topstories/", 0));
		add(new Section("News", "https://www.japantimes.co.jp/news/feed/", 0));
		add(new Section("Life", "https://www.japantimes.co.jp/life/feed/", 0));
		add(new Section("Community", "https://www.japantimes.co.jp/community/feed/", 0));
		add(new Section("Culture", "https://www.japantimes.co.jp/culture/feed", 0));
		add(new Section("Sports", "https://www.japantimes.co.jp/sports/feed/", 0));
		add(new Section("Opinion", "https://www.japantimes.co.jp/opinion/feed/", 0));

	}

}
