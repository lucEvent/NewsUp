package com.newsup.kernel.set;

import com.newsup.kernel.basic.News;

import java.util.Comparator;
import java.util.TreeSet;

public class NewsMap extends TreeSet<News> {

    public NewsMap() {
    }

    public NewsMap(Comparator<News> comparator) {
        super(comparator);
    }

}
