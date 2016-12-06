package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.Report;
import com.lucevent.newsup.backend.utils.SiteStats;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.backend.utils.TimeStats;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppServlet extends HttpServlet {

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

        if (req.getParameter("news") != null) {

            String site_request = req.getParameter("site");

            String[] parts = site_request.split(",");

            Site site = Data.sites.getSiteByCode(Integer.parseInt(parts[0]));
            Site sitev2 = Data.sitesV2.getSiteByCode(Integer.parseInt(parts[0]));
            if (req.getParameter("nc") == null)
                Data.stats.count(site, req.getRemoteAddr(), req.getParameter("v"));

            int[] sections = new int[parts.length - 1];
            for (int i = 0; i < sections.length; i++) {
                sections[i] = Integer.parseInt(parts[i + 1]);
            }

            NewsArray news = site.readNewsHeaders(sections);
            site.news.addAll(news);
            sitev2.news.addAll(news);

            resp.getWriter().println(BackendParser.toEntry(news).toString());

        } else if (req.getParameter("content") != null) {

            String snid = req.getParameter("nid");
            if (snid != null) {
                Site site = Data.sitesV2.getSiteByCode(Integer.parseInt(req.getParameter("site")));

                News bait = new News(-1, "", "", "", 0, null);
                bait.server_id = Long.parseLong(req.getParameter("nid"));

                News prey = site.news.ceiling(bait);
                if (prey != null && prey.server_id == bait.server_id) {

                    if (prey.content == null || prey.content.isEmpty())
                        site.readNewsContent(prey);

                    resp.getWriter().print(prey.content == null ? "" : prey.content);
                }
                return;
            }

            Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");
            long date = Long.parseLong(req.getParameter("date"));

            News bait = new News("", link, "", date, null);
            News prey = site.news.ceiling(bait);

            if (prey != null && prey.compareTo(bait) == 0) {

                if (prey.content == null || prey.content.isEmpty())
                    site.readNewsContent(prey);

                resp.getWriter().print(prey.content == null ? "" : prey.content);
            }

        } else if (req.getParameter("stats") != null) {

            if (req.getParameter("reset") != null)
                Data.stats.reset();

            String options = req.getParameter("options");
            StringBuilder sb = new StringBuilder();
            sb.append(BackendParser.toEntry(Data.stats, options != null ? options : ""));

            resp.getWriter().println(sb);

        } else if (req.getParameter("notify") != null) {

            String[] values = req.getParameter("values").split(",");
            for (int i = 0; i < values.length; i += 2) {
                Site site = Data.sites.getSiteByCode(Integer.parseInt(values[i]));
                Data.stats.read(site, Integer.parseInt(values[i + 1]));
            }

        } else if (req.getParameter("report") != null) {

            Data.reports.addReport(
                    req.getParameter("version"),
                    req.getRemoteAddr(),
                    req.getParameter("email"),
                    req.getParameter("message"));

        } else if (req.getParameter("clear") != null) {

            for (Site site : Data.sites)
                site.news.clear();

        }
    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(SiteStats.class);
        oFactory.register(TimeStats.class);
        oFactory.register(Statistics.class);
        oFactory.register(Report.class);
        oFactory.begin();

        new Data();
    }

}
