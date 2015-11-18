package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class HuffingtonPostCanadaNewsReader extends NewsReader {

    public HuffingtonPostCanadaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Canada", 0, "http://www.huffingtonpost.ca/feeds/news.xml"));
        SECTIONS.add(new SectionDeprecated("Politics", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-politics/news.xml"));
        SECTIONS.add(new SectionDeprecated("Business", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-business/news.xml"));
        SECTIONS.add(new SectionDeprecated("Sorts", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-sports/news.xml"));
        SECTIONS.add(new SectionDeprecated("Lifestyle", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-lifestyle/news.xml"));
        SECTIONS.add(new SectionDeprecated("Travel", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/news.xml"));
        SECTIONS.add(new SectionDeprecated("Impact", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/news.xml"));
        SECTIONS.add(new SectionDeprecated("Music", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-music/news.xml"));
        SECTIONS.add(new SectionDeprecated("Alberta", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/news.xml"));
        SECTIONS.add(new SectionDeprecated("British Columbia", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-parents/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-living/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-style/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-music/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-tv/index.xml"));

        SECTIONS.add(new SectionDeprecated("Blogs", 1, "http://www.huffingtonpost.ca/feeds/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs politics", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-politics/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs business", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-business/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs sports", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-sports/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs lifestyle", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-lifestyle/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs travel", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-travel/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs impact", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-impact/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs music", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-music/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs Alberta", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/blog.xml"));
        SECTIONS.add(new SectionDeprecated("Blogs British Columbia", 1, "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/blog.xml"));

    }

}
