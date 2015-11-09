package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class TimeNewsReader extends NewsReader {
    public TimeNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("News", 0, "http://time.com/newsfeed/feed/"));
        SECTIONS.add(new Section("Top Stories", 0, "http://feeds2.feedburner.com/time/topstories"));
        SECTIONS.add(new Section("Most Emailed", 0, "http://feeds2.feedburner.com/time/mostemailed"));

        SECTIONS.add(new Section("Politics", 0, "http://time.com/politics/feed/"));
        SECTIONS.add(new Section("World", 0, "http://feeds2.feedburner.com/time/world"));
        SECTIONS.add(new Section("Global Spin", 0, "http://feeds2.feedburner.com/timeblogs/globalspin"));
        SECTIONS.add(new Section("Moneyland", 0, "http://time.com/business/feed/"));
        SECTIONS.add(new Section("Technology", 0, "http://time.com/tech/feed/"));
        SECTIONS.add(new Section("Health", 0, "http://time.com/health/feed/"));
        SECTIONS.add(new Section("Science", 0, "http://feeds2.feedburner.com/time/scienceandhealth"));
        SECTIONS.add(new Section("Entertainment", 0, "http://feeds2.feedburner.com/time/entertainment"));

        SECTIONS.add(new Section("10 Questions", 0, "http://feeds2.feedburner.com/time/10questions"));
        SECTIONS.add(new Section("Joe Klein 's Columns", 0, "http://feeds2.feedburner.com/time/joeklein"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();

        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(content);
        doc.select("[width=\"1\"],object").remove();
        news.content = doc.select("body").html();
        return news;
    }

}
