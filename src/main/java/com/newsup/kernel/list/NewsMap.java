package com.newsup.kernel.list;

import java.util.Comparator;
import java.util.TreeSet;

import com.newsup.kernel.Date;
import com.newsup.kernel.News;

public class NewsMap extends TreeSet<News> {

    private static final long serialVersionUID = 1900914332073565212L;

    public NewsMap() {
        super(new Comparator<News>() {
            @Override
            public int compare(News o1, News o2) {
                int comparison = Date.compare(o1.date, o2.date);
                return comparison == 0 ? o1.title.compareTo(o2.title) : comparison;
            }
        });

    }

    public NewsMap(Comparator<News> comparator) {
        super(comparator);
    }

}
