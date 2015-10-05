package com.newsup.kernel;

import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;
import com.newsup.settings.SiteSettings;

public class Site {

    /**
     * Variables
     **/
    public final int code;
    public final String name;

    public NewsList news;
    public NewsMap historial;

    public SiteSettings settings;

    public NewsReader reader;

    public Site(int code, String name, NewsReader reader) {
        this.code = code;
        this.name = name;
        this.reader = reader;
        this.historial = new NewsMap();
    }

    public NewsReader getReader() {
        return reader;
    }

    public SectionList getSections() {
        return reader.SECTIONS;
    }

}
