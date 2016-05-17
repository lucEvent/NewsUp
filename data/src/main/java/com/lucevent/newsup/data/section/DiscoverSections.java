package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DiscoverSections extends Sections {

    public DiscoverSections()
    {
        super();

        add(new Section("Top stories", "http://feeds.feedburner.com/DiscoverTopStories", 0));
        add(new Section("All stories", "http://feeds.feedburner.com/AllDiscovermagazinecomContent", 0));

        add(new Section("Blogs", null, -1));
        add(new Section("Latest blogs", "http://feeds.feedburner.com/DiscoverBlogs", 1));
        add(new Section("The Crux", "http://feeds.feedburner.com/discovercrux", 1));
        add(new Section("The extremo files", "http://feeds.feedburner.com/the-extremo-files", 1));
        add(new Section("ImaGeo", "http://feeds.feedburner.com/imageo", 1));
        add(new Section("Lovesick Cyborg", "http://feeds.feedburner.com/lovesick-cyborg", 1));
        add(new Section("Neuroskeptic", "http://feeds.feedburner.com/neuro-skeptic", 1));
        add(new Section("Out There", "http://feeds.feedburner.com/out-there", 1));
        add(new Section("Science Sushi", "http://feeds.feedburner.com/Science-Sushi", 1));

        add(new Section("Topics", null, -1));
        add(new Section("Health & Medicine", "http://feeds.feedburner.com/DiscoverHealthMedicine", 1));
        add(new Section("Mind Brain", "http://feeds.feedburner.com/DiscoverMindBrain", 1));
        add(new Section("Technology", "http://feeds.feedburner.com/DiscoverTechnology", 1));
        add(new Section("Space & Physics", "http://feeds.feedburner.com/DiscoverSpace", 1));
        add(new Section("Environment", "http://feeds.feedburner.com/DiscoverEnvironment", 1));

        add(new Section("Departments", null, -1));
        add(new Section("20 Things You Didn't Know About...", "http://feeds.feedburner.com/20ThingsYouDidntKnowAbout", 1));
        add(new Section("Mind Over Matter", "http://feeds.feedburner.com/mind-over-matter", 1));

    }

}