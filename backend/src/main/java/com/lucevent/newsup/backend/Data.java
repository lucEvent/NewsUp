package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;

import java.util.Comparator;

class Data {

    @Deprecated
    static Sites sites;

    static Sites sitesV2;

    static Statistics stats;

    static Reports reports;

    Data()
    {
        Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});
        if (sites == null) {
            sites = Sites.getDefault(true);
            sitesV2 = Sites.getDefault(true);
            for (Site s : sitesV2) {
                s.news = new NewsMap(new Comparator<News>() {
                    @Override
                    public int compare(News n1, News n2)
                    {
                        return Long.compare(n1.server_id, n2.server_id);
                    }
                });
            }

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.getInstance();
                }
            });

            reports = new Reports();

        }
    }

}
