package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.Sites;

public class Data {

    public static Sites sites;

    public static Statistics stats;

    public Data()
    {
        if (sites == null) {
            sites = new Sites((String[]) null);
            stats = new Statistics(sites.size());
        }
    }

}
