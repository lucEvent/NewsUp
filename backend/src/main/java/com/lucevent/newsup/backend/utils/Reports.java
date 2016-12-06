package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.util.Date;

import java.util.Comparator;
import java.util.TreeSet;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Reports {

    public static final int VALID_USERNAME = -1204752003;
    public static final int VALID_PASSWORD = 340261560;

    public Reports()
    {
    }

    public String getHtmlReports()
    {
        TreeSet<Report> reports = new TreeSet<>(new Comparator<Report>() {
            @Override
            public int compare(Report o1, Report o2)
            {
                return -Long.compare(o1.time, o2.time);
            }
        });
        reports.addAll(ofy().load().type(Report.class).list());

        StringBuilder sb = new StringBuilder();
        for (Report r : reports) {
            sb.append("<div class='report'><span class='from'>")
                    .append(r.email).append(" [").append(r.ip)
                    .append("]</span><span class='time'>")
                    .append(Date.getAge(r.time))
                    .append("</span><span class='version'>")
                    .append(r.appVersion)
                    .append("</span><span class='message'>")
                    .append(r.message.replace("<", "&lt").replace(">", "&gt").replace("\n", "<br>").replace("\t","&nbsp;&nbsp;"))
                    .append("</span></div>");
        }
        return sb.toString();
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
