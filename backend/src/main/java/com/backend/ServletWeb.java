package com.backend;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Site;
import com.backend.kernel.list.BE_NewsList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletWeb extends HttpServlet {

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

            BE_Site site = Data.sites.getSiteByCode(Integer.parseInt(parts[0]));

            int[] site_sections = new int[parts.length - 1];
            for (int i = 0; i < site_sections.length; i++) {
                site_sections[i] = Integer.parseInt(parts[i + 1]);
            }

            BE_NewsList site_news = site.readNews(site_sections);

            StringBuilder sb = new StringBuilder();
            for (BE_News news : site_news) {
                sb.append(news.toHtml());
            }

//        PrintWriter out = response.getWriter();
            resp.getWriter().println(sb.toString());

            site.addNews(site_news);
            
        } else if (req.getParameter("content") != null) {

            BE_Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");
            long date = Long.parseLong(req.getParameter("date"));

            BE_News bait = new BE_News("", link, "", date, null);
            BE_News prey = site.historial.ceiling(bait);

            if (prey != null && prey.compareTo(bait) == 0) {
                if (prey.content == null) {
                    site.readNewsContent(prey);
                }

                if (prey.content == null) {
                    resp.getWriter().println("");
                } else {
                    resp.getWriter().println(prey.content);
                }
            }
        } else if (req.getParameter("debug") != null) {

            BE_Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");

            BE_News N = new BE_News("", link, "", 0, null);

            site.readNewsContent(N);

            resp.getWriter().println(N.content);

        } else if (req.getParameter("clear") != null) {

            for (BE_Site site : Data.sites) {
                site.historial.clear();
            }

        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

        new Data();

    }

}

