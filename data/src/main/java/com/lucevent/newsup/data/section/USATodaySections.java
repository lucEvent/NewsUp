package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class USATodaySections extends Sections {

	public USATodaySections()
	{
		super();

		add(new Section("Headlines", "http://rssfeeds.usatoday.com/usatoday-newstopstories&x=1", 0));
		add(new Section("National", "http://rssfeeds.usatoday.com/UsatodaycomNation-TopStories&x=1", 0));
		add(new Section("Washington", "http://rssfeeds.usatoday.com/UsatodaycomWashington-TopStories&x=1", 0));
		add(new Section("World", "http://rssfeeds.usatoday.com/UsatodaycomWorld-TopStories&x=1", 0));
		add(new Section("Opinion", "http://rssfeeds.usatoday.com/News-Opinion&x=1", 0));

		add(new Section("Sports", "http://rssfeeds.usatoday.com/UsatodaycomSports-TopStories&x=1", 0));
		add(new Section("NFL", "http://rssfeeds.usatoday.com/UsatodaycomNfl-TopStories&x=1", 1));
		add(new Section("NCAAF", "http://rssfeeds.usatoday.com/UsatodaycomCollegeFootball-TopStories&x=1", 1));
		add(new Section("NBA", "http://rssfeeds.usatoday.com/UsatodaycomNba-TopStories&x=1", 1));
		add(new Section("Golf", "http://rssfeeds.usatoday.com/UsatodaycomGolf-TopStories&x=1", 1));
		add(new Section("Fantasy", "http://rssfeeds.usatoday.com/topfantasy&x=1", 1));
		add(new Section("NHL", "http://rssfeeds.usatoday.com/UsatodaycomNhl-TopStories&x=1", 1));
		add(new Section("NCAAB", "http://rssfeeds.usatoday.com/UsatodaycomCollegeMensBasketball-TopStories&x=1", 1));
		add(new Section("Motor Sports", "http://rssfeeds.usatoday.com/topmotorsports&x=1", 1));
		add(new Section("Tennis", "http://rssfeeds.usatoday.com/UsatodayTennis-TopStories&x=1", 1));
		add(new Section("Cycling", "http://rssfeeds.usatoday.com/UsatodayCycling-TopStories&x=1", 1));
		add(new Section("Olympics", "http://rssfeeds.usatoday.com/UsatodaycomOlympicsCoverage-TopStories&x=1", 1));

		add(new Section("Entertainment", "http://rssfeeds.usatoday.com/usatoday-LifeTopStories&x=1", 0));
		add(new Section("People", "http://rssfeeds.usatoday.com/toppeople&x=1", 1));
		add(new Section("Movies", "http://rssfeeds.usatoday.com/UsatodaycomMovies-TopStories&x=1", 1));
		add(new Section("Music", "http://rssfeeds.usatoday.com/UsatodaycomMusic-TopStories&x=1", 1));
		add(new Section("TV", "http://rssfeeds.usatoday.com/UsatodaycomTelevision-TopStories&x=1", 1));
		add(new Section("Books", "http://rssfeeds.usatoday.com/UsatodaycomBooks-TopStories&x=1", 1));

		add(new Section("Money", "http://rssfeeds.usatoday.com/UsatodaycomMoney-TopStories&x=1", 0));

		add(new Section("Tech", "http://rssfeeds.usatoday.com/usatoday-TechTopStories&x=1", 0));
		add(new Section("Personal Tech", "http://rssfeeds.usatoday.com/UsatodaycomTech-PersonalTalk&x=1", 1));
		add(new Section("Gaming", "http://rssfeeds.usatoday.com/topgaming&x=1", 1));

		add(new Section("Travel", "http://rssfeeds.usatoday.com/UsatodaycomTravel-TopStories&x=1", 0));
		add(new Section("Destinations", "http://rssfeeds.usatoday.com/UsatodayTravel-Destinations&x=1", 1));
		add(new Section("Flights", "http://rssfeeds.usatoday.com/UsatodayTravel-Flights&x=1", 1));
		add(new Section("Hotels", "http://rssfeeds.usatoday.com/UsatodayTravel-Hotels&x=1", 1));

	}

}
