package com.newsup.net.dev;

import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReaderDeprecated;

public class ComputerHoyNewsReader extends NewsReaderDeprecated {

    public ComputerHoyNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Principal", 0, "http://computerhoy.com/rss.xml"));

    }

}