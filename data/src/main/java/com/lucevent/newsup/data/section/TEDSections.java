package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TEDSections extends Sections {

    public TEDSections()
    {
        super();

        add(new Section("TED Talks", "https://www.ted.com/talks/rss", 0));
        add(new Section("Blog", "http://blog.ted.com/feed/", 0));

        add(new Section("Ideas", "http://ideas.ted.com/feed/", 0));
        add(new Section("Tech", "http://ideas.ted.com/category/tech/feed/", 1));
        add(new Section("Business", "http://ideas.ted.com/category/business/feed/", 1));
        add(new Section("Arts & Design", "http://ideas.ted.com/category/arts-design/feed/", 1));
        add(new Section("Science", "http://ideas.ted.com/category/science/feed/", 1));
        add(new Section("We humans", "http://ideas.ted.com/category/we-humans/feed/", 1));

    }

}

