package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class CataloniaTodaySections extends Sections {

	public CataloniaTodaySections()
	{
		super();

		add(new Section("News", "http://www.cataloniatoday.cat/sections/news.feed?type=rss", 0));
		add(new Section("Features", "http://www.cataloniatoday.cat/sections/features.feed?type=rss", 0));
		add(new Section("Catalans abroad", "http://www.cataloniatoday.cat/sections/catalans-abroad.feed?type=rss", 0));
		add(new Section("Food & Wine", "http://www.cataloniatoday.cat/sections/food-wine.feed?type=rss", 0));
		add(new Section("My space", "http://www.cataloniatoday.cat/sections/my-space.feed?type=rss", 0));
		add(new Section("Books", "http://www.cataloniatoday.cat/sections/books.feed?type=rss", 0));
		add(new Section("The Eye", "http://www.cataloniatoday.cat/sections/the-eye.feed?type=rss", 0));
		add(new Section("Interview", "http://www.cataloniatoday.cat/sections/interview.feed?type=rss", 0));
		add(new Section("Europe-World", "http://www.cataloniatoday.cat/sections/europe-world.feed?type=rss", 0));
		add(new Section("Opinion", "http://www.cataloniatoday.cat/opinion.feed?type=rss", 0));
		add(new Section("Articles", "http://www.cataloniatoday.cat/articles.feed?type=rss", 0));

	}

}
