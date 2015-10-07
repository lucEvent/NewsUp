package com.newsup.kernel;

import android.graphics.drawable.ColorDrawable;

import com.newsup.ia.IASite;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;
import com.newsup.settings.SiteSettings;

public class Site extends IASite {

    /**
     * Variables
     **/
    public final String name;
    public final ColorDrawable color;

    public NewsList news;
    public NewsMap historial;

    public SiteSettings settings;

    private NewsReader reader;

    public Site(int code, String name, int color, NewsReader reader) {
        super(code);
        this.name = name;
        this.color = new ColorDrawable(color);
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
