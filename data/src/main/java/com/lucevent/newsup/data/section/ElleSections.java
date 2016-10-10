package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElleSections extends Sections {

    public ElleSections()
    {
        super();

        add(new Section("Principal", "http://www.elle.es/rss/all.xml", 0));

    }

}
