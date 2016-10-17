package com.lucevent.newsup.kernel.util;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Tags;

public class HistoryNews extends News {

    public final int hid;

    public HistoryNews(int id, int news_id, String title, String link, String description, long date, Tags tags, int site_code)
    {
        super(news_id, title, link, description, date, tags);
        this.hid = id;
        this.site_code = site_code;
    }

    public News toNews()
    {
        News res = new News(id, title, link, description, date, tags);
        res.content = content;
        res.enclosures = enclosures;
        res.site_code = site_code;
        return res;
    }

    @Override
    public int compareTo(News o)
    {
        return Integer.compare(((HistoryNews) o).hid, this.hid);
    }

}
