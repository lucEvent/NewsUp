package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VerneSections extends Sections {

    public VerneSections()
    {
        super();

        add(new Section("Principal", "http://verne.elpais.com/rss/verne/portada.xml", 0));

    }

}
