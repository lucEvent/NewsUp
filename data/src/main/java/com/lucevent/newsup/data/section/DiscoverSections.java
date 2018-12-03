package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DiscoverSections extends Sections {

	public DiscoverSections()
	{
		super();

		add(new Section("Top stories", "http://feeds.feedburner.com/DiscoverTopStories", 0));
		add(new Section("All stories", "http://feeds.feedburner.com/AllDiscovermagazinecomContent", 0));

		add(new Section("Health & Medicine", "http://feeds.feedburner.com/DiscoverHealthMedicine", 0));
		add(new Section("Mind brain", "http://feeds.feedburner.com/DiscoverMindBrain", 0));
		add(new Section("Technology", "http://feeds.feedburner.com/DiscoverTechnology", 0));
		add(new Section("Space & Physics", "http://feeds.feedburner.com/DiscoverSpace", 0));
		add(new Section("Living world", "http://feeds.feedburner.com/DiscoverLivingWorld", 0));
		add(new Section("Environment", "http://feeds.feedburner.com/DiscoverEnvironment", 0));

		add(new Section("Blogs", "http://feeds.feedburner.com/DiscoverBlogs", 0));
		add(new Section("The Crux", "http://feeds.feedburner.com/discovercrux", 1));
		add(new Section("Dead things", "http://feeds.feedburner.com/dead-things/", 1));
		add(new Section("ImaGeo", "http://feeds.feedburner.com/imageo", 1));
		add(new Section("Inkfish", "http://feeds.feedburner.com/ink-fish", 1));
		add(new Section("Lovesick cyborg", "http://feeds.feedburner.com/lovesick-cyborg", 1));
		add(new Section("Neuroskeptic", "http://feeds.feedburner.com/neuro-skeptic", 1));
		add(new Section("Out there", "http://feeds.feedburner.com/out-there", 1));
		add(new Section("Science sushi", "http://feeds.feedburner.com/Science-Sushi", 1));

		add(new Section("20 things you didn't know about...", "http://feeds.feedburner.com/20ThingsYouDidntKnowAbout", 0));
		add(new Section("Notes from Earth", "http://feeds.feedburner.com/notes-from-earth", 0));
		add(new Section("Vital signs", "http://feeds.feedburner.com/discovermagazine/VitalSigns", 0));

	}

}