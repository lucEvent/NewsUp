package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class OttawaCitizenSections extends Sections {

	public OttawaCitizenSections()
	{
		super();

		add(new Section("Homepage", "https://ottawacitizen.com/feed/", 0));

		add(new Section("News", "https://ottawacitizen.com/category/news/feed", 0));
		add(new Section("Politics", "https://ottawacitizen.com/category/news/politics/feed", 1));
		add(new Section("Defence Watch", "https://ottawacitizen.com/category/news/national/defence-watch/feed", 1));
		add(new Section("National", "https://ottawacitizen.com/category/news/national/feed", 1));
		add(new Section("World", "https://ottawacitizen.com/category/news/world/feed", 1));

		add(new Section("Opinion", "https://ottawacitizen.com/category/opinion/feed", 0));
		add(new Section("Editorials", "https://ottawacitizen.com/category/opinion/editorials/feed", 1));
		add(new Section("Letters", "https://ottawacitizen.com/category/opinion/letters/feed", 1));
		add(new Section("Columnists", "https://ottawacitizen.com/category/opinion/columnists/feed", 1));

		add(new Section("Business", "https://ottawacitizen.com/category/business/feed", 0));
		add(new Section("Small Business", "https://ottawacitizen.com/category/business/small-business/feed", 1));

		add(new Section("Technology", "https://ottawacitizen.com/category/technology/feed", 0));

		add(new Section("Arts", "https://ottawacitizen.com/category/entertainment/feed", 0));
		add(new Section("Local Arts", "https://ottawacitizen.com/category/entertainment/local-arts/feed", 1));
		add(new Section("Movies", "https://ottawacitizen.com/category/entertainment/movies/feed", 1));
		add(new Section("Music", "https://ottawacitizen.com/category/entertainment/music/feed", 1));
		add(new Section("Books", "https://ottawacitizen.com/category/entertainment/books/feed", 1));
		add(new Section("Celebrity", "https://ottawacitizen.com/category/entertainment/celebrity/feed", 1));
		add(new Section("Cappies", "https://ottawacitizen.com/category/entertainment/cappies/feed", 1));

		add(new Section("Sports", "https://ottawacitizen.com/category/sports/feed", 0));
		add(new Section("Local Sports", "https://ottawacitizen.com/category/sports/local-sports/feed", 1));
		add(new Section("Hockey", "https://ottawacitizen.com/category/sports/hockey/feed", 1));
		add(new Section("Football", "https://ottawacitizen.com/category/sports/football/feed", 1));
		add(new Section("Baseball", "https://ottawacitizen.com/category/sports/baseball/feed", 1));
		add(new Section("Golf", "https://ottawacitizen.com/category/sports/golf/feed", 1));
		add(new Section("Tennis", "https://ottawacitizen.com/category/sports/tennis/feed", 1));

		add(new Section("Life", "https://ottawacitizen.com/category/life/feed", 0));
		add(new Section("Homes", "https://ottawacitizen.com/category/life/homes/feed", 1));
		add(new Section("Food", "https://ottawacitizen.com/category/life/food/feed", 1));
		add(new Section("Life Story", "https://ottawacitizen.com/category/life/life-story/feed", 1));

		add(new Section("Health", "https://ottawacitizen.com/category/health/feed", 0));
		add(new Section("Travel", "https://ottawacitizen.com/category/travel/feed", 0));

	}

}
