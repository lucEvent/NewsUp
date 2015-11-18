package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_TimeNewsReader extends BE_NewsReader {
    public BE_TimeNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("News", "http://time.com/newsfeed/feed/"));
        SECTIONS.add(new BE_Section("Top Stories", "http://feeds2.feedburner.com/time/topstories"));
        SECTIONS.add(new BE_Section("Most Emailed", "http://feeds2.feedburner.com/time/mostemailed"));

        SECTIONS.add(new BE_Section("Politics", "http://time.com/politics/feed/"));
        SECTIONS.add(new BE_Section("World", "http://feeds2.feedburner.com/time/world"));
        SECTIONS.add(new BE_Section("Global Spin", "http://feeds2.feedburner.com/timeblogs/globalspin"));
        SECTIONS.add(new BE_Section("Moneyland", "http://time.com/business/feed/"));
        SECTIONS.add(new BE_Section("Technology", "http://time.com/tech/feed/"));
        SECTIONS.add(new BE_Section("Health", "http://time.com/health/feed/"));
        SECTIONS.add(new BE_Section("Science", "http://feeds2.feedburner.com/time/scienceandhealth"));
        SECTIONS.add(new BE_Section("Entertainment", "http://feeds2.feedburner.com/time/entertainment"));

        SECTIONS.add(new BE_Section("10 Questions", "http://feeds2.feedburner.com/time/10questions"));
        SECTIONS.add(new BE_Section("Joe Klein 's Columns", "http://feeds2.feedburner.com/time/joeklein"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();

        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(content);
        doc.select("[width=\"1\"],object").remove();
        news.content = doc.select("body").html();
        return news;
    }

}
