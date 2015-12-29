package com.backend.kernel;

import com.backend.kernel.util.BE_Date;

import java.util.ArrayList;

public class BE_News implements Comparable<BE_News> {

    public String title, link, description, content;

    public long date;

    public int site_code;

    public ArrayList<String> categories;

    public ArrayList<BE_Enclosure> enclosures;

    public BE_News(String title, String link, String description, long date, ArrayList<String> categories) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = date;
        this.categories = categories;
    }

    public BE_News(String title, String link, String description, String date, ArrayList<String> categories) {
        this(title, link, description, BE_Date.toDate(date), categories);
    }

    public StringBuilder toEntry() {
        StringBuilder res = new StringBuilder("<item><title>");
        res.append(title);
        res.append("</title><link>");
        res.append(link);
        res.append("</link><date>");
        res.append(date);
        res.append("</date><description>");
        res.append(description);
        res.append("</description><content>");
        if (content != null && !content.isEmpty()) res.append(content);
        res.append("</content><categories>");
        res.append(categories.toString());
        res.append("</categories></item>");
        return res;
    }

    public StringBuilder toHtml() {
        StringBuilder res = new StringBuilder("<div class=\"nu_news\" data-featherlight=\"/web?content&site=");
        res.append(site_code + "&date=" + date + "&link=" + link);
        res.append("\"><h2>");
        res.append(title);
        res.append("</h2><p>");
        res.append(description);
        res.append("</p><br><small><em>");
        res.append(categories.toString());
        res.append("</small></em></div>");
        return res;
    }

    @Override
    public int compareTo(BE_News o) {
        int comparison = Long.compare(this.date, o.date);
        return comparison != 0 ? comparison : this.link.compareTo(o.link);
    }
}
