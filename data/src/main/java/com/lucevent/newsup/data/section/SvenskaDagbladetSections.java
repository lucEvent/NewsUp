package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SvenskaDagbladetSections extends Sections {

    public SvenskaDagbladetSections() {
        super();

        add(new Section("Huvudnyheter", "http://www.svd.se/?service=rss", 0));
        add(new Section("Articles", "http://www.svd.se/feed/articles.rss", 0));

    }

}
