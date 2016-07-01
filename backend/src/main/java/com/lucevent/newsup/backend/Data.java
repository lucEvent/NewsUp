package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.sports.Sports;
import com.lucevent.newsup.data.util.Date;

public class Data {

    public static Sites sites;
    public static Sports sports;

    public static Statistics stats;

    public Data()
    {
        Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});
        if (sites == null) {
            sites = Sites.getDefault(true);
            sports = new Sports(new String[]{"", "", "", "", ""});

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.initialize(sites);
                }
            });

        }
    }

}
