package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class NationalGeographicNewsReader extends NewsReader {

    public NationalGeographicNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Main news", 0, "http://news.nationalgeographic.com/rss/index.rss"));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));
        SECTIONS.add(new SectionDeprecated("", 0, ""));


    }

}
