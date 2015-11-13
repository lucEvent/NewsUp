package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class TheBarkNewsReader extends NewsReader {

    public TheBarkNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main", 0, "http://thebark.com/rss.xml"));

    }

}