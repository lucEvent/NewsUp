package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
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
            Site sitev2 = Data.sitesV2.getSiteByCode(Integer.parseInt(parts[0]));
            Data.stats.count(Data.sites.indexOf(site), req.getRemoteAddr());

            int[] sections = new int[parts.length - 1];
            for (int i = 0; i < sections.length; i++)
                sections[i] = Integer.parseInt(parts[i + 1]);

            NewsArray news = site.readNewsHeaders(sections);
            long t = System.nanoTime();
            sitev2.news.addAll(news);
            long t2 = System.nanoTime();
            site.news.addAll(news);
            long t3 = System.nanoTime();
            System.out.println("Add times ["+(t2-t)+"] vs ["+(t3-t2)+"]");

            resp.getWriter().println(BackendParser.toHtml(news).toString());

            String f = "<li><a><div class='site' id='%d'><img src='img/%d.png'>%s</div></a></li>";
            for(Site s : Data.sitesV2){
                System.out.println(String.format(f,s.code,s.code,s.name));
            }

        } else if (req.getParameter("content") != null) {

            Site site = Data.sitesV2.getSiteByCode(Integer.parseInt(req.getParameter("site")));

            News bait = new News(-1, "", "", "", 0, null);
            bait.server_id = Long.parseLong(req.getParameter("nid"));

            News prey = site.news.ceiling(bait);
            if (prey != null && prey.server_id == bait.server_id) {

                if (prey.content == null || prey.content.isEmpty())
                    site.readNewsContent(prey);

                resp.getWriter().println(prey.content == null ? "" : prey.content);
            }

        } else if (req.getParameter("sections") != null) {

            String s_site = req.getParameter("site");

            Site site = Data.sitesV2.getSiteByCode(Integer.parseInt(s_site));

            resp.getWriter().println(BackendParser.toHtml(site.sections).toString());

        } else if (req.getParameter("stats") != null) {

            if (req.getParameter("reset") != null)
                Data.stats.reset(Data.sitesV2);

            StringBuilder sb = new StringBuilder();
            sb.append("Since ").append(Date.getAge(Data.stats.since)).append("\n\n");
            sb.append("Last start ").append(Date.getAge(Data.stats.lastStart)).append("\n\n");

            String options = req.getParameter("options");
            sb.append(BackendParser.toHtml(Data.stats, options != null ? options : ""));

            resp.getWriter().println(sb);

        }

    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        new Data();
    }

}

