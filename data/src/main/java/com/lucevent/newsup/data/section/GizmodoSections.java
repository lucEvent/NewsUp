package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GizmodoSections extends Sections {

    public GizmodoSections()
    {
        super();

        add(new Section("Main site", "http://feeds.gawker.com/gizmodo/full", 0));
        add(new Section("UK version", "http://feeds.feedburner.com/uk/gizmodo", 0));
        add(new Section("Spain version", "http://feeds.gawker.com/esgizmodo/full", 0));
        add(new Section("Australia version", "http://feeds.gizmodo.com.au/gizmodoaustralia", 0));

    }

}
