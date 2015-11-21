package com.newsup.net.dev;

import com.newsup.net.NewsReaderDeprecated;

public class SpaceNewsReader extends NewsReaderDeprecated {

    public SpaceNewsReader() {
        super();

        SECTIONS.add(new SectionDeprecated("Main", 0, "http://www.space.com/home/feed/site.xml"));

    }

}