package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheAtlanticSections extends Sections {

    public TheAtlanticSections()
    {
        super();

        add(new Section("The Atlantic", "http://feeds.feedburner.com/TheAtlantic", 0));
        add(new Section("Politics", "http://feeds.feedburner.com/AtlanticPoliticsChannel", 1));
        add(new Section("Business", "http://feeds.feedburner.com/AtlanticBusinessChannel", 1));
        add(new Section("Culture", "http://feeds.feedburner.com/AtlanticCulture", 1));
        add(new Section("Global", "http://feeds.feedburner.com/AtlanticInternational", 1));
        add(new Section("Technology", "http://feeds.feedburner.com/AtlanticScienceAndTechnology", 1));
        add(new Section("U.S.", "http://feeds.feedburner.com/AtlanticNational", 1));
        add(new Section("Health", "http://feeds.feedburner.com/AtlanticFood", 1));
        add(new Section("Video", "http://feeds.feedburner.com/AtlanticVideo", 1));
        add(new Section("Education", "http://feeds.feedburner.com/AtlanticEducationChannel", 1));
        add(new Section("Science", "http://feeds.feedburner.com/AtlanticScienceChannel", 1));
        add(new Section("Photo", "http://feeds.feedburner.com/theatlantic/infocus", 1));
        add(new Section("Notes", "http://feeds.feedburner.com/TheAtlanticNotes", 1));

        add(new Section("The Wire", "http://feeds.feedburner.com/TheAtlanticWire", 0));
        add(new Section("CityLab", "http://feeds.feedburner.com/TheAtlanticCities", 0));

    }

}
