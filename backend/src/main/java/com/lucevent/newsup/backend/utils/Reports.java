package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.util.Date;

import java.util.Comparator;
import java.util.TreeSet;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Reports extends TreeSet<Report> {

    public static final int VALID_USERNAME = -1204752003;
    public static final int VALID_PASSWORD = 340261560;

    public Reports()
    {
        super(new Comparator<Report>() {
            @Override
            public int compare(Report o1, Report o2)
            {
                return -Long.compare(o1.time, o2.time);
            }
        });
    }

    public String getHtmlReports()
    {
        load();

        StringBuilder sb = new StringBuilder();
        for (Report r : this) {
            sb.append("<div class='report'><span class='from'>")
                    .append(r.email).append(" [").append(r.ip)
                    .append("]</span><span class='time'>")
                    .append(Date.getAge(r.time))
                    .append("</span><span class='version'>")
                    .append(r.appVersion)
                    .append("</span><span class='message'>")
                    .append(r.message.replace("<", "&lt").replace(">", "&gt").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;"))
                    .append("</span></div>");
        }
        return sb.toString();
    }

    public Reports getReports()
    {
        load();
        return this;
    }

    private void load()
    {
        if (isEmpty()) {
            addAll(ofy().load().type(Report.class).list());
        }
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

        this.clear();
    }

    public void deleteReport(long id)
    {
        Report r = ofy().load().type(Report.class).filter("id", id).first().now();
        if (r != null) {
            ofy().delete().entity(r);
        }

        this.clear();
    }

}
