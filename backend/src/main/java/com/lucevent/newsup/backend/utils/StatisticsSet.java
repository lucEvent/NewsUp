package com.lucevent.newsup.backend.utils;

import java.util.Comparator;
import java.util.TreeSet;

public class StatisticsSet extends TreeSet<SiteStat> {

    public StatisticsSet(SiteStat[] stats, Comparator<SiteStat> comparator)
    {
        super(comparator);

        for (SiteStat ss : stats)
            add(ss);

    }

}
