package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PeopleSections extends Sections {

    public PeopleSections() {
        super();

        add(new Section("Latest News", "http://rss.people.com/web/people/rss/topheadlines/index.xml", 0));
        add(new Section("StyleWatch", "http://feeds.feedburner.com/people/stylewatch/offtherack", 0));
        add(new Section("TV Watch", "http://rss.people.com/web/people/rss/tvwatch/index.xml", 0));
        add(new Section("Pets", "http://rss.people.com/web/people/rss/pets/index.xml", 0));

    }

}
