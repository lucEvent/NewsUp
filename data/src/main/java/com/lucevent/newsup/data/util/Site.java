package com.lucevent.newsup.data.util;

public class Site {

    public final int code;

    public final String name;
    public final int color;

    public Sections sections;

    public NewsMap news;

    private NewsReader reader;

    public News prior_news;

    /**
     *
     */
    private final boolean isDarkColor;

    public Site(int code, String name, int color, Sections sections, NewsReader reader)
    {
        this.code = code;
        this.name = name;
        this.color = color;
        this.sections = sections;
        this.news = new NewsMap();
        this.reader = reader;

        isDarkColor = (((color >> 16) & 0xFF) < 0x7F) || (((color >> 8) & 0xFF) < 0x7F) || ((color & 0xFF) < 0x7F);
    }

    public NewsArray readNewsHeaders(int[] isections)
    {
        NewsArray res = new NewsArray();

        for (int isection : isections)
            res.addAll(reader.readRssHeader(sections.get(isection).url));

        for (News N : res)
            N.site_code = code;

        return res;
    }

    public void readNewsContent(News news)
    {
        reader.readNewsContent(news);
    }

    public boolean hasDarkColor()
    {
        return isDarkColor;
    }

    public String getStyle()
    {
        return reader.style;
    }

}
