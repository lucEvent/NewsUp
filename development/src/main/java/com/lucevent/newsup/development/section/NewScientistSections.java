package com.lucevent.newsup.development.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class NewScientistSections extends Sections {

	public NewScientistSections()
	{
		super();

		add(new Section("All articles", "http://feeds.newscientist.com/", 0));
		add(new Section("News", "http://feeds.newscientist.com/science-news", 0));
		add(new Section("Features", "http://feeds.newscientist.com/features", 0));
		add(new Section("Physics", "http://feeds.newscientist.com/physics-math", 0));
		add(new Section("Technology", "http://feeds.newscientist.com/tech", 0));
		add(new Section("Space", "http://feeds.newscientist.com/space", 0));
		add(new Section("Life", "http://feeds.newscientist.com/life", 0));
		add(new Section("Earth", "http://feeds.newscientist.com/environment", 0));
		add(new Section("Health", "http://feeds.newscientist.com/health", 0));
		add(new Section("Humans", "http://feeds.newscientist.com/humans", 0));

	}

}
