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

    public static final Comparator<SiteStats> CMP_TOTAL_REQUESTS = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.totalRequests < o2.totalRequests ? 1 : -1;
        }
    };

    public static final Comparator<SiteStats> CMP_MONTH_REQUESTS = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.monthRequests < o2.monthRequests ? 1 : -1;
        }
    };

    public static final Comparator<SiteStats> CMP_LAST_REQUEST = new Comparator<SiteStats>() {
        @Override
        public int compare(SiteStats o1, SiteStats o2)
        {
            return o1.lastRequest < o2.lastRequest ? 1 : -1;
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
            return o1.readings < o2.readings ? 1 : -1;
        }
    };

}
