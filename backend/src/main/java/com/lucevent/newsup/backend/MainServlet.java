package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

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
            Data.stats.count(Data.sites.indexOf(site), req.getRemoteAddr());

            int[] sections = new int[parts.length - 1];
            for (int i = 0; i < sections.length; i++) {
                sections[i] = Integer.parseInt(parts[i + 1]);
            }

            NewsArray news = site.readNewsHeaders(sections);
            site.news.addAll(news);

            resp.getWriter().println(BackendParser.toEntry(news).toString());

        } else if (req.getParameter("content") != null) {

            Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");
            long date = Long.parseLong(req.getParameter("date"));

            News bait = new News("", link, "", date, null);
            News prey = site.news.ceiling(bait);

            if (prey != null && prey.compareTo(bait) == 0) {

                if (prey.content == null || prey.content.isEmpty()) {
                    site.readNewsContent(prey);
                }

                if (prey.content == null) {
                    resp.getWriter().println("");
                } else {
                    resp.getWriter().println(prey.content);
                }
            }
        } else if (req.getParameter("stats") != null) {

            if (req.getParameter("reset") != null)
                Data.stats.reset(Data.sites);

            String options = req.getParameter("options");
            StringBuilder sb = new StringBuilder();
            sb.append(BackendParser.toEntry(Data.stats, options != null ? options : ""));

            resp.getWriter().println(sb);

        } else if (req.getParameter("debug") != null) {
/*
            Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");

            News N = new News("", link, "", 0, null);

            site.readNewsContent(N);

            resp.getWriter().println(N.content);
*/
        } else if (req.getParameter("clear") != null) {

            for (Site site : Data.sites)
                site.news.clear();

        }

    }

    @Override
    public void init() throws ServletException
    {
        super.init();
    }

}
