package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;
import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

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
        count(site.code, site.name, ip, version);
    }

    public void countEvent(int event_code, String ip, String version)
    {
        Event event = ofy().load().type(Event.class)
                .id(event_code)
                .now();

        count(event_code, "[E] " + event.info[0].title, ip, version);
    }

    private void count(int code, String label, String ip, String version)
    {
        synchronized (this) {
            SiteStats siteStats = getSiteStats(code, label);
            siteStats.monthRequests++;
            siteStats.totalRequests++;
            siteStats.lastRequest = System.currentTimeMillis();
            siteStats.lastIp = ip;
            siteStats.fromVersion = version;
            ofy().save().entity(siteStats).now();
        }
        timeStats.count();
        monthStats.count();
    }

    public void read(Site site, int times)
    {
        synchronized (this) {
            SiteStats siteStats = getSiteStats(site.code, site.name);
            siteStats.readings += times;
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

        List<SiteStats> siteStats = ofy().load().type(SiteStats.class).list();
        for (SiteStats stats : siteStats)
            stats.monthRequests = 0;

        ofy().save().entities(siteStats).now();
    }

    public TreeSet<MonthStats> getMonthStats()
    {
        TreeSet<MonthStats> res = new TreeSet<>(new Comparator<MonthStats>() {
            @Override
            public int compare(MonthStats o1, MonthStats o2)
            {
                int yearComparison = Integer.compare(o1.year, o2.year);
                return yearComparison != 0 ? -yearComparison : -Integer.compare(o1.month, o2.month);
            }
        });
        res.addAll(ofy().load().type(MonthStats.class).list());
        return res;
    }

    private static void initializeStats(Statistics stats)
    {
        stats.since = System.currentTimeMillis();
        stats.lastStart = System.currentTimeMillis();
    }

    private SiteStats getSiteStats(int code, String name)
    {
        SiteStats res = ofy().load().type(SiteStats.class)
                .id(code)
                .now();

        if (res == null) {
            res = new SiteStats();
            res.siteName = name;
            res.siteCode = code;
            res.monthRequests = 0;
            res.totalRequests = 0;
            res.readings = 0;
            res.lastRequest = 0;
            res.lastIp = "";
            res.fromVersion = "";
        }

        return res;
    }

    public TimeStats getDistribution()
    {
        return timeStats;
    }

}
