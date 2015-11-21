package com.newsup.kernel;

import com.newsup.kernel.list.Tags;
import com.newsup.kernel.util.Date;
import com.newsup.net.util.Enclosure;

import java.util.ArrayList;

public class News implements Comparable<News> {

    public int id;

    public String title, link, description, content;

    public long date;

    public final Tags categories;
    public ArrayList<Enclosure> enclosures;

    public Site site;

    public News(int id, String title, String link, String description, long date, Tags categories) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = date;
        this.categories = categories;
    }

    public News(String title, String link, String description, long date, Tags categories) {
        this(-1, title, link, description, date, categories);
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(News another) {
        int comparison = Date.compare(this.date, another.date);
        return comparison != 0 ? -comparison : this.link.compareTo(another.link);
    }

}
