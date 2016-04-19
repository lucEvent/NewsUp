package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DogsterSections extends Sections {

    public DogsterSections() {
        super();

        add(new Section("News", "http://www.dogster.com/feed/", 0));
        add(new Section("The Scoop", "http://www.dogster.com/the-scoop/feed/", 0));
        add(new Section("Lifestyle", "http://www.dogster.com/lifestyle/feed/", 0));

    }

}