package com.newsup.kernel;

public class Date {

    String date;
    int day, month, year, time;

    public Date(String date) {
        if (date == null) return;
        this.date = date;

        String[] items = date.split(" ");
        if (items.length == 1) {
            // 2015-09-03T17:31:08+02:00
            year = Integer.parseInt(date.substring(0, 4));
            month = Integer.parseInt(date.substring(5, 7));
            day = Integer.parseInt(date.substring(8, 10));
            time = timeToInt(date.substring(11, 19));

        } else if (items.length == 2) {
            //2015-10-03 16:11:21
            year = Integer.parseInt(date.substring(0, 4));
            month = Integer.parseInt(date.substring(5, 7));
            day = Integer.parseInt(date.substring(8, 10));
            time = timeToInt(items[1]);
        } else {
            // Sat, 8 Aug 2015 19:48:06
            day = Integer.parseInt(items[1]);
            month = monthToInt(items[2]);
            year = Integer.parseInt(items[3]);
            time = timeToInt(items[4]);
        }
    }

    public static int compare(Date date1, Date date2) {
        int result = Integer.compare(date1.year, date2.year);
        if (result == 0) {
            result = Integer.compare(date1.month, date2.month);
            if (result == 0) {
                result = Integer.compare(date1.day, date2.day);
                if (result == 0) {
                    result = Integer.compare(date1.time, date2.time);
                }
            }
        }
        return result;
    }

    private static final String[] months = {"Jan", "Feb", "Mar", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private static int monthToInt(String month) {
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                return i;
            }
        }
        return 0;
    }

    private static int timeToInt(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 3600 + Integer.parseInt(parts[1]) * 60 + Integer.parseInt(parts[2]);
    }

    @Override
    public String toString() {
        return date;
    }
}
