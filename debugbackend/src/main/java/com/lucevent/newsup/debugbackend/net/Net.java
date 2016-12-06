package com.lucevent.newsup.debugbackend.net;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Net {

    public static void sendReport(String title, String message)
    {
        try {

            Connection.Response response = Jsoup.connect("http://newsup-2406.appspot.com/app?report")
                    .method(Connection.Method.POST)
                    .postDataCharset("UTF-8")
                    .data("version", title, "email", "lucenalabs", "message", message)
                    .execute();

        } catch (Exception ignored) {
        }
    }

}
