package com.newsup.net.dev;

import com.newsup.net.NewsReaderDeprecated;

public class NationalGeographicNewsReader extends NewsReaderDeprecated {

    public NationalGeographicNewsReader() {
        super();

        SECTIONS.add(new SectionDeprecated("Main news", 0, "http://news.nationalgeographic.com/rss/index.rss"));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));


    }

}
