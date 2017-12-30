package com.lucevent.newsup.backend.utils;

import java.util.Comparator;
import java.util.TreeSet;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Reports extends TreeSet<Report> {

    public Reports()
    {
        super(new Comparator<Report>() {
            @Override
            public int compare(Report o1, Report o2)
            {
                return -Long.compare(o1.time, o2.time);
            }
        });
        addAll(ofy().load().type(Report.class).list());
    }

    public static void addReport(String version, String ip, String email, String message)
    {
        Report r = new Report();
        r.time = System.currentTimeMillis();
        r.appVersion = version;
        r.ip = ip;
        r.email = email;
        r.message = message;

        ofy().save().entity(r);
    }

    public void deleteReport(long id)
    {
        Report r = ofy().load().type(Report.class).filter("id", id).first().now();
        if (r != null)
            ofy().delete().entity(r);
    }

}
