package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
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

public class WebServlet extends HttpServlet {

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

            String site_request = req.getParameter("site");

            String[] parts = site_request.split(",");

            Site site = Data.sites.getSiteByCode(Integer.parseInt(parts[0]));
            Data.stats.count(site, req.getRemoteAddr(), "web");

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

        } else if (req.getParameter("content") != null) {

            Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));

            News prey = site.news.get(Integer.parseInt(req.getParameter("nid")));
            if (prey != null) {

                if (prey.content.isEmpty())
                    site.readNewsContent(prey);

                resp.getWriter().print(site.getStyle() + prey.content);
            }

        } else if (req.getParameter("sections") != null) {

            String s_site = req.getParameter("site");

            Site site = Data.sites.getSiteByCode(Integer.parseInt(s_site));

            resp.getWriter().println(BackendParser.toHtml(site.getSections()).toString());

        } else if (req.getParameter("stats") != null) {

            if (req.getParameter("reset") != null)
                Data.stats.reset();

            String options = req.getParameter("options");
            String filters = req.getParameter("only");
            StringBuilder sb = BackendParser
                    .toHtml(Data.stats,
                            options != null ? options : "",
                            filters != null && !filters.isEmpty() ? filters.split(",") : null);

            resp.getWriter().println(sb);

        } else if (req.getParameter("reports") != null) {

            int user = req.getParameter("user").hashCode();
            int pass = req.getParameter("pass").hashCode();

            if (user == Reports.VALID_USERNAME && pass == Reports.VALID_PASSWORD)
                resp.getWriter().println(Data.reports.getHtmlReports());

        } else if (req.getParameter("stats_distribution") != null) {

            resp.setContentType("json");
            resp.getWriter().println(Data.stats.getDistribution());

        }
    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        new Data();
    }

}

