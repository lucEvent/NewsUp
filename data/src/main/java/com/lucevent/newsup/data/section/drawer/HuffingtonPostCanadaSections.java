package com.lucevent.newsup.data.section.drawer;

import com.lucevent.newsup.data.util.Section;

public class HuffingtonPostCanadaSections extends com.lucevent.newsup.data.util.Sections {

    public HuffingtonPostCanadaSections() {
        super();

        add(new Section("Canada", "http://www.huffingtonpost.ca/feeds/news.xml", 0));
        add(new Section("Politics", "http://www.huffingtonpost.ca/feeds/verticals/canada-politics/news.xml", 1));
        add(new Section("Business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/news.xml", 1));
        add(new Section("Sorts", "http://www.huffingtonpost.ca/feeds/verticals/canada-sports/news.xml", 1));
        add(new Section("Lifestyle", "http://www.huffingtonpost.ca/feeds/verticals/canada-lifestyle/news.xml", 1));
        add(new Section("Travel", "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/news.xml", 1));
        add(new Section("Impact", "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/news.xml", 1));
        add(new Section("Music", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/news.xml", 1));
        add(new Section("Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/news.xml", 1));
        add(new Section("British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/news.xml", 1));
        add(new Section("", "http://www.huffingtonpost.ca/feeds/verticals/canada-parents/index.xml", 1));
        add(new Section("", "http://www.huffingtonpost.ca/feeds/verticals/canada-living/index.xml", 1));
        add(new Section("", "http://www.huffingtonpost.ca/feeds/verticals/canada-style/index.xml", 1));
        add(new Section("", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/index.xml", 1));
        add(new Section("", "http://www.huffingtonpost.ca/feeds/verticals/canada-tv/index.xml", 1));

        add(new Section("Blogs", "http://www.huffingtonpost.ca/feeds/blog.xml", 0));
        add(new Section("Blogs politics", "http://www.huffingtonpost.ca/feeds/verticals/canada-politics/blog.xml", 1));
        add(new Section("Blogs business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/blog.xml", 1));
        add(new Section("Blogs sports", "http://www.huffingtonpost.ca/feeds/verticals/canada-sports/blog.xml", 1));
        add(new Section("Blogs lifestyle", "http://www.huffingtonpost.ca/feeds/verticals/canada-lifestyle/blog.xml", 1));
        add(new Section("Blogs travel", "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/blog.xml", 1));
        add(new Section("Blogs impact", "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/blog.xml", 1));
        add(new Section("Blogs music", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/blog.xml", 1));
        add(new Section("Blogs Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/blog.xml", 1));
        add(new Section("Blogs British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/blog.xml", 1));

    }

}
