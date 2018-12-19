package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GoteborgsPostenSections extends Sections {

	public GoteborgsPostenSections()
	{
		super();

		add(new Section("Start", "http://www.gp.se/?rss", 0));
		add(new Section("Nyheter", "http://www.gp.se/nyheter?rss", 1));
		add(new Section("Sverige", "http://www.gp.se/nyheter/sverige?rss", 1));
		add(new Section("G\u00F6teborg", "http://www.gp.se/nyheter/g%C3%B6teborg?rss", 1));
		add(new Section("V\u00E4stsverige", "http://www.gp.se/nyheter/v%C3%A4stsverige?rss", 1));
		add(new Section("V\u00E4rlden", "http://www.gp.se/nyheter/v%C3%A4rlden?rss", 1));
		add(new Section("Ekonomi", "http://www.gp.se/ekonomi?rss", 1));
		add(new Section("Debatt", "http://www.gp.se/debatt?rss", 1));

		add(new Section("Sport", "http://www.gp.se/sport?rss", 0));
		add(new Section("Fotboll", "http://www.gp.se/sport/fotboll?rss", 1));
		add(new Section("Ishockey", "http://www.gp.se/sport/ishockey?rss", 1));
		add(new Section("Handboll", "http://www.gp.se/sport/handboll?rss", 1));
		add(new Section("Rylander", "http://blogg.gp.se/rylanderjohan/feed/", 1));

		add(new Section("Kultur", "http://www.gp.se/kultur?rss", 0));
		add(new Section("Musik", "http://www.gp.se/kultur/musik?rss", 1));
		add(new Section("Film", "http://www.gp.se/kultur/film?rss", 1));
		add(new Section("Spel", "http://www.gp.se/kultur/spel?rss", 1));

		add(new Section("Ledare", "http://www.gp.se/ledare?rss", 0));

		add(new Section("Livsstil", "http://www.gp.se/livsstil?rss", 0));
		add(new Section("H\u00E4lsa", "http://www.gp.se/livsstil/h%C3%A4lsa?rss", 1));
		add(new Section("Resor", "http://www.gp.se/livsstil/resor?rss", 1));
		add(new Section("Mat", "http://www.gp.se/livsstil/mat?rss", 1));
		add(new Section("Dryck", "http://www.gp.se/livsstil/dryck?rss", 1));
		add(new Section("Konsument", "http://www.gp.se/livsstil/konsument?rss", 1));
		add(new Section("V\u00E4rldens G\u00E5ng", "http://www.gp.se/livsstil/v%C3%A4rldens-g%C3%A5ng?rss", 1));

	}

}
