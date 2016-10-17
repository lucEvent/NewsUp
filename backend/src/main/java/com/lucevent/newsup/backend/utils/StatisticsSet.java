package com.lucevent.newsup.backend.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class StatisticsSet extends TreeSet<SiteStats> {

    public StatisticsSet(ArrayList<SiteStats> stats, Comparator<SiteStats> comparator)
    {
        super(comparator);
        addAll(stats);
    }

    public static final Comparator<SiteStats> CMP_N_ACCESSES = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.nAccesses < o2.nAccesses ? 1 : -1;
        }
    };

    public static final Comparator<SiteStats> CMP_LAST_ACCESS = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.lastAccess < o2.lastAccess ? 1 : -1;
        }
    };

    public static final Comparator<SiteStats> CMP_SITE_NAME = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.siteName.compareTo(o2.siteName);
        }
    };

    public static final Comparator<SiteStats> CMP_READINGS = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.nNewsRead < o2.nNewsRead ? 1 : -1;
        }
    };

}
