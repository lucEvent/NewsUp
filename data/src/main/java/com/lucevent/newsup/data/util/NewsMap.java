package com.lucevent.newsup.data.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class NewsMap extends TreeSet<News> {

    public NewsMap()
    {
        super();
    }

    public NewsMap(Collection<? extends News> c)
    {
        super(c);
    }

    public NewsMap(Comparator<News> comparator)
    {
        super(comparator);
    }

    public void setCode(int code)
    {
        for (News N : this)
            N.site_code = code;
    }

}
