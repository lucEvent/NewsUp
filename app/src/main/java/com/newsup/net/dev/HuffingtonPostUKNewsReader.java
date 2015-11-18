package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class HuffingtonPostUKNewsReader extends NewsReader {

    public HuffingtonPostUKNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("UK", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-politics/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-entertainment/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-style/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-universities-education/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-lifestyle/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-comedy/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/celebrity/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-tech/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-sport/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-parents/news.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-universities-education/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-comedy/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-entertainment/index.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/media/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/women/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/men/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/environment/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/whats-working/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/impact/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/entrepreneurs/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/young-talent/feed/"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/news/property/feed/"));

        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-politics/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-entertainment/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-style/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-universities-education/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-lifestyle/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-comedy/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-tech/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-sport/blog.xml"));
        SECTIONS.add(new SectionDeprecated("", 1, "http://www.huffingtonpost.co.uk/feeds/verticals/uk-parents/blog.xml"));

    }

}
