package com.lucevent.newsup.data.util;

public class Site {

    public final int code;

    public final String name;

    public final int color;

    public final int info;

    public Sections sections;

    public NewsMap news;

    private NewsReader reader;

    public News prior_news;

    /**
     *
     */
    private final boolean isDarkColor;

    public Site(int code, String name, int color, int info, Sections sections, NewsReader reader)
    {
        this.code = code;
        this.name = name;
        this.info = 0x1000000 | info;
        this.color = color;
        this.sections = sections;
        this.news = new NewsMap();
        this.reader = reader;

        isDarkColor = (((color >> 16) & 0xFF) < 0x7F) || (((color >> 8) & 0xFF) < 0x7F) || ((color & 0xFF) < 0x7F);
    }

    public int getCountry()
    {
        return ((info >> SiteCountry.shift) & 0xFF);
    }

    public int getLanguage()
    {
        return ((info >> SiteLanguage.shift) & 0xFF);
    }

    public int getCategory()
    {
        return ((info >> SiteCategory.shift) & 0xFF);
    }

    public NewsArray readNewsHeaders(int[] isections)
    {
        NewsArray res = new NewsArray();

        for (int isection : isections)
            res.addAll(reader.readRssHeader(sections.get(isection).url));

        synchronized (News.NEWS_STATIC_SYNCHRONIZER) {
            for (News N : res)
            {
                N.site_code = code;
                N.server_id = News.SERVER_ID_ASSIGNER++;
            }
        }

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
