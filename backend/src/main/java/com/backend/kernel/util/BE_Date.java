package com.backend.kernel.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class BE_Date {

    private static final long SECOND_TIME = 1000;
    private static final long MINUTE_TIME = 60 * SECOND_TIME;
    private static final long HOUR_TIME = 60 * MINUTE_TIME;
    private static final long DAY_TIME = 24 * HOUR_TIME;
    private static final long MONTH_TIME = 30 * DAY_TIME;
    private static final long YEAR_TIME = 365 * DAY_TIME;

    public static long toDate(String date) {
        if (date == null || date.length() == 0) return -1;
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
            e.printStackTrace();
        }
        timemillis += zoneOffset;
        return timemillis;
    }


    public static int compare(long date1, long date2) {
        return date1 < date2 ? -1 : (date1 == date2 ? 0 : 1);
    }

    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private static String monthToIntString(String month) {
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

    private static long estimateZoneOffset(String s) {
        if (s.length() <= 3) {
            if (s.equals("GMT") || s.equals("Z")) return 0;
            if (s.equals("PDT")) return 7 * HOUR_TIME;
            if (s.equals("PST")) return 8 * HOUR_TIME;
            if (s.equals("CET")) return -HOUR_TIME;
            if (s.equals("EDT")) return 4 * HOUR_TIME;
            if (s.equals("EST")) return 5 * HOUR_TIME;
            return 0;
        }
        boolean plus = s.charAt(0) == '+';
        long hourmillis = Long.parseLong(s.substring(1, 3)) * HOUR_TIME;
        return plus ? -hourmillis : hourmillis;
    }

}
