package com.lucevent.newsup.data.util;

public class Site {

    public final int code;

    public final String name;

    public final int color;
    private final double colorDarkness;

    public final int info;

    public NewsMap news;

    private Class readerClass;
    private Reader reader;

    private Class sectionsClass;
    private Sections sections;

    public Site(int code, String name, int color, int info, Class sectionsClass, Class readerClass)
    {
        this.code = code;
        this.name = name;
        this.info = 0x1000000 | info;
        this.color = color;
        this.sectionsClass = sectionsClass;
        this.readerClass = readerClass;

        colorDarkness = 1 - (0.299 * ((color >> 16) & 0xFF) + 0.587 * ((color >> 8) & 0xFF) + 0.114 * (color & 0xFF)) / 255;
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

    public Reader getReader()
    {
        if (reader == null)
            reader = (Reader) load(readerClass);
        return reader;
    }

    public NewsArray readNewsHeaders(int[] isections)
    {
        NewsArray res = new NewsArray();

        for (int isection : isections)
            res.addAll(getReader().readRssHeader(getSections().get(isection).url));

        return res;
    }

    public void readNewsContent(News news)
    {
        getReader().readContent(news);
    }

    public double getColorDarkness()
    {
        return colorDarkness;
    }

    public boolean needsBrightColors()
    {
        return colorDarkness >= 0.3;
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