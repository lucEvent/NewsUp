package com.lucevent.newsup.backend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.Statistics;
import com.lucevent.newsup.data.util.Date;
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

            StringBuilder sb = new StringBuilder();

            String options = req.getParameter("options");
            if (options != null) {

                if (options.contains("r"))
                    Data.stats.reset(Data.sites.size());

                if (options.contains("s"))
                    sb.append(BackendParser.toHtml(Data.sites, Data.stats, Statistics.Order.BySiteName));

                if (options.contains("n"))
                    sb.append(BackendParser.toHtml(Data.sites, Data.stats, Statistics.Order.ByNumber));

                if (options.contains("t"))
                    sb.append(BackendParser.toHtml(Data.sites, Data.stats, Statistics.Order.ByLastAccessTime));

            } else {
                sb.append(BackendParser.toHtml(Data.sites, Data.stats, Statistics.Order.ByDefault));
            }
            sb.insert(0, "Last start " + Date.getAge(Data.stats.lastStart) + "\n\n");
            sb.insert(0, "Since " + Date.getAge(Data.stats.since) + "\n\n");

            sb.append("\n\n").append("Options:\n\tr - Reset\n\ts - order by Site name\n\tn - order by Number of requests\n\tt - order by access Time\n");

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
    public void destroy()
    {
        super.destroy();
        Data.stats.save();
    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(Statistics.class);
        oFactory.begin();

        new Data();
    }

}
/*        Posible support for more than 1 site request per request

        String[] news_requests = req.getParameterValues("site");

        for (String site_request : news_requests) {
            int l = site_request.indexOf("(");

            BE_Site site = sites.getSiteByName(site_request.substring(0, l));

            String[] site_sections = site_request.substring(l + 1, site_request.length() - 1).split(",");
            int[] site_sections_i = new int[site_sections.length];
            for (int i = 0; i < site_sections.length; i++) {
                site_sections_i[i] = Integer.parseInt(site_sections[i]);
            }

            BE_NewsList site_news = site.getReader().readNews(site_sections_i);

            StringBuilder sb = new StringBuilder("<channel>");
            for (BE_News news : site_news) {
                sb.append(news.toEntry());
            }
            sb.append("</channel>");

         resp.getWriter().println("Site name: " + site.name);
          resp.getWriter().println("Site sections: " + site_sections);
            resp.getWriter().println(sb.toString());

        }
*/
