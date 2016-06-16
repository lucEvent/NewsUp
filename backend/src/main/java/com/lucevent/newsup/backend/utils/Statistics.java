package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Statistics {

    @Id
    private Long id;

    @Unindex
    public Long since;

    @Ignore
    public Long lastStart;

    @Unindex
    private SiteStat[] siteStats;


    public static Statistics initialize(Sites sites)
    {
        LoadType<Statistics> db = ofy().load().type(Statistics.class);

        Statistics statistics;
        if (db.count() > 0) {

            statistics = db.first().now();
            statistics.lastStart = System.currentTimeMillis();

        } else {

            statistics = new Statistics();
            initializeStats(statistics, sites);

            ofy().save().entity(statistics).now();

        }
        return statistics;
    }

    public void count(int position, String ip)
    {
        siteStats[position].nAccesses++;
        siteStats[position].lastAccess = System.currentTimeMillis();
        siteStats[position].lastIp = ip;
        ofy().save().entity(this).now();//save()    //possible improvement? save only when the server instance is destroyed
    }

    public SiteStat[] getSiteStats()
    {
        return siteStats;
    }

    public void save()
    {
        ofy().save().entity(this).now();
    }

    public void reset(Sites sites)
    {
        initializeStats(this, sites);
        ofy().save().entity(this).now();
    }

    private static void initializeStats(Statistics stats, Sites sites)
    {
        stats.since = System.currentTimeMillis();
        stats.lastStart = System.currentTimeMillis();
        stats.siteStats = new SiteStat[sites.size()];
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            stats.siteStats[i] = new SiteStat();
            stats.siteStats[i].siteName = site.name;
            stats.siteStats[i].siteCode = site.code;
            stats.siteStats[i].nAccesses = 0;
            stats.siteStats[i].lastAccess = 0;
            stats.siteStats[i].lastIp = "";
        }
    }

}
