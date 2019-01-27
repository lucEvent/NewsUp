package com.lucevent.newsup.debugbackend.net;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Net {

	public static void sendReport(String title, String message)
	{
		try {
			Connection.Response response = Jsoup.connect("http://newsup-2406.appspot.com/appv3?report")
					.method(Connection.Method.POST)
					.postDataCharset("UTF-8")
					.data("version", title, "email", "lucenalabs", "message", message)
					.execute();
		} catch (Exception ignored) {
		}
	}

	public static void sendStatus(int code, String name, int info,
	                              long check_time, long check_duration, long check_rounds,
	                              long num_news, long with_content)
	{
		try {
			Connection.Response response = Jsoup.connect("http://newsup-2406.appspot.com/dev?new_status")
					.method(Connection.Method.POST)
					.postDataCharset("UTF-8")
					.data("code", Integer.toString(code), "name", name, "info", Integer.toString(info),
							"check_time", Long.toString(check_time), "check_duration", Long.toString(check_duration), "check_rounds", Long.toString(check_rounds),
							"num_news", Long.toString(num_news), "no_content", Long.toString(with_content)
					)
					.execute();
		} catch (Exception ignored) {
		}
	}

}
