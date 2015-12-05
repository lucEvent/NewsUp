package com.newsup.kernel.basic;

import com.newsup.kernel.set.Tags;

public class HistoryNews extends News {

    public final int hid;

    public HistoryNews(int id, int news_id, String title, String link, String description,
                       long date, Tags categories, int site_code) {
        super(news_id, title, link, description, date, categories);
        this.hid = id;
        this.site_code = site_code;
    }

}
