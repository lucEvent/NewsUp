package com.backend;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Site;
import com.backend.kernel.list.BE_NewsList;

import java.io.IOException;

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

            BE_Site site = Data.sites.getSiteByCode(Integer.parseInt(parts[0]));

            StringBuilder sb = new StringBuilder("<channel>");
            for (int i = 0; i < parts.length - 1; i++) {
                int section = Integer.parseInt(parts[i + 1]);

                long lastupdate = site.getSectionUpdateTime(section);
                if (lastupdate == -1 || lastupdate < System.currentTimeMillis() - 3600000) {
                    BE_NewsList news = site.readNews(new int[]{section});

                    boolean allcontent = true;
                    for (BE_News N : news) {
                        if (N.content == null || N.content.isEmpty()) {
                            allcontent = false;
                        }
                    }

                    if (allcontent) {
                        site.addContent(section, news);
                        sb.append(site.getSectionContent(section));
                    } else {
                        site.addNews(news);
                        for (BE_News N : news) {
                            sb.append(N.toEntry());
                        }
                    }
                } else {
                    sb.append(site.getSectionContent(section));
                }

            }
            sb.append("</channel>");

//        PrintWriter out = response.getWriter();
            resp.getWriter().println(sb.toString());

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


           System.out.println("Site name: " + site.name);
            System.out.println("Site sections: " + site_sections);
         resp.getWriter().println("Site name: " + site.name);
          resp.getWriter().println("Site sections: " + site_sections);
            resp.getWriter().println(sb.toString());

        }
*/
