package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheLocalSections extends Sections {

    public TheLocalSections()
    {
        super();

        add(new Section("Austria", "http://www.thelocal.at/feeds/rss.php", 0));
        add(new Section("Denmark", "http://www.thelocal.dk/feeds/rss.php", 0));
        add(new Section("France", "http://www.thelocal.fr/feeds/rss.php", 0));
        add(new Section("Germany", "http://www.thelocal.de/feeds/rss.php", 0));
        add(new Section("Italy", "http://www.thelocal.it/feeds/rss.php", 0));
        add(new Section("Norway", "http://www.thelocal.no/feeds/rss.php", 0));
        add(new Section("Spain", "http://www.thelocal.es/feeds/rss.php", 0));
        add(new Section("Sweden", "http://www.thelocal.se/feeds/rss.php", 0));
        add(new Section("Switzerland", "http://www.thelocal.ch/feeds/rss.php", 0));

    }

}
