package com.lucevent.newsup.data.util;

public class Site {

    public final int code;

    public final String name;

    public final int color;

    public final int info;

    public NewsMap news;

    private Class readerClass;
    private NewsReader reader;

    private Class sectionsClass;
    private Sections sections;

    /**
     *
     */
    private final boolean isDarkColor;

    public Site(int code, String name, int color, int info, Class sectionsClass, Class readerClass)
    {
        this.code = code;
        this.name = name;
        this.info = 0x1000000 | info;
        this.color = color;
        this.sectionsClass = sectionsClass;
        this.readerClass = readerClass;
        this.news = new NewsMap();

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

    public Sections getSections()
    {
        if (sections == null)
            sections = (Sections) load(sectionsClass);
        return sections;
    }

    public NewsReader getReader()
    {
        if (reader == null)
            reader = (NewsReader) load(readerClass);
        return reader;
    }

    public NewsArray readNewsHeaders(int[] isections)
    {
        NewsArray res = new NewsArray();

        for (int isection : isections)
            res.addAll(getReader().readRssHeader(getSections().get(isection).url));

        synchronized (News.NEWS_STATIC_SYNCHRONIZER) {
            for (News N : res) {
                N.site_code = code;
                N.server_id = News.SERVER_ID_ASSIGNER++;
            }
        }

        return res;
    }

    public void readNewsContent(News news)
    {
        getReader().readNewsContent(news);
    }

    public boolean hasDarkColor()
    {
        return isDarkColor;
    }

    public String getStyle()
    {
        return getReader().style;
    }

    private Object load(Class _class)
    {
        try {
            return _class.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
