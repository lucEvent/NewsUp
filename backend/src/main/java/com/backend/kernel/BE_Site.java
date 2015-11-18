package com.backend.kernel;

import com.backend.kernel.list.BE_NewsList;
import com.backend.net.BE_NewsReader;

import java.util.TreeSet;

public class BE_Site {

    public final int code;

    public final String name;

    public TreeSet<BE_News> news;
    private BE_NewsReader reader;

    public BE_Site(int code, String name, BE_NewsReader reader) {
        this.code = code;
        this.name = name;
        this.reader = reader;
        this.news = new TreeSet<>();
    }

    public BE_NewsReader getReader() {
        return reader;
    }

    public void addNews(BE_NewsList newslist) {
        for (BE_News news : newslist) {
            this.news.add(news);
        }
    }

}
