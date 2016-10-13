package com.lucevent.newsup.data.util;

import java.io.Serializable;

public class News implements Comparable<News>, Serializable {

    public static final Object NEWS_STATIC_SYNCHRONIZER = new Object();
    public static Integer SERVER_ID_ASSIGNER = 1;

    public int id;
    public long server_id;

    public String title, link, description, content;

    public long date;

    public final Tags tags;

    public Enclosures enclosures;

    public int site_code;

    public News(int id, String title, String link, String description, long date, Tags tags)
    {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = date;
        this.tags = tags;
    }

    public News(String title, String link, String description, long date, Tags categories)
    {
        this(-1, title, link, description, date, categories);
    }

    public void setSite(Site site)
    {
        this.site_code = site.code;
    }

    @Override
    public int compareTo(News o)
    {
        int comparison = Long.compare(this.date, o.date);
        return comparison != 0 ? -comparison : this.link.compareTo(o.link);
    }

}
