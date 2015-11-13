package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class ElJuevesNewsReader extends NewsReader {

    public ElJuevesNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Principal", 0, "http://www.eljueves.es/feeds/rss.html"));

    }

}