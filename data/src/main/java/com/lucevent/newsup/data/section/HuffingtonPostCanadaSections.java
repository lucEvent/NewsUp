package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;

public class HuffingtonPostCanadaSections extends com.lucevent.newsup.data.util.Sections {

    public HuffingtonPostCanadaSections()
    {
        super();

        add(new Section("Main news", "http://www.huffingtonpost.ca/feeds/index.xml", 0));
        add(new Section("Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/index.xml", 1));
        add(new Section("British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/index.xml", 1));
        add(new Section("Business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/index.xml", 0));
        add(new Section("Travel", "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/index.xml", 0));
        add(new Section("Impact", "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/index.xml", 0));
        add(new Section("Music", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/index.xml", 0));

        add(new Section("Blogs", null, -1));
        add(new Section("Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/blog.xml", 1));
        add(new Section("British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/blog.xml", 1));
        add(new Section("Business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/blog.xml", 1));
        add(new Section("Travel", "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/blog.xml", 1));
        add(new Section("Impact", "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/blog.xml", 1));
        add(new Section("Music", "http://www.huffingtonpost.ca/feeds/verticals/canada-music/blog.xml", 1));

    }

}
