package com.newsup.kernel;

import com.newsup.kernel.list.Tags;

public class News {

    public int id;

    public final String title, link, description;

    public Date date;

    public final Tags categories;

    public String content;

    public News(int id, String title, String link, String description, String date, Tags categories) {
        this(title, link, description, date, categories);
        this.id = id;
    }

    public News(String title, String link, String description, String date, Tags categories) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = new Date(date);
        this.categories = categories;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
