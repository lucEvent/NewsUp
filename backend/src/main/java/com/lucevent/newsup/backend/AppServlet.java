package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.MonthStats;
import com.lucevent.newsup.backend.utils.Report;
import com.lucevent.newsup.backend.utils.SiteStats;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.backend.utils.TimeStats;
import com.lucevent.newsup.backend.utils.UpdateMessageCreator;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Sections;
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
            Site site = Data.getSite(Integer.parseInt(parts[0]));

            if (req.getParameter("nc") == null)
                Data.stats.count(site, req.getRemoteAddr(), req.getParameter("v"));

            if (UpdateMessageCreator.needsUpdate(req.getParameter("v"))) {
                UpdateMessageCreator.generateUpdateNews(site, resp);
                return;
            }

            int[] section_codes = new int[parts.length - 1];
            Sections sections = site.getSections();
            for (int i = 0; i < section_codes.length; i++) {
                int section_index = Integer.parseInt(parts[i + 1]);
                if (section_index < sections.size())
                    section_codes[i] = sections.get(section_index).code;
            }

            NewsArray news = site.readNewsHeaders(section_codes);
            site.news.addAll(news);

            resp.getWriter().println(BackendParser.toEntry(news).toString());

        } else if (req.getParameter("content") != null) {
            Site site = Data.getSite(Integer.parseInt(req.getParameter("site")));

            String link = req.getParameter("l");
            if (link != null) {
                int id = link.hashCode();

                News news = site.news.get(id);
                if (news == null) {
                    news = new News(id, "", link, "", -1, null, -1, -1, -1);
                    news.content = "";
                }
                if (news.content.isEmpty())
                    site.readNewsContent(news);

                if (!news.content.isEmpty()) {
                    resp.getWriter().print(news.content);

                    site.news.put(id, news);
                }
                return;
            }

            String news_id = req.getParameter("nid");
            if (news_id == null) {
                resp.getWriter().print(UpdateMessageCreator.generateContent(site));
                return;
            }

            News prey = site.news.get(Integer.parseInt(news_id));
            if (prey != null) {

                if (prey.content.isEmpty())
                    site.readNewsContent(prey);

                resp.getWriter().print(prey.content);
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
                Site site = Data.getSite(Integer.parseInt(values[i]));
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
        oFactory.register(MonthStats.class);
        oFactory.register(Statistics.class);
        oFactory.register(Report.class);
        oFactory.begin();

        new Data();
    }

}
