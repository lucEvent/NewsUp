package com.newsup.settings;

public class DownloadScheduleSetting {

    public static final String DOWNLOAD_SCHEDULE = "schedule";

    private static final String TRUE = "T";
    private static final String FALSE = "F";
    private static final String SEPARATOR = ",";
    public static String[] s_days;

    //whether the download schedule repeats every week (true) or no (false)
    public boolean repeat;

    public boolean notify;

    public int hour, minute;

    public boolean[] days;

    public int[] dl_sites_codes;

    public DownloadScheduleSetting() {
    }

    public DownloadScheduleSetting(String compact) {
        String[] elems = compact.split(SEPARATOR);

        repeat = elems[0].equals(TRUE);

        notify = elems[1].equals(TRUE);

        hour = Integer.parseInt(elems[2]);
        minute = Integer.parseInt(elems[3]);

        days = new boolean[7];
        for (int i = 0; i < days.length; ++i) {
            days[i] = elems[i + 4].equals(TRUE);
        }

        dl_sites_codes = new int[elems.length - 11];
        for (int i = 0; i < dl_sites_codes.length; ++i) {
            dl_sites_codes[i] = Integer.parseInt(elems[i + 11]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(repeat ? TRUE : FALSE).append(SEPARATOR);

        sb.append(notify ? TRUE : FALSE).append(SEPARATOR);

        sb.append(hour).append(SEPARATOR).append(minute);

        for (boolean day : days) {
            sb.append(SEPARATOR).append(day ? TRUE : FALSE);
        }

        for (int code : dl_sites_codes) {
            sb.append(SEPARATOR).append(code);
        }

        return sb.toString();
    }

    public String toShortString() {
        return toTimeString() + " - " + toDaysString();
    }

    public String toTimeString() {
        return (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
    }

    public String toDaysString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.length; ++i) {
            if (days[i]) {
                if (sb.length() != 0) sb.append(", ");
                sb.append(s_days[i]);
            }
        }
        return sb.toString();
    }
}
