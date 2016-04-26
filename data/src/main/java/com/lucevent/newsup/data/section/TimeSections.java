package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TimeSections extends Sections {

    public TimeSections()
    {
        super();

        add(new Section("News", "http://time.com/newsfeed/feed/", 0));
        add(new Section("Top Stories", "http://feeds2.feedburner.com/time/topstories", 0));
        add(new Section("Most Emailed", "http://feeds2.feedburner.com/time/mostemailed", 0));

        add(new Section("Politics", "http://time.com/politics/feed/", 0));
        add(new Section("World", "http://feeds2.feedburner.com/time/world", 0));
        add(new Section("Global Spin", "http://feeds2.feedburner.com/timeblogs/globalspin", 0));
        add(new Section("Moneyland", "http://time.com/business/feed/", 0));
        add(new Section("Technology", "http://time.com/tech/feed/", 0));
        add(new Section("Health", "http://time.com/health/feed/", 0));
        add(new Section("Science", "http://feeds2.feedburner.com/time/scienceandhealth", 0));
        add(new Section("Entertainment", "http://feeds2.feedburner.com/time/entertainment", 0));

        add(new Section("10 Questions", "http://feeds2.feedburner.com/time/10questions", 0));
        add(new Section("Joe Klein 's Columns", "http://feeds2.feedburner.com/time/joeklein", 0));

    }

}
