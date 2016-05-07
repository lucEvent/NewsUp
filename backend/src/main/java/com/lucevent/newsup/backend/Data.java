package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.Sites;

public class Data {

    public static Sites sites;

    public static Statistics stats;

    public Data()
    {
        if (sites == null) {
            sites = new Sites((String[]) null);

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.initialize(sites.size());
                }
            });

        }
    }

}
