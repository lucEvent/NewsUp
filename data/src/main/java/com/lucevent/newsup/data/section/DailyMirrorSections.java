package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DailyMirrorSections extends Sections {

	public DailyMirrorSections()
	{
		super();

		add(new Section("Homepage", "https://www.mirror.co.uk/?service=rss", 0));

		add(new Section("News", "https://www.mirror.co.uk/news/?service=rss", 0));
		add(new Section("UK News", "https://www.mirror.co.uk/news/uk-news/?service=rss", 1));
		add(new Section("World News", "https://www.mirror.co.uk/news/world-news/?service=rss", 1));
		add(new Section("Weird News", "https://www.mirror.co.uk/news/weird-news/?service=rss", 1));
		add(new Section("Real Life Stories", "https://www.mirror.co.uk/news/real-life-stories/?service=rss", 1));
		add(new Section("Science", "https://www.mirror.co.uk/science/?service=rss", 1));
		add(new Section("Health", "https://www.mirror.co.uk/lifestyle/health/?service=rss", 1));
		add(new Section("Motoring", "https://www.mirror.co.uk/lifestyle/motoring/?service=rss", 1));

		add(new Section("Politics", "https://www.mirror.co.uk/news/politics/?service=rss", 0));

		add(new Section("Sport", "https://www.mirror.co.uk/sport/?service=rss", 0));
		add(new Section("Football", "https://www.mirror.co.uk/sport/football/?service=rss", 1));
		add(new Section("Boxing", "https://www.mirror.co.uk/sport/boxing/?service=rss", 1));
		add(new Section("WWE", "https://www.mirror.co.uk/sport/other-sports/wrestling/?service=rss", 1));
		add(new Section("F1", "https://www.mirror.co.uk/sport/formula-1/?service=rss", 1));
		add(new Section("Racing", "https://www.mirror.co.uk/sport/horse-racing/?service=rss", 1));
		add(new Section("Cricket", "https://www.mirror.co.uk/sport/cricket/?service=rss", 1));
		add(new Section("Rugby Union", "https://www.mirror.co.uk/sport/rugby-union/?service=rss", 1));
		add(new Section("Rugby League", "https://www.mirror.co.uk/sport/rugby-league/?service=rss", 1));
		add(new Section("Golf", "https://www.mirror.co.uk/sport/golf/?service=rss", 1));
		add(new Section("Athletics", "https://www.mirror.co.uk/sport/other-sports/athletics/?service=rss", 1));
		add(new Section("Other Sports", "https://www.mirror.co.uk/sport/other-sports/?service=rss", 1));

		add(new Section("Celebs", "https://www.mirror.co.uk/3am/?service=rss", 0));
		add(new Section("TV & Film", "https://www.mirror.co.uk/tv/?service=rss", 0));
		add(new Section("Tech", "https://www.mirror.co.uk/tech/?service=rss", 0));
		add(new Section("Money", "https://www.mirror.co.uk/money/?service=rss", 0));

		add(new Section("Travel", "https://www.mirror.co.uk/travel/?service=rss", 0));
		add(new Section("Cheap Flights", "https://www.mirror.co.uk/travel/cheap-flights/?service=rss", 1));
		add(new Section("Cruises", "https://www.mirror.co.uk/travel/cruises/?service=rss", 1));
		add(new Section("Europe", "https://www.mirror.co.uk/travel/europe/?service=rss", 1));
		add(new Section("Travel News", "https://www.mirror.co.uk/travel/news/?service=rss", 1));
		add(new Section("UK & Ireland", "https://www.mirror.co.uk/travel/uk-ireland/?service=rss", 1));
		add(new Section("USA & Canada", "https://www.mirror.co.uk/travel/usa-canada/?service=rss", 1));

		add(new Section("Fashion", "https://www.mirror.co.uk/3am/style/?service=rss", 0));
		add(new Section("Mums", "https://www.mirror.co.uk/lifestyle/family/?service=rss", 0));

	}

}
