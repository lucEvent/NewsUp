package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class HuffingtonPostAustraliaNewsReader extends NewsReader {

    public HuffingtonPostAustraliaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Australia", 0, "http://www.huffingtonpost.com.au/feeds/verticals/australia/news.xml"));
        SECTIONS.add(new SectionDeprecated("Politics", 1, "http://www.huffingtonpost.com.au/news/au-politics/feed/"));
        SECTIONS.add(new SectionDeprecated("World", 1, "http://www.huffingtonpost.com.au/news/au-world/feed/"));
        SECTIONS.add(new SectionDeprecated("Sport", 1, "http://www.huffingtonpost.com.au/news/au-sport/feed/"));
        SECTIONS.add(new SectionDeprecated("Health", 1, "http://www.huffingtonpost.com.au/news/au-health-news/feed/"));
        SECTIONS.add(new SectionDeprecated("Life", 1, "http://www.huffingtonpost.com.au/news/au-life/feed/"));
        SECTIONS.add(new SectionDeprecated("Food", 1, "http://www.huffingtonpost.com.au/news/au-food/feed/"));
        SECTIONS.add(new SectionDeprecated("Travel", 1, "http://www.huffingtonpost.com.au/news/au-travel/feed/"));
        SECTIONS.add(new SectionDeprecated("Innovation", 1, "http://www.huffingtonpost.com.au/news/au-innovation/feed/"));
        SECTIONS.add(new SectionDeprecated("Small Business", 1, "http://www.huffingtonpost.com.au/news/au-small-business/feed/"));
        SECTIONS.add(new SectionDeprecated("What's working", 1, "http://www.huffingtonpost.com.au/news/au-whats-working/feed/"));
        SECTIONS.add(new SectionDeprecated("Video", 1, "http://www.huffingtonpost.com.au/news/au-hpvideo/feed/"));
        SECTIONS.add(new SectionDeprecated("Home", 1, "http://www.huffingtonpost.com.au/news/au-home/feed/"));
        SECTIONS.add(new SectionDeprecated("Entertainment", 1, "http://www.huffingtonpost.com.au/news/au-entertainment/feed/"));
        SECTIONS.add(new SectionDeprecated("Headstart", 1, "http://www.huffingtonpost.com.au/news/au-headstart/feed/"));
        SECTIONS.add(new SectionDeprecated("Blueprint", 1, "http://www.huffingtonpost.com.au/news/au-blueprint/feed/"));
        SECTIONS.add(new SectionDeprecated("Money", 1, "http://www.huffingtonpost.com.au/news/au-money/feed/"));
        SECTIONS.add(new SectionDeprecated("Work", 1, "http://www.huffingtonpost.com.au/news/au-work/feed/"));
        SECTIONS.add(new SectionDeprecated("Relationships", 1, "http://www.huffingtonpost.com.au/news/au-relationships/feed/"));
        SECTIONS.add(new SectionDeprecated("Win the right way", 1, "http://www.huffingtonpost.com.au/news/au-win-the-right-way/feed/"));
        SECTIONS.add(new SectionDeprecated("Blogs", 1, "http://www.huffingtonpost.com.au/feeds/verticals/australia/blog.xml"));

    }

}
