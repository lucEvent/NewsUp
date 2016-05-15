package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;

public class HuffingtonPostCanadaSections extends com.lucevent.newsup.data.util.Sections {

    public HuffingtonPostCanadaSections()
    {
        super();

        add(new Section("Main news", "http://www.huffingtonpost.ca/feeds/index.xml", 0));
        add(new Section("Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/index.xml", 1));
        add(new Section("British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/index.xml", 1));
        add(new Section("Politics", "http://www.huffingtonpost.ca/feeds/verticals/canada-politics/index.xml", 0));
        add(new Section("Business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/index.xml", 0));
        add(new Section("Travel", "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/index.xml", 0));
        add(new Section("Impact", "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/index.xml", 0));
        add(new Section("Music", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/index.xml", 0));
        add(new Section("Parents", "http://www.huffingtonpost.ca/feeds/verticals/canada-parents/index.xml", 0));
        add(new Section("Living", "http://www.huffingtonpost.ca/feeds/verticals/canada-living/index.xml", 0));
        add(new Section("Style", "http://www.huffingtonpost.ca/feeds/verticals/canada-style/index.xml", 0));
        add(new Section("TV", "http://www.huffingtonpost.ca/feeds/verticals/canada-tv/index.xml", 0));

        add(new Section("Blogs", "http://www.huffingtonpost.ca/feeds/blog.xml", 0));
        add(new Section("Blogs Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/blog.xml", 1));
        add(new Section("Blogs British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/blog.xml", 1));
        add(new Section("Blogs politics", "http://www.huffingtonpost.ca/feeds/verticals/canada-politics/blog.xml", 1));
        add(new Section("Blogs business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/blog.xml", 1));
        add(new Section("Blogs travel", "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/blog.xml", 1));
        add(new Section("Blogs impact", "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/blog.xml", 1));
        add(new Section("Blogs music", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/blog.xml", 1));

    }

}
