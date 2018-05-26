package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.Event;
import com.lucevent.newsup.backend.utils.Report;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DevelopmentServer extends HttpServlet {

    @Override
    public void init() throws ServletException
    {
        super.init();
        new Data();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        if (req.getParameter("stats") != null) {

            resp_stats(
                    req.getParameter("options"),
                    req.getParameter("only"),
                    req.getParameter("reset"),
                    resp
            );

        } else if (req.getParameter("reports") != null) {

            resp_reports(resp);

        } else if (req.getParameter("search_sections") != null) {

            resp_key_sections(req.getParameter("key").split(","), resp);

        } else if (req.getParameter("events") != null) {

            resp_events(resp);

        } else if (req.getParameter("add_event") != null) {

            resp_addEvent(
                    req.getParameter("data"),
                    resp);

        } else if (req.getParameter("remove_event") != null) {

            resp_removeEvent(
                    Long.parseLong(req.getParameter("code")),
                    resp);

        } else if (req.getParameter("clear") != null) {

            for (Site site : Data.sites)
                site.news.clear();

        } else if (req.getParameter("debugsite") != null) {

            String[] request = req.getParameter("site").split(",");

            Site site = Data.getSite(Integer.parseInt(request[0]));

            int[] section_codes = new int[request.length - 1];
            for (int i = 0; i < section_codes.length; i++)
                section_codes[i] = Integer.parseInt(request[i + 1]);

            NewsArray news = site.readNewsHeaders(section_codes);

            resp.getWriter().println(BackendParser.toEntry(news).toString());

            resp.getWriter().println("##  \n Extra info \n");

            resp.getWriter().println("section_codes.length=" + section_codes.length);
            //
            int c = 0;
            Sections sections = site.getSections();
            for (int section_code : section_codes) {
                Section section = sections.getSectionByCode(section_code);
                if (section == null || section.url == null)
                    continue;

                c++;
            }
            //
            resp.getWriter().println("sections checked=" + c);

            if (news.isEmpty() && c > 0) {
                Section section = sections.getSectionByCode(section_codes[0]);
                resp.getWriter().println("url->" + section.url);

                String result;
                try {
                    result = org.jsoup.Jsoup.connect(section.url)
                            .timeout(10000)
                            // .userAgent(USER_AGENT)
                            .followRedirects(true)

                            .header("Accept-Encoding", "gzip, deflate, br")
                            .header("Accept-Language", "es-ES,es;q=0.9,en;q=0.8")
                            .header("Cache-Control", "max-age=0")
                            .header("Cookie", "accessec=15022931512a4643caef9f3e4a5f007970652b02ca; visid_incap_661214=sjdBFAdCQ/eYlUWYpvr2e6Asi1kAAAAAQUIPAAAAAAAHuY/W5FT1egljEUHWwetd; _ga=GA1.2.2137369746.1502293153; mp_vilynx=%7B%22distinct_id%22%3A%20%2215e0b92652e14f-0ba80ee6c563f3-c313760-100200-15e0b92652f1b3%22%2C%22%24search_engine%22%3A%20%22google%22%2C%22%24initial_referrer%22%3A%20%22https%3A%2F%2Fwww.google.es%2F%22%2C%22%24initial_referring_domain%22%3A%20%22www.google.es%22%7D; visitedsections_ec=eyIyNCI6MSwiMjE0IjoxLCIxIjoxMCwiMzE4IjoxLCI4Ijo0LCI3IjoxfQ%3D%3D; powersections_ec=eyIyNCI6MSwiMjE0IjoxLCIxIjoxMCwiMzE4IjoxLCI4Ijo0LCI3IjoxfQ%3D%3D; ___tg_vis=7B8554B2AC5B2965.1522923070029; ___tg_vis_sec=170:1522923070029; amplitude_idelconfidencial.com=eyJkZXZpY2VJZCI6IjhjYWQ0ZDc2LTYxNTQtNDkxNi1iOTEwLWE3YTg1OTA2MjI5Y1IiLCJ1c2VySWQiOm51bGwsIm9wdE91dCI6ZmFsc2UsInNlc3Npb25JZCI6MTUyMjkyMzA2ODg2NCwibGFzdEV2ZW50VGltZSI6MTUyMjkyMzA2ODg5NSwiZXZlbnRJZCI6MjQsImlkZW50aWZ5SWQiOjQ4LCJzZXF1ZW5jZU51bWJlciI6NzJ9; PHPSESSID=4f9eek2tpbkip1bggqpv8t78g1; incap_ses_509_661214=XCEVFbU5STt1FxKNjFUQBwbk2FoAAAAAsce8DDzoGHeOj/Gz7GOuVw==")
                            .header("Upgrade-Insecure-Requests", "1")
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                            .referrer("https://www.google.com/")

                            .get().outerHtml();

                    //     resp.getWriter().println("status.code->"+response.statusCode());
                    //    resp.getWriter().println("status.msg->"+response.statusMessage());
                    resp.getWriter().println("result->" + result);
                    resp.getWriter().println("Testing again without errors");
                } catch (Exception e) {
                    resp.getWriter().println("#Error#");
                    resp.getWriter().println("name->" + e.getClass().getSimpleName());
                    resp.getWriter().println("mesg->" + e.getMessage());


                    StackTraceElement[] trace = e.getStackTrace();
                    for (StackTraceElement traceElement : trace)
                        resp.getWriter().println("\tat " + traceElement);


                }
                //     resp.getWriter().println(result);
            }
            //
            //      resp.getWriter().println("");
            //    resp.getWriter().println("");

        }
    }

    private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0.1; GT-I9300 Build/MOB30Z) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36";


    private void resp_stats(String options, String filters, String reset, HttpServletResponse resp) throws IOException
    {
        if (reset != null)
            Data.stats.reset();

        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append(BackendParser.json(Data.stats.getMonthStats()))
                .append(",")
                .append(BackendParser.json(
                        Data.stats,
                        options != null ? options : "",
                        filters != null && !filters.isEmpty() ? filters.split(",") : null))
                .append(",")
                .append(BackendParser.json(Data.stats.getDistribution()))
                .append("}");

        resp.setContentType("json");
        resp.getWriter().println(sb);
    }

    private void resp_reports(HttpServletResponse resp) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<content>");

        Reports reports = new Reports();
        for (Report r : reports)
            sb.append("<report ti='").append(r.time)
                    .append("' ve='").append(r.appVersion)
                    .append("' ip='").append(r.ip)
                    .append("' em='").append(r.email)
                    .append("'><message><![CDATA[").append(r.message)
                    .append("]]></message></report>");

        sb.append("</content>");
        resp.getWriter().println(sb);
    }

    private void resp_key_sections(String[] keys, HttpServletResponse resp) throws IOException
    {
        for (int i = 0; i < keys.length; i++)
            keys[i] = keys[i].toLowerCase();

        StringBuilder sb = new StringBuilder("[");
        for (Site s : Data.sites) {
            Sections sections = s.getSections();
            for (int i = 0; i < sections.size(); i++) {
                Section sec = sections.get(i);
                for (String key : keys) {
                    if (key.equals(sec.name.toLowerCase())) {
                        if (sb.length() > 1)
                            sb.append(",");

                        String pname = "";
                        if (sec.level > 0) {
                            int j = i - 1;
                            while (sections.get(j).level > 0)
                                j--;
                            pname = sections.get(j).name;
                        }
                        sb.append("{\"level\":").append(sec.level)
                                .append(",\"name\":\"").append(sec.name)
                                .append("\",\"code\":").append(sec.code)
                                .append(",\"pname\":\"").append(pname)
                                .append("\",\"scode\":").append(s.code)
                                .append(",\"sname\":\"").append(s.name)
                                .append("\"}");
                    }
                }
            }
        }
        sb.append("]");
        resp.getWriter().println(sb);
    }

    private void resp_events(HttpServletResponse resp) throws IOException
    {
        List<Event> events = ofy().load().type(Event.class).list();
        StringBuilder sb = new StringBuilder("<data>");
        for (Event E : events) {
            sb.append("<event code='")
                    .append(E.code)
                    .append("' visible='")
                    .append(E.visible)
                    .append("' imgsrc='")
                    .append(E.imgSrc)
                    .append("' start='")
                    .append(E.startTime)
                    .append("' end='")
                    .append(E.endTime)
                    .append("' sources='");

            for (int i = 0; i < E.sites.length; i++) {
                if (i != 0) sb.append(";");
                Event.EventSite s = E.sites[i];
                sb.append(s.site_code);
                for (int is : s.section_codes)
                    sb.append(",").append(is);
            }

            sb.append("' tags='");
            for (int i = 0; i < E.tags.length; i++) {
                if (i != 0) sb.append(",");
                sb.append(E.tags[i]);
            }

            sb.append("'>");

            for (int i = 0; i < E.info.length; i++) {
                Event.EventInfo eventInfo = E.info[i];
                sb.append("<description lang='")
                        .append(eventInfo.lang)
                        .append("' title=\"")
                        .append(eventInfo.title)
                        .append("\" topic=\"")
                        .append(eventInfo.topic)
                        .append("\"/>");
            }
            sb.append("</event>");
        }
        sb.append("</data>");

        resp.getWriter().println(sb.toString());
    }

    private void resp_addEvent(String event_data, HttpServletResponse resp) throws IOException
    {
        try {
            Event newEvent = Event.parse(event_data);
            if (newEvent != null) {
                Event e = ofy().load().type(Event.class).id(newEvent.code).now();
                if (e == null) {
                    ofy().save().entity(newEvent).now();
                    resp.getWriter().println("Event saved successfully");
                } else {
                    e.visible = newEvent.visible;
                    e.imgSrc = newEvent.imgSrc;
                    e.startTime = newEvent.startTime;
                    e.endTime = newEvent.endTime;
                    e.tags = newEvent.tags;
                    e.info = newEvent.info;
                    e.sites = newEvent.sites;
                    ofy().save().entity(e).now();
                    resp.getWriter().println("Event updated successfully");
                }
            } else
                resp.getWriter().println("Unknown error");
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    private void resp_removeEvent(long event_code, HttpServletResponse resp) throws IOException
    {
        Event e = ofy().load().type(Event.class).id(event_code).now();
        if (e == null) {
            resp.getWriter().println("Event not found");
        } else {
            ofy().delete().type(Event.class).id(event_code).now();
            resp.getWriter().println("Event removed successfully");
        }
    }

}

