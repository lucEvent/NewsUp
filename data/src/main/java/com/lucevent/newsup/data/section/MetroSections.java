package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MetroSections extends Sections {

    public MetroSections()
    {
        super();

        add(new Section("Nyheter", "http://www.metro.se/rss.xml?c=1292335191-0", 0));
        add(new Section("Senaste nytt", "http://www.metro.se/rss.xml", 0));
        add(new Section("Toppnyheter", "http://www.metro.se/rss.xml?c=1300413663-0", 0));

        add(new Section("Sverige", "http://www.metro.se/rss.xml?c=1292335191-25", 0));
        add(new Section("Sverige-topp", "http://www.metro.se/rss.xml?c=1292335191-29", 1));

        add(new Section("Världen", "http://www.metro.se/rss.xml?c=1292335191-13", 0));

        add(new Section("Sport", "http://www.metro.se/rss.xml?c=1292335191-1", 0));
        add(new Section("Sport-topp", "http://www.metro.se/rss.xml?c=1292335191-32", 1));

        add(new Section("Nöje", "http://www.metro.se/rss.xml?c=1292335191-5", 0));
        add(new Section("Teknik", "http://www.metro.se/rss.xml?c=1292335191-4", 0));
        add(new Section("Viralgranskaren", "http://www.metro.se/rss.xml?c=1394586029-0", 0));
        add(new Section("Kolumner", "http://www.metro.se/rss.xml?c=1292335191-14", 0));

    }

}
