package com.newsup.kernel;

import com.newsup.kernel.list.Tags;
import com.newsup.kernel.util.Date;

public class News {

    public int id;

    public final String title, link, description;

    public Date date;

    public final Tags categories;

    public String content;

    public Site site;

    public News(int id, String title, String link, String description, Date date, Tags categories) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = date;
        this.categories = categories;
    }

    public News(String title, String link, String description, String date, Tags categories) {
        this(-1, title, link, description, new Date(date), categories);
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
