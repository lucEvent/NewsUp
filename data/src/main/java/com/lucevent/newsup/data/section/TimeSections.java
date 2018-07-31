package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TimeSections extends Sections {

	public TimeSections()
	{
		super();

		add(new Section("News", "http://time.com/newsfeed/feed/", 0));
		add(new Section("U.S.", "http://time.com/us/feed/", 0));
		add(new Section("Politics", "http://time.com/politics/feed/", 0));
		add(new Section("World", "http://time.com/world/feed/", 0));
		add(new Section("Business", "http://time.com/business/feed/", 0));
		add(new Section("Technology", "http://time.com/tech/feed/", 0));
		add(new Section("Health", "http://time.com/health/feed/", 0));
		add(new Section("Motto", "http://time.com/motto/feed/", 0));
		add(new Section("Entertainment", "http://time.com/entertainment/feed/", 0));
		add(new Section("Science", "http://time.com/science/feed/", 0));
		add(new Section("Living", "http://time.com/living/feed/", 0));
		add(new Section("Sports", "http://time.com/sports/feed/", 0));
		add(new Section("History", "http://time.com/history/feed/", 0));
		add(new Section("Ideas", "http://time.com/ideas/feed/", 0));
		add(new Section("Opinion", "http://time.com/opinion/feed/", 0));

		add(new Section("Photos", null, -1));
		add(new Section("LightBox", "http://time.com/lightbox/feed/", 1));
		add(new Section("LIFE.com", "http://time.com/life/feed/", 1));

		add(new Section("Blogs", null, -1));
		add(new Section("Swampland", "http://feeds.feedburner.com/timeblogs/swampland", 1));
		add(new Section("Nerd World", "http://feeds.feedburner.com/timeblogs/nerd_world", 1));

	}

}
