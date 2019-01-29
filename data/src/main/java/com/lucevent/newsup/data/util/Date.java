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
		msg_seconds = texts[0];
		msg_minute = texts[1];
		msg_hour = texts[2];
		msg_day = texts[3];
		msg_month = texts[4];
		msg_year = texts[5];
	}

	public static long toDate(String date)
	{
		if (date.isEmpty()) return -1;
		String temp;
		long timemillis = 0;
		long zoneOffset = 0;

		String[] items = date.split(" ");
		if (items.length == 1) {

			if (date.length() == 10) {
				// 2019-01-29
				temp = date + " 00:00:00";
				zoneOffset = 0;

			} else if (date.length() == 22) {
				// 2015-10-08T16:29+02:00
				temp = date.substring(0, 10) + " " + date.substring(11, 16) + ":00";
				zoneOffset = 0;
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
			if (items.length >= 6)
				zoneOffset = estimateZoneOffset(items[5]);

			if (items[4].length() == 5)
				items[4] = items[4] + ":00";

			String day = items[1].length() == 1 ? "0" + items[1] : items[1];

			temp = items[3] + "-" + monthToIntString(items[2]) + "-" + day + " " + items[4];
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		try {
			timemillis = sdf.parse(temp).getTime();
		} catch (Exception e) {
			System.out.println("Error converting date: " + date + " [gotten:" + temp + "]");
			e.printStackTrace();
		}
		timemillis += zoneOffset;
		return timemillis;
	}

	private static final String[] eng_months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private static final String[] esp_months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

	private static String monthToIntString(String month)
	{
		for (int i = 0; i < eng_months.length; i++) {
			if (month.equals(eng_months[i]) || month.equals(esp_months[i])) {
				if (i + 1 < 10)
					return "0" + (i + 1);

				return "" + (i + 1);
			}
		}
		return "";
	}

	private static long estimateZoneOffset(String zone)
	{
		if (zone.length() <= 3) {
			if (zone.equals("GMT") || zone.equals("Z")) return 0;
			if (zone.equals("PDT")) return 7 * HOUR_TIME;
			if (zone.equals("PST")) return 8 * HOUR_TIME;
			if (zone.equals("CET")) return -HOUR_TIME;
			if (zone.equals("EDT")) return 4 * HOUR_TIME;
			if (zone.equals("EST")) return 5 * HOUR_TIME;
			System.out.println("Date zone unexpected: " + zone);
			return 0;
		}
		boolean plus = zone.charAt(0) == '+';
		long hourmillis = Long.parseLong(zone.substring(1, 3)) * HOUR_TIME;
		return plus ? -hourmillis : hourmillis;
	}

	public static String getAge(long date)
	{
		long age = System.currentTimeMillis() - date;

		if (age < 0) {
			System.out.println("Negative age:" + date);
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

		if (date <= 0)
			return "";

		return String.format(Locale.ENGLISH, msg_year, (age / YEAR_TIME));
	}

}
