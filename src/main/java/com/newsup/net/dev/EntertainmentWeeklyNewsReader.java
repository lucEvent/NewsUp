package com.newsup.net.dev;


import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class EntertainmentWeeklyNewsReader extends NewsReader {

    public EntertainmentWeeklyNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));

    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}