package com.lucevent.newsup.data;

import com.lucevent.newsup.data.util.Site;

import java.util.Comparator;
import java.util.TreeSet;

public class SitesMap extends TreeSet<Site> {

    public SitesMap(Sites sites, Comparator<Site> comparator)
    {
        super(comparator);
        addAll(sites);
    }

    public SitesMap(Sites sites, Comparator<Site> comparator, String filter)
    {
        super(comparator);

        if (filter.isEmpty()) {
            addAll(sites);
        } else {
            String lcfilter = filter.toLowerCase();
            for (Site s : sites)
                if (s.name.toLowerCase().contains(lcfilter))
                    add(s);

        }

    }

    public static Comparator<Site> SITE_COMPARATOR_BY_NAME = new Comparator<Site>() {
        @Override
        public int compare(Site o1, Site o2)
        {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    };

    public static Comparator<Site> SITE_COMPARATOR_BY_COUNTRY = new Comparator<Site>() {
        @Override
        public int compare(Site o1, Site o2)
        {
            return o1.getCountry() < o2.getCountry() ? -1 : 1;
        }
    };

    public static Comparator<Site> SITE_COMPARATOR_BY_LANGUAGE = new Comparator<Site>() {
        @Override
        public int compare(Site o1, Site o2)
        {
            return o1.getLanguage() < o2.getLanguage() ? -1 : 1;
        }
    };

    public static Comparator<Site> SITE_COMPARATOR_BY_CATEGORY = new Comparator<Site>() {
        @Override
        public int compare(Site o1, Site o2)
        {
            return o1.getCategory() < o2.getCategory() ? -1 : 1;
        }
    };

    public Site getSiteByCode(int code)
    {
        for (Site s : this)
            if (s.code == code)
                return s;

        return null;
    }

}
