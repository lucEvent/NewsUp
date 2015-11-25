package com.backend;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Site;
import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_SiteList;
import com.backend.servlet.AsyncProcessManager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processPetition(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processPetition(req, resp);
    }

    private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        if (req.getParameter("index") != null) {

            String site_request = req.getParameter("site");

            String[] parts = site_request.split(",");

            BE_Site site = sites.getSiteByCode(Integer.parseInt(parts[0]));

            int[] site_sections = new int[parts.length - 1];
            for (int i = 0; i < site_sections.length; i++) {
                site_sections[i] = Integer.parseInt(parts[i + 1]);
            }

            BE_NewsList site_news = site.getReader().readNews(site_sections);

            StringBuilder sb = new StringBuilder("<channel>");
            for (BE_News news : site_news) {
                sb.append(news.toEntry());
            }
            sb.append("</channel>");

//        PrintWriter out = response.getWriter();
            resp.getWriter().println(sb.toString());

            site.addNews(site_news);
            //      processManager.addReadContentTask(site);

        } else if (req.getParameter("content") != null) {

            BE_Site site = sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");
            long date = Long.parseLong(req.getParameter("date"));

            BE_News bait = new BE_News("", link, "", date, null);
            BE_News prey = site.news.ceiling(bait);

            if (prey != null && prey.compareTo(bait) == 0) {
                if (prey.content == null) {
                    site.getReader().readNewsContent(prey);
                }

                if (prey.content == null) {
                    resp.getWriter().println("");
                } else {
                    resp.getWriter().println(prey.content);
                }
            }
        } else if (req.getParameter("debug") != null) {
/*
            BE_Site site = sites.getSiteByCode(600);

            BE_News news = new ArrayList<BE_News>(site.news).get(Integer.parseInt(req.getParameter("i")));
            site.getReader().readNewsContent(news);

            resp.getWriter().println(news.content);
*/
        }
    }

    private AsyncProcessManager processManager;
    private BE_SiteList sites;

    @Override
    public void init() throws ServletException {
        super.init();


        if (sites == null) {
            sites = new BE_SiteList();
        }
        if (processManager == null) {
            processManager = new AsyncProcessManager();
        }
        //debug
   /*

        boolean debug = false;
        if (debug) {

            for (BE_Site s : sites) {
                System.out.println("### [" + s.name + "] ###");
                for (int isec = 0; isec < s.getReader().SECTIONS.size(); ++isec) {
                    BE_Section d = s.getReader().SECTIONS.get(isec);
           //         System.out.println("@@# [" + d.name.toUpperCase() + "] #@@");
                    if (d.link != null) s.getReader().readNews(new int[]{ isec});
//                    else System.out.println("Es que el l");
                }
            }
        }
        //end debug
    */
    }


}
/*        //Posible support for more than 1 site request per request
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


           System.out.println("Site name: " + site.name);
            System.out.println("Site sections: " + site_sections);
         resp.getWriter().println("Site name: " + site.name);
          resp.getWriter().println("Site sections: " + site_sections);
            resp.getWriter().println(sb.toString());

        }
*/
