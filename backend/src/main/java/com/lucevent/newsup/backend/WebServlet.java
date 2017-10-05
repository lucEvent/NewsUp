package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.Event;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class WebServlet extends HttpServlet {

    @Override
    public void init() throws ServletException
    {
        super.init();
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

        if (req.getParameter("index") != null) {

            resp_headers(
                    req.getParameter("site"),
                    req.getRemoteAddr(),
                    resp
            );

        } else if (req.getParameter("content") != null) {

            resp_content(
                    Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site"))),
                    Integer.parseInt(req.getParameter("nid")),
                    resp
            );

        } else if (req.getParameter("sections") != null) {

            resp_sections(
                    Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site"))),
                    resp
            );

        } else if (req.getParameter("stats") != null) {

            resp_stats(
                    req.getParameter("options"),
                    req.getParameter("only"),
                    req.getParameter("reset"),
                    resp
            );

        } else if (req.getParameter("reports") != null) {

            resp_reports(
                    req.getParameter("user"),
                    req.getParameter("pass"),
                    resp
            );

        } else if (req.getParameter("stats_distribution") != null) {

            resp_load(resp);

        } else if (req.getParameter("sites") != null) {
            resp_sites(resp);
        } else if (req.getParameter("add_event") != null) {

            resp_addEvent(
                    req.getParameter("data"),
                    resp);

        }
    }

    private void resp_headers(String site_request, String address, HttpServletResponse resp) throws IOException
    {
        String[] parts = site_request.split(",");

        Site site = Data.sites.getSiteByCode(Integer.parseInt(parts[0]));
        Data.stats.count(site, address, "web");

        int[] section_codes = new int[parts.length - 1];
        Sections sections = site.getSections();
        for (int i = 0; i < section_codes.length; i++) {
            int section_index = Integer.parseInt(parts[i + 1]);
            if (section_index < sections.size())
                section_codes[i] = sections.get(section_index).code;
        }

        NewsArray news = site.readNewsHeaders(section_codes);
        site.news.addAll(news);

        resp.getWriter().println(BackendParser.toHtml(news).toString());
    }

    private void resp_content(Site site, int news_id, HttpServletResponse resp) throws IOException
    {
        News prey = site.news.get(news_id);
        if (prey != null) {

            if (prey.content.isEmpty())
                site.readNewsContent(prey);

            resp.getWriter().print(site.getStyle() + prey.content);
        }
    }

    private void resp_sites(HttpServletResponse resp) throws IOException
    {
        resp.setContentType("json");
        resp.getWriter().println(BackendParser.jsonSites());
    }

    private void resp_sections(Site site, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("json");
        resp.getWriter().println(BackendParser.json(site.getSections()));
    }

    private void resp_stats(String options, String filters, String reset, HttpServletResponse resp) throws IOException
    {
        if (reset != null)
            Data.stats.reset();

        StringBuilder sb = BackendParser
                .toHtml(Data.stats,
                        options != null ? options : "",
                        filters != null && !filters.isEmpty() ? filters.split(",") : null);

        resp.getWriter().println(sb);
    }

    private void resp_reports(String user, String pass, HttpServletResponse resp) throws IOException
    {
        if (user.hashCode() == Reports.VALID_USERNAME && pass.hashCode() == Reports.VALID_PASSWORD)
            resp.getWriter().println(Data.reports.getHtmlReports());
        else
            resp.getWriter().println("Wrong username or password");
    }

    private void resp_load(HttpServletResponse resp) throws IOException
    {
        resp.setContentType("json");
        resp.getWriter().println(Data.stats.getDistribution());
    }

    private void resp_addEvent(String event_data, HttpServletResponse resp) throws IOException
    {
        try {
            Event event = Event.parse(event_data);
            if (event != null) {
                ofy().save().entity(event).now();
                resp.getWriter().println("Event saved successfully (server)");
            } else
                resp.getWriter().println("Unknown error");
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

}

