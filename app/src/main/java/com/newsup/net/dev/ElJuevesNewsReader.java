package com.newsup.net.dev;

import com.newsup.net.NewsReaderDeprecated;

public class ElJuevesNewsReader extends NewsReaderDeprecated {

    public ElJuevesNewsReader() {
        super();

        SECTIONS.add(new SectionDeprecated("Principal", 0, "http://www.eljueves.es/feeds/rss.html"));

    }

}