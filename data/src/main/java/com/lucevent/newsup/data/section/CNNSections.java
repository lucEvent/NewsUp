package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class CNNSections extends Sections {

	public CNNSections()
	{
		super();

		add(new Section("Top Stories", "http://rss.cnn.com/rss/edition.rss", 0));
		add(new Section("Most Recent", "http://rss.cnn.com/rss/cnn_latest.rss", 0));
		add(new Section("World", "http://rss.cnn.com/rss/edition_world.rss", 0));
		add(new Section("Africa", "http://rss.cnn.com/rss/edition_africa.rss", 1));
		add(new Section("Americas", "http://rss.cnn.com/rss/edition_americas.rss", 1));
		add(new Section("Asia", "http://rss.cnn.com/rss/edition_asia.rss", 1));
		add(new Section("Europe", "http://rss.cnn.com/rss/edition_europe.rss", 1));
		add(new Section("Middle East", "http://rss.cnn.com/rss/edition_meast.rss", 1));
		add(new Section("U.S.", "http://rss.cnn.com/rss/edition_us.rss", 1));

		add(new Section("Entertainment", "http://rss.cnn.com/rss/edition_entertainment.rss", 0));
		add(new Section("Politics", "http://rss.cnn.com/rss/cnn_allpolitics.rss", 0));
		add(new Section("Health", "http://rss.cnn.com/rss/cnn_health.rss", 0));
		add(new Section("Travel", "http://rss.cnn.com/rss/edition_travel.rss", 0));
		add(new Section("Living", "http://rss.cnn.com/rss/cnn_living.rss", 0));

		add(new Section("World Sport", "http://rss.cnn.com/rss/edition_sport.rss", 0));
		add(new Section("Football", "http://rss.cnn.com/rss/edition_football.rss", 1));
		add(new Section("Golf", "http://rss.cnn.com/rss/edition_golf.rss", 1));
		add(new Section("Motorsport", "http://rss.cnn.com/rss/edition_motorsport.rss", 1));
		add(new Section("Tennis", "http://rss.cnn.com/rss/edition_tennis.rss", 1));

		add(new Section("Student News", "http://rss.cnn.com/services/podcasting/studentnews/rss.xml", 0));

	}

}