package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TEDSections extends Sections {

    public TEDSections() {
        super();

        add(new Section("Main talks", "http://blog.ted.com/feed/", 0));
        add(new Section("Talks Videos", "https://www.ted.com/talks/rss", 0));

    }

}

