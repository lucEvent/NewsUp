package com.lucevent.newsup.backend.utils;

import java.util.Calendar;

public class Statistics {

    public final String since;
    private int[] counters;


    public Statistics(int counterSize)
    {
        counters = new int[counterSize];
        for (int i = 0; i < counterSize; i++)
            counters[i] = 0;

        Calendar calendar = Calendar.getInstance();
        since = "[ Since " + calendar.getTime().toString() + " ]";
    }

    public void count(int position)
    {
        counters[position]++;
    }

    public int getCount(int position)
    {
        return counters[position];
    }

}
