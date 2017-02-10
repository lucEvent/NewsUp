package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;
import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Statistics {

    @Id
    private Long id;

    @Unindex
    public Long since;

    @Ignore
    public Long lastStart;

    @Ignore
    private TimeStats timeStats;

    @Ignore
    private MonthStats monthStats;

    public static Statistics getInstance()
    {
        LoadType<Statistics> db = ofy().load().type(Statistics.class);

        Statistics statistics;
        if (db.count() > 0) {

            statistics = db.first().now();
            statistics.lastStart = System.currentTimeMillis();

        } else {

            statistics = new Statistics();

            ofy().save().entity(statistics).now();

        }
        statistics.timeStats = TimeStats.getInstance();
        statistics.monthStats = MonthStats.getInstance();

        return statistics;
    }

    private Statistics()
    {
        initializeStats(this);
    }

    public void count(Site site, String ip, String version)
    {
        synchronized (this) {
            SiteStats siteStats = getSiteStats(site);
            siteStats.nAccesses++;
            siteStats.lastAccess = System.currentTimeMillis();
            siteStats.lastIp = ip;
            siteStats.fromVersion = version;
            ofy().save().entity(siteStats).now();
        }
        timeStats.count();
        monthStats.count();
    }

    public void read(Site site, int n)
    {
        synchronized (this) {
            SiteStats siteStats = getSiteStats(site);
            siteStats.nNewsRead += n;
            ofy().save().entity(siteStats).now();
        }
    }

    public ArrayList<SiteStats> getSiteStats()
    {
        return new ArrayList<>(ofy().load().type(SiteStats.class).list());
    }

    public void reset()
    {
        synchronized (this) {
            initializeStats(this);
            ofy().save().entity(this).now();
            ofy().delete().entities(
                    ofy().load().type(SiteStats.class).list()
            );
        }
        timeStats.reset();
    }

    public void newMonth()
    {
        monthStats = MonthStats.getInstance();
    }

    private static void initializeStats(Statistics stats)
    {
        stats.since = System.currentTimeMillis();
        stats.lastStart = System.currentTimeMillis();
    }

    private SiteStats getSiteStats(Site site)
    {
        SiteStats res = ofy().load().type(SiteStats.class)
                .id(site.code)
                .now();

        if (res == null) {
            res = new SiteStats();
            res.siteName = site.name;
            res.siteCode = site.code;
            res.nAccesses = 0;
            res.nNewsRead = 0;
            res.lastAccess = 0;
            res.lastIp = "";
            res.fromVersion = "";
        }

        return res;
    }

    public String getDistribution()
    {
        return timeStats.toString();
    }
}
