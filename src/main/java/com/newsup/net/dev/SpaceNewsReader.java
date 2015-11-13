package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class SpaceNewsReader extends NewsReader {

    public SpaceNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main", 0, "http://www.space.com/home/feed/site.xml"));

    }

}