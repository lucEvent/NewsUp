package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.newsup.backend.utils.Event;
import com.lucevent.newsup.backend.utils.MonthStats;
import com.lucevent.newsup.backend.utils.Report;
import com.lucevent.newsup.backend.utils.SiteStats;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.backend.utils.TimeStats;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;

public class Data {

    public static Sites sites;

    public static Statistics stats;

    static {
        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(SiteStats.class);
        oFactory.register(TimeStats.class);
        oFactory.register(MonthStats.class);
        oFactory.register(Statistics.class);
        oFactory.register(Report.class);
        oFactory.register(Event.class);
        oFactory.begin();

        Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});
        if (sites == null) {
            sites = Sites.getDefault(true);
            // to avoid errors if some old version requests this dep site
            sites.add(new Site(330, "Metro", 0, "", 0, com.lucevent.newsup.data.section.MetroSVSections.class, com.lucevent.newsup.data.reader.MetroSV.class));

            for (Site s : sites)
                s.news = new NewsMap();

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.getInstance();
                }
            });
        }
    }

    static Site getSite(int code)
    {
        return sites.getSiteByCode(code);
    }

}
