package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class RacoCatalaSections extends Sections {

    public RacoCatalaSections()
    {
        super();

        add(new Section("Notícies", "http://www.racocatala.cat/feed/rss.php", 0));

    }

}
