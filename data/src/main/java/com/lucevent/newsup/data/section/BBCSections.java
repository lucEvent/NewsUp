package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class BBCSections extends Sections {

	public BBCSections()
	{
		super();

		add(new Section("Top stories", "http://feeds.bbci.co.uk/news/rss.xml", 0));

		add(new Section("UK", "http://feeds.bbci.co.uk/news/uk/rss.xml", 0));
		add(new Section("England", "http://feeds.bbci.co.uk/news/england/rss.xml", 1));
		add(new Section("Northern Ireland", "http://feeds.bbci.co.uk/news/northern_ireland/rss.xml", 1));
		add(new Section("Scotland", "http://feeds.bbci.co.uk/news/scotland/rss.xml", 1));
		add(new Section("Wales", "http://feeds.bbci.co.uk/news/wales/rss.xml", 1));

		add(new Section("World News ", "http://feeds.bbci.co.uk/news/world/rss.xml", 0));
		add(new Section("Africa", "http://feeds.bbci.co.uk/news/world/africa/rss.xml", 1));
		add(new Section("Asia", "http://feeds.bbci.co.uk/news/world/asia/rss.xml", 1));
		add(new Section("Europe", "http://feeds.bbci.co.uk/news/world/europe/rss.xml", 1));
		add(new Section("Latin America", "http://feeds.bbci.co.uk/news/world/latin_america/rss.xml", 1));
		add(new Section("Middle east", "http://feeds.bbci.co.uk/news/world/middle_east/rss.xml", 1));
		add(new Section("US & Canada", "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml", 1));

		add(new Section("Business", "http://feeds.bbci.co.uk/news/business/rss.xml", 0));
		add(new Section("Politics", "http://feeds.bbci.co.uk/news/politics/rss.xml", 0));
		add(new Section("Health", "http://feeds.bbci.co.uk/news/health/rss.xml", 0));
		add(new Section("Education & Family", "http://feeds.bbci.co.uk/news/education/rss.xml", 0));
		add(new Section("Science & Environment", "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml", 0));
		add(new Section("Technology", "http://feeds.bbci.co.uk/news/technology/rss.xml", 0));
		add(new Section("Entertainment & Arts", "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml", 0));

		add(new Section("Sports", "http://feeds.bbci.co.uk/sport/rss.xml?edition=uk", 0));
		add(new Section("Football", "http://feeds.bbci.co.uk/sport/football/rss.xml?edition=uk", 1));
		add(new Section("Cricket", "http://feeds.bbci.co.uk/sport/cricket/rss.xml?edition=uk", 1));
		add(new Section("Rugby Union", "http://feeds.bbci.co.uk/sport/rugby-union/rss.xml?edition=uk", 1));
		add(new Section("Rugby League", "http://feeds.bbci.co.uk/sport/rugby-league/rss.xml?edition=uk", 1));
		add(new Section("Tennis", "http://feeds.bbci.co.uk/sport/tennis/rss.xml?edition=uk", 1));
		add(new Section("Golf", "http://feeds.bbci.co.uk/sport/golf/rss.xml?edition=uk", 1));
		add(new Section("Snooker", "http://feeds.bbci.co.uk/sport/snooker/rss.xml?edition=uk", 1));

		add(new Section("Magazine", "http://feeds.bbci.co.uk/news/stories/rss.xml", 0));
		add(new Section("In pictures", "http://feeds.bbci.co.uk/news/in_pictures/rss.xml", 0));
		add(new Section("Have your say", "http://feeds.bbci.co.uk/news/have_your_say/rss.xml", 0));

	}

}
