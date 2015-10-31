package com.newsup.net.dev;


import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class HuffingtonPostAustraliaNewsReader extends NewsReader {

    public HuffingtonPostAustraliaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Australia", 0, "http://www.huffingtonpost.com.au/feeds/verticals/australia/news.xml"));
        SECTIONS.add(new Section("Politics", 1, "http://www.huffingtonpost.com.au/news/au-politics/feed/"));
        SECTIONS.add(new Section("World", 1, "http://www.huffingtonpost.com.au/news/au-world/feed/"));
        SECTIONS.add(new Section("Sport", 1, "http://www.huffingtonpost.com.au/news/au-sport/feed/"));
        SECTIONS.add(new Section("Health", 1, "http://www.huffingtonpost.com.au/news/au-health-news/feed/"));
        SECTIONS.add(new Section("Life", 1, "http://www.huffingtonpost.com.au/news/au-life/feed/"));
        SECTIONS.add(new Section("Food", 1, "http://www.huffingtonpost.com.au/news/au-food/feed/"));
        SECTIONS.add(new Section("Travel", 1, "http://www.huffingtonpost.com.au/news/au-travel/feed/"));
        SECTIONS.add(new Section("Innovation", 1, "http://www.huffingtonpost.com.au/news/au-innovation/feed/"));
        SECTIONS.add(new Section("Small Business", 1, "http://www.huffingtonpost.com.au/news/au-small-business/feed/"));
        SECTIONS.add(new Section("What's working", 1, "http://www.huffingtonpost.com.au/news/au-whats-working/feed/"));
        SECTIONS.add(new Section("Video", 1, "http://www.huffingtonpost.com.au/news/au-hpvideo/feed/"));
        SECTIONS.add(new Section("Home", 1, "http://www.huffingtonpost.com.au/news/au-home/feed/"));
        SECTIONS.add(new Section("Entertainment", 1, "http://www.huffingtonpost.com.au/news/au-entertainment/feed/"));
        SECTIONS.add(new Section("Headstart", 1, "http://www.huffingtonpost.com.au/news/au-headstart/feed/"));
        SECTIONS.add(new Section("Blueprint", 1, "http://www.huffingtonpost.com.au/news/au-blueprint/feed/"));
        SECTIONS.add(new Section("Money", 1, "http://www.huffingtonpost.com.au/news/au-money/feed/"));
        SECTIONS.add(new Section("Work", 1, "http://www.huffingtonpost.com.au/news/au-work/feed/"));
        SECTIONS.add(new Section("Relationships", 1, "http://www.huffingtonpost.com.au/news/au-relationships/feed/"));
        SECTIONS.add(new Section("Win the right way", 1, "http://www.huffingtonpost.com.au/news/au-win-the-right-way/feed/"));
        SECTIONS.add(new Section("Blogs", 1, "http://www.huffingtonpost.com.au/feeds/verticals/australia/blog.xml"));

    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}
