package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;

class Data {

    static Sites sites;

    static Statistics stats;

    static Reports reports;

    Data()
    {
        Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});
        if (sites == null) {
            sites = Sites.getDefault(true);

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.initialize(sites);
                }
            });

            reports = new Reports();

        }
    }

}
