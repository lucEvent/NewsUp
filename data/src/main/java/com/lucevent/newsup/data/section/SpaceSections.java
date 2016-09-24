package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SpaceSections extends Sections {

    public SpaceSections()
    {
        super();

        add(new Section("Main", "http://www.space.com/home/feed/site.xml", 0));

    }

}
