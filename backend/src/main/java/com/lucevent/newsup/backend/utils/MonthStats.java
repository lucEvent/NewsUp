package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class MonthStats {

    private static final String[] MONTH_TO_STRING = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Id
    @Index
    public String id;

    @Unindex
    public int month;

    @Unindex
    public int year;

    @Unindex
    public long counter;

    public static MonthStats getInstance()
    {
        Calendar calendar = new GregorianCalendar();
        String month = calendar.get(Calendar.YEAR) + "-" + MONTH_TO_STRING[calendar.get(Calendar.MONTH)];

        MonthStats monthStats = ofy().load().type(MonthStats.class).id(month).now();

        if (monthStats == null) {

            monthStats = new MonthStats();
            monthStats.id = month;
            monthStats.month = calendar.get(Calendar.MONTH)+1;
            monthStats.year = calendar.get(Calendar.YEAR);
            monthStats.counter = 0;

            ofy().save().entity(monthStats).now();
        }
        return monthStats;
    }

    public MonthStats()
    {
    }

    public void count()
    {
        counter++;
        ofy().save().entity(this).now();
    }

}
