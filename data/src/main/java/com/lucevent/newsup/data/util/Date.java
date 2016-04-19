package com.lucevent.newsup.data.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Date {

    public static final long SECOND_TIME = 1000;
    public static final long MINUTE_TIME = 60 * SECOND_TIME;
    public static final long HOUR_TIME = 60 * MINUTE_TIME;
    public static final long DAY_TIME = 24 * HOUR_TIME;
    public static final long MONTH_TIME = 30 * DAY_TIME;
    public static final long YEAR_TIME = 365 * DAY_TIME;

    private static String msg_seconds;
    private static String msg_minute;
    private static String msg_hour;
    private static String msg_day;
    private static String msg_month;
    private static String msg_year;

    public static void setTitles(String[] texts)
    {
        if (texts != null) {
            msg_seconds = texts[0];
            msg_minute = texts[1];
            msg_hour = texts[2];
            msg_day = texts[3];
            msg_month = texts[4];
            msg_year = texts[5];
        }
    }

    public static long toDate(String date)
    {
        if (date == null) return -1;
        String temp;
        long timemillis = 0;
        long zoneOffset = 0;

        String[] items = date.split(" ");
        if (items.length == 1) {

            if (date.length() == 22) {
                // 2015-10-08T16:29+02:00
                temp = date.substring(0, 10) + " " + date.substring(11, 16) + ":00";
                zoneOffset = estimateZoneOffset(date.substring(16));
            } else {
                // 2015-09-03T17:31:08+02:00
                temp = date.substring(0, 10) + " " + date.substring(11, 19);
                zoneOffset = estimateZoneOffset(date.substring(19));
            }

        } else if (items.length == 2) {
            //2015-10-03 16:11:21
            temp = date;

        } else {
            // Sat, 8 Aug 2015 19:48:06
            //Tue, 13 Oct 2015 18:04:07 +0300 //TODO
            if (items.length >= 6) {
                zoneOffset = estimateZoneOffset(items[5]);
            }
            String day = items[1].length() == 1 ? "0" + items[1] : items[1];

            temp = items[3] + "-" + monthToIntString(items[2]) + "-" + day + " " + items[4];
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            timemillis = sdf.parse(temp).getTime();
        } catch (Exception e) {
            System.out.println("Error convirtiendo esta fecha: " + date);
            e.printStackTrace();
        }
        timemillis += zoneOffset;
        return timemillis;
    }

    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private static String monthToIntString(String month)
    {
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                if (i + 1 < 10) {
                    return "0" + (i + 1);
                }
                return "" + (i + 1);
            }
        }
        return "";
    }

    private static long estimateZoneOffset(String s)
    {
        if (s.length() <= 3) {
            if (s.equals("GMT") || s.equals("Z")) return 0;
            if (s.equals("PDT")) return 7 * HOUR_TIME;
            if (s.equals("PST")) return 8 * HOUR_TIME;
            if (s.equals("CET")) return -HOUR_TIME;
            if (s.equals("EDT")) return 4 * HOUR_TIME;
            if (s.equals("EST")) return 5 * HOUR_TIME;
            System.out.println("El zone offset no es el que se esperaba: " + s);
            return 0;
        }
        boolean plus = s.charAt(0) == '+';
        long hourmillis = Long.parseLong(s.substring(1, 3)) * HOUR_TIME;
        return plus ? -hourmillis : hourmillis;
    }

    public static String getAge(long date)
    {
        long age = System.currentTimeMillis() - date;

        if (age < 0) {
            System.out.println("Edad negativa: millis:" + date);
            return "";
        }
        if (age < MINUTE_TIME)
            return String.format(Locale.ENGLISH, msg_seconds, (age / SECOND_TIME));
        if (age < HOUR_TIME)
            return String.format(Locale.ENGLISH, msg_minute, (age / MINUTE_TIME));
        if (age < DAY_TIME)
            return String.format(Locale.ENGLISH, msg_hour, (age / HOUR_TIME));
        if (age < MONTH_TIME)
            return String.format(Locale.ENGLISH, msg_day, (age / DAY_TIME));
        if (age < YEAR_TIME)
            return String.format(Locale.ENGLISH, msg_month, (age / MONTH_TIME));

        return String.format(Locale.ENGLISH, msg_year, (age / YEAR_TIME));
    }

}
