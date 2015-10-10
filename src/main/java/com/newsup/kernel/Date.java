package com.newsup.kernel;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Date {

    private static final int SECOND_TIME = 1000;
    private static final int MINUTE_TIME = 60 * SECOND_TIME;
    private static final int HOUR_TIME = 60 * MINUTE_TIME;
    private static final int DAY_TIME = 24 * HOUR_TIME;
    private static final int MONTH_TIME = 30 * DAY_TIME;
    private static final int YEAR_TIME = 365 * DAY_TIME;

    String date;
    private long timemillis;

    public Date(String date) {
        if (date == null) return;
        this.date = date;

        String[] items = date.split(" ");
        if (items.length == 1) {

            if (date.length() == 22) {
                // 2015-10-08T16:29+02:00
                this.date = date.substring(0, 10) + " " + date.substring(11, 16) + ":00";
            } else {
                // 2015-09-03T17:31:08+02:00
                this.date = date.substring(0, 10) + " " + date.substring(11, 19);
            }

        } else if (items.length == 2) {
            //2015-10-03 16:11:21
            this.date = date;
        } else {
            // Sat, 8 Aug 2015 19:48:06
            String day = items[1].length() == 1 ? "0" + items[1] : items[1];

            this.date = items[3] + "-" + monthToIntString(items[2]) + "-" + day + " " + items[4];
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());

        try {
            timemillis = sdf.parse(this.date).getTime();
        } catch (Exception e) {
            debug("Error convirtiendo esta fecha: " + date);
            e.printStackTrace();
        }
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

    @Override
    public String toString() {
        return date;
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


}
