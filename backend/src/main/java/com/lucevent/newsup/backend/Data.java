package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;

public class Data {

    public static Sites sites;

    public static Statistics stats;

    public Data()
    {
        Date.setTitles(new String[]{"%d s. ago", "%d m. ago", "%d h. ago", "%d D. ago", "%d M. ago", "%d Y. ago",});
        if (sites == null) {
            sites = Sites.getDefault(true);

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.initialize(sites);
                }
            });

        }
    }

}
