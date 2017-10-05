package com.lucevent.newsup.data.util;

import java.io.Serializable;

public class News implements Comparable<News>, Serializable {

    public final int id;

    public final String title, link;

    public final long date;

    public long readOn;

    public final int site_code, section_code;

    public String description, content;

    public final Tags tags;

    public Enclosures enclosures;

    public News(int id, String title, String link, String description, long date, Tags tags, int site_code, int section_code, long readOn)
    {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = date;
        this.tags = tags;
        this.section_code = section_code;
        this.readOn = readOn;
        this.site_code = site_code;
    }

    public News(String title, String link, String description, long date, Tags categories, int site_code, int section_code, long readOn)
    {
        this(link.hashCode(), title, link, description, date, categories, site_code, section_code, readOn);
    }

    public boolean hasKeyWords(String[] keyWords){
        for(String keyW : keyWords){
            for(String tag : tags){
                if (tag.equals(keyW))
                    return true;
            }
            if (title.toLowerCase().contains(keyW))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(News o)
    {
        int comparison = Long.compare(this.date, o.date);
        return comparison != 0 ? -comparison : Integer.compare(this.id, o.id);
    }

}
