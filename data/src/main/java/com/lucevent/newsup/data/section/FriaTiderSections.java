package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class FriaTiderSections extends Sections {

    public FriaTiderSections() {
        super();

        add(new Section("Nyheter", "http://www.friatider.se/rss.xml", 0));
        add(new Section("Politik", "http://www.friatider.se/taxonomy/term/38/feed", 0));
        add(new Section("Ekonomi", "http://www.friatider.se/taxonomy/term/2/feed", 0));
        add(new Section("Kultur", "http://www.friatider.se/taxonomy/term/3/feed", 0));
        add(new Section("Vetenskap", "http://www.friatider.se/taxonomy/term/19/feed", 0));
        add(new Section("Inrikes", "http://www.friatider.se/taxonomy/term/20/feed", 0));
        add(new Section("Utrikes", "http://www.friatider.se/taxonomy/term/21/feed", 0));
        add(new Section("Ledare", "http://www.friatider.se/taxonomy/term/31/feed", 0));
        add(new Section("Special: Sidebar top", "http://www.friatider.se/taxonomy/term/16/feed", 0));
        add(new Section("Media", "http://www.friatider.se/taxonomy/term/36/feed", 0));
        add(new Section("Du betalar", "http://www.friatider.se/taxonomy/term/34/feed", 0));

        add(new Section("Top first", "http://www.friatider.se/taxonomy/term/26/feed", 0));
        add(new Section("Top second", "http://www.friatider.se/taxonomy/term/27/feed", 0));

        add(new Section("Large", "http://www.friatider.se/taxonomy/term/4/feed", 0));
        add(new Section("Medium", "http://www.friatider.se/taxonomy/term/5/feed", 0));
        add(new Section("Normal", "http://www.friatider.se/taxonomy/term/7/feed", 0));
        add(new Section("Wide", "http://www.friatider.se/taxonomy/term/8/feed", 0));

    }

}
