package com.newsup.net.dev;

import com.newsup.net.NewsReaderDeprecated;

public class TheBarkNewsReader extends NewsReaderDeprecated {

    public TheBarkNewsReader() {
        super();

        SECTIONS.add(new SectionDeprecated("Main", 0, "http://thebark.com/rss.xml"));

    }

}