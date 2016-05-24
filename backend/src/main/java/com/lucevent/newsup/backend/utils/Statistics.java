package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Statistics {

    public enum Order {
        ByDefault, BySiteName, ByNumber, ByLastAccessTime
    }

    @Id
    private Long id;

    @Unindex
    public Long since;

    @Ignore
    public Long lastStart;

    @Unindex
    private int[] counters;

    @Unindex
    private long[] lastAccesses;

    @Unindex
    private String[] lastIps;


    public static Statistics initialize(int counterSize)
    {
        LoadType<Statistics> db = ofy().load().type(Statistics.class);

        Statistics statistics;
        if (db.count() > 0) {

            statistics = db.first().now();
            statistics.lastStart = System.currentTimeMillis();

        } else {

            statistics = new Statistics();
            statistics.since = System.currentTimeMillis();
            statistics.lastStart = System.currentTimeMillis();
            statistics.counters = new int[counterSize];
            statistics.lastAccesses = new long[counterSize];
            statistics.lastIps = new String[counterSize];
            for (int i = 0; i < counterSize; i++) {
                statistics.counters[i] = 0;
                statistics.lastAccesses[i] = 0;
                statistics.lastIps[i] = "";
            }

            ofy().save().entity(statistics).now();

        }
        return statistics;
    }

    public void count(int position, String ip)
    {
        counters[position]++;
        lastAccesses[position] = System.currentTimeMillis();
        lastIps[position] = ip;
        ofy().save().entity(this).now();//save()    //possible improvement? save only when the server instance is destroyed
    }

    public int getCount(int position)
    {
        return counters[position];
    }

    public Long getLastAccess(int position)
    {
        return lastAccesses[position];
    }

    public String getLastIp(int position)
    {
        return lastIps[position];
    }

    public void save()
    {
        ofy().save().entity(this).now();
    }

    public void reset(int counterSize)
    {
        since = System.currentTimeMillis();
        lastStart = System.currentTimeMillis();
        counters = new int[counterSize];
        for (int i = 0; i < counters.length; i++) {
            counters[i] = 0;
            lastAccesses[i] = 0;
            lastIps[i] = "";
        }
        ofy().save().entity(this).now();
    }

}
