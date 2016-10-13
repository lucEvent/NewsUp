package com.lucevent.newsup.backend.utils;

import java.util.ArrayList;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Reports {

    public Reports()
    {
    }

    public ArrayList<Report> getReports()
    {
        return new ArrayList<>(ofy().load().type(Report.class).list());
    }

    public void addReport(String version, String ip, String email, String message)
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
        if (r != null) {
            ofy().delete().entity(r);
        }
    }

}
