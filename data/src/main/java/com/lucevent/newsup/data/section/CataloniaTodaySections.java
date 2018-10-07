package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class CataloniaTodaySections extends Sections {

	public CataloniaTodaySections()
	{
		super();

		add(new Section("News", "https://www.cataloniatoday.cat/sections/news.feed?type=rss", 0));
		add(new Section("Features", "https://www.cataloniatoday.cat/sections/features.feed?type=rss", 0));
		add(new Section("Catalans abroad", "https://www.cataloniatoday.cat/sections/catalans-abroad.feed?type=rss", 0));
		add(new Section("Food & Wine", "https://www.cataloniatoday.cat/sections/food-wine.feed?type=rss", 0));
		add(new Section("My space", "https://www.cataloniatoday.cat/sections/my-space.feed?type=rss", 0));
		add(new Section("Books", "https://www.cataloniatoday.cat/sections/books.feed?type=rss", 0));
		add(new Section("The Eye", "https://www.cataloniatoday.cat/sections/the-eye.feed?type=rss", 0));
		add(new Section("Interview", "https://www.cataloniatoday.cat/sections/interview.feed?type=rss", 0));
		add(new Section("Europe-World", "https://www.cataloniatoday.cat/sections/europe-world.feed?type=rss", 0));
		add(new Section("Opinion", "https://www.cataloniatoday.cat/opinion.feed?type=rss", 0));
		add(new Section("Articles", "https://www.cataloniatoday.cat/articles.feed?type=rss", 0));

	}

}
