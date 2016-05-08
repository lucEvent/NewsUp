package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;

import java.util.Date;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Statistics {

    public enum Order {
        ByDefault, BySiteName, ByNumber, ByLastAccessTime
    }

    @Id
    private Long id;

    @Unindex
    public String since;

    @Ignore
    public String lastStart;

    @Unindex
    private int[] counters;

    public static Statistics initialize(int counterSize)
    {
        LoadType<Statistics> db = ofy().load().type(Statistics.class);

        Statistics statistics;
        if (db.count() > 0) {

            statistics = db.first().now();
            statistics.lastStart = "[ Last start " + new Date().toString() + " ]";

        } else {

            statistics = new Statistics();
            statistics.since = "[ Since " + new Date().toString() + " ]";
            statistics.lastStart = "[ Last start " + new Date().toString() + " ]";
            statistics.counters = new int[counterSize];
            for (int i = 0; i < counterSize; i++)
                statistics.counters[i] = 0;

            ofy().save().entity(statistics).now();

        }
        return statistics;
    }

    public void count(int position)
    {
        counters[position]++;
        ofy().save().entity(this).now();//save()    //possible improvement? save only when the server instance is destroyed
    }

    public int getCount(int position)
    {
        return counters[position];
    }


    public void save()
    {
        ofy().save().entity(this).now();
    }

    public void reset()
    {
        since = "[ Since " + new Date().toString() + " ]";
        lastStart = "[ Last start " + new Date().toString() + " ]";
        for (int i = 0; i < counters.length; i++)
            counters[i] = 0;
        ofy().save().entity(this).now();
    }

}
