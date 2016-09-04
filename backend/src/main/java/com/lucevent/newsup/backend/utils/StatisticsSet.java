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

    public static final Comparator<SiteStat> CMP_N_ACCESSES = new Comparator<SiteStat>() {
        @Override
        public int compare(SiteStat o1, SiteStat o2)
        {
            return o1.nAccesses < o2.nAccesses ? 1 : -1;
        }
    };

    public static final Comparator<SiteStat> CMP_LAST_ACCESS = new Comparator<SiteStat>() {
        @Override
        public int compare(SiteStat o1, SiteStat o2)
        {
            return o1.lastAccess < o2.lastAccess ? 1 : -1;
        }
    };

    public static final Comparator<SiteStat> CMP_SITE_NAME = new Comparator<SiteStat>() {
        @Override
        public int compare(SiteStat o1, SiteStat o2)
        {
            return o1.siteName.compareTo(o2.siteName);
        }
    };

    public static final Comparator<SiteStat> CMP_READINGS = new Comparator<SiteStat>() {
        @Override
        public int compare(SiteStat o1, SiteStat o2)
        {
            return o1.nNewsRead < o2.nNewsRead ? 1 : -1;
        }
    };

}
