package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheVergeSections extends Sections {

    public TheVergeSections()
    {
        super();

        add(new Section("Main", "http://www.theverge.com/rss/full.xml", 0));
        add(new Section("Features", "http://www.theverge.com/rss/group/features/index.xml", 0));

        add(new Section("Hubs", null, -1));
        add(new Section("Android", "http://www.theverge.com/google/rss/full.xml", 1));
        add(new Section("Apple", "http://www.theverge.com/apple/rss/full.xml", 1));
        add(new Section("Apps & Software", "http://www.theverge.com/apps/rss/full.xml", 1));
        add(new Section("BlackBerry", "http://www.theverge.com/rss/group/blackberry/index.xml", 1));
        add(new Section("Microsoft", "http://www.theverge.com/microsoft/rss/full.xml", 1));
        add(new Section("Mobile", "http://www.theverge.com/mobile/rss/full.xml", 1));
        add(new Section("Photography & Video", "http://www.theverge.com/photography/rss/full.xml", 1));
        add(new Section("Policy & Law", "http://www.theverge.com/policy/rss/full.xml", 1));
        add(new Section("Web & Social", "http://www.theverge.com/web/rss/full.xml", 1));

        add(new Section("Labels", null, -1));
        add(new Section("Book Review", "http://www.theverge.com/rss/group/book-review/index.xml", 1));
        add(new Section("Breaking", "http://www.theverge.com/rss/group/breaking/index.xml", 1));
        add(new Section("Editorial", "http://www.theverge.com/rss/group/editorial/index.xml", 1));
        add(new Section("Exclusive", "http://www.theverge.com/rss/group/exclusive/index.xml", 1));
        add(new Section("Longform", "http://www.theverge.com/rss/group/longform/index.xml", 1));
        add(new Section("Interview", "http://www.theverge.com/rss/group/interview/index.xml", 1));
        add(new Section("Meta", "http://www.theverge.com/rss/group/meta/index.xml", 1));
        add(new Section("Podcast", "http://www.theverge.com/rss/group/podcast/index.xml", 1));
        add(new Section("Report", "http://www.theverge.com/rss/group/report/index.xml", 1));
        add(new Section("Review", "http://www.theverge.com/rss/group/review/index.xml", 1));
        add(new Section("Video", "http://www.theverge.com/rss/videos/full.xml", 1));
    }

}
