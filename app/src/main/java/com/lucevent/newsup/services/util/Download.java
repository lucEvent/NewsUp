package com.lucevent.newsup.services.util;

import java.io.Serializable;

public class Download extends Schedule implements Serializable {

    public static String[] s_days;

    public final int id;

    //whether the download schedule repeats every week (true) or no (false)
    public boolean repeat;

    public boolean notify;

    public int hour, minute;

    public boolean[] days;

    public int[] sites_codes;

    public Download(int id)
    {
        this.id = id;
    }

    public Download(int id, int hour, int minute, boolean notify, boolean repeat,
                    boolean[] days, int[] sites_codes)
    {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.notify = notify;
        this.repeat = repeat;
        this.days = days;
        this.sites_codes = sites_codes;
    }

    public String toShortString()
    {
        return timeString() + " - " + daysString();
    }

    public String timeString()
    {
        return (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
    }

    public String daysString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.length; ++i)
            if (days[i]) {
                if (sb.length() != 0) sb.append(", ");
                sb.append(s_days[i]);
            }
        return sb.toString();
    }

    public boolean isEvent()
    {
        return id <= -10000;
    }

}
