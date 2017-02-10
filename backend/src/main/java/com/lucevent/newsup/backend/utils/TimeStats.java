package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class TimeStats {

    @Id
    private Long id;

    @Unindex
    private int[] values; //[][]

    public static TimeStats getInstance()
    {
        LoadType<TimeStats> db = ofy().load().type(TimeStats.class);

        TimeStats timeStats;
        if (db.count() > 0) {

            timeStats = db.first().now();

        } else {

            timeStats = new TimeStats();

            ofy().save().entity(timeStats).now();

        }
        return timeStats;
    }

    private TimeStats()
    {
        initialize(this);
    }

    public void count()
    {
        synchronized (this) {
            Calendar calendar = new GregorianCalendar();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int dayOfTheWeek = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7;

            values[(dayOfTheWeek * 24) + hour]++;

            ofy().save().entity(this).now();
        }
    }

    public void reset()
    {
        synchronized (this) {
            initialize(this);
            ofy().save().entity(this).now();
        }
    }

    private static void initialize(TimeStats timeStats)
    {
        timeStats.values = new int[7 * 24];
        for (int i = 0; i < 7 * 24; i++) {
            timeStats.values[i] = 0;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(values[i]);
            if (i == values.length - 1)
                return b.append(']').toString();
            b.append(", ");
        }
    }

}