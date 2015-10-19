package com.newsup.kernel.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Date {

    private static final long SECOND_TIME = 1000;
    private static final long MINUTE_TIME = 60 * SECOND_TIME;
    private static final long HOUR_TIME = 60 * MINUTE_TIME;
    private static final long DAY_TIME = 24 * HOUR_TIME;
    private static final long MONTH_TIME = 30 * DAY_TIME;
    private static final long YEAR_TIME = 365 * DAY_TIME;

    private long timemillis;

    public Date(Long time) {
        timemillis = time;
    }

    public Date(String date) {
        if (date == null) return;
        String temp;
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
            debug("Error convirtiendo esta fecha: " + date);
            e.printStackTrace();
        }
        timemillis += zoneOffset;
    }

    public static int compare(Date date1, Date date2) {
        return Long.compare(date1.timemillis, date2.timemillis);
    }

    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private String monthToIntString(String month) {
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

    private long estimateZoneOffset(String s) {
        if (s.length() <= 3) {
            if (s.equals("GMT")) return 0;
            if (s.equals("PDT")) return 7 * HOUR_TIME;
            if (s.equals("PST")) return 8 * HOUR_TIME;
            if (s.equals("CET")) return -HOUR_TIME;
            debug("El zone offset no es el que se esperaba: " + s);
            return 0;
        }
        boolean plus = s.charAt(0) == '+' ? true : false;
        long hourmillis = Long.parseLong(s.substring(1, 3)) * HOUR_TIME;
        return plus ? -hourmillis : hourmillis;
    }

    public String getAge() {
        long age = System.currentTimeMillis() - timemillis;
        if (age < MINUTE_TIME) {
            return age / SECOND_TIME + " segundos";
        }
        if (age < HOUR_TIME) {
            return age / MINUTE_TIME + " minutos";
        }
        if (age < DAY_TIME) {
            return age / HOUR_TIME + " horas";
        }
        if (age < MONTH_TIME) {
            return age / DAY_TIME + " días";
        }
        if (age < YEAR_TIME) {
            return age / MONTH_TIME + " meses";
        }
        return age / YEAR_TIME + " años";
    }

    private void debug(String text) {
        Log.d("##DATE##", text);
    }

    public long getValue() {
        return timemillis;
    }
}
