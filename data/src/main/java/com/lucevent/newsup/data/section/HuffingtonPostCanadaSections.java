package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;

public class HuffingtonPostCanadaSections extends com.lucevent.newsup.data.util.Sections {

    public HuffingtonPostCanadaSections()
    {
        super();

        add(new Section("Main news", "http://www.huffingtonpost.ca/feeds/index.xml", 0));
        add(new Section("Alberta", "http://www.huffingtonpost.ca/feeds/verticals/canada-alberta/index.xml", 1));
        add(new Section("British Columbia", "http://www.huffingtonpost.ca/feeds/verticals/canada-british-columbia/index.xml", 1));
        add(new Section("Business", "http://www.huffingtonpost.ca/feeds/verticals/canada-business/index.xml", 0));

    }

}
