package com.lucevent.newsup.data;

import com.lucevent.newsup.data.util.Site;

import java.util.Comparator;
import java.util.TreeSet;

public class SitesMap extends TreeSet<Site> {

    public SitesMap(Comparator<Site> comparator)
    {
        super(comparator);

        addAll(new Sites((String[]) null));
    }

    public static Comparator<Site> SITE_COMPARATOR_BY_NAME = new Comparator<Site>() {
        @Override
        public int compare(Site o1, Site o2)
        {
            return o1.name.compareToIgnoreCase(o2.name);
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
