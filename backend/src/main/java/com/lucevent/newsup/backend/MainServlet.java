package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.data.util.Tags;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    private static final String applink = "https://play.google.com/store/apps/details?id=com.lucevent.newsup";

    private static News generateNotificationNews(Site site)
    {
        StringBuilder title = new StringBuilder();
        StringBuilder description = new StringBuilder();
        StringBuilder content = new StringBuilder();

        switch (site.getLanguage()) {
            case SiteLanguage.SPANISH:
                title.append("Actualiza ya a la \u00FAltima versi\u00F3n de NewsUp");
                description.append("Click para m\u00E1s informaci\u00F3n");
                content.append("<p>La versi\u00F3n de NewsUp que utilizas est\u00E1 obsoleta y dejar\u00E1 de funcionar a partir del 1 de enero de 2017.</p>");
                content.append("<p>Te recomendamos que actualices a la \u00FAltima versi\u00F3n disponible en Google Play para disfrutar de las \u00FAltimas novedades, mejoras y correcci\u00F3n de errores.</p>");
                content.append("<p><a href='").append(applink).append("'>Actualizar</a></p>");
                break;

            case SiteLanguage.CATALAN:
                title.append("Actualitza ja a la \u00FAltima versi\u00F3 de NewsUp");
                description.append("Click per a m\u00E9s informaci\u00F3");
                content.append("<p>La versi\u00F3 de NewsUp que utilitzes est\u00E0 obsoleta i deixar\u00E0 de funcionar a partir de l'1 de gener de 2017.</p>");
                content.append("<p>Et recomanem que actualitzis a la \u00FAltima versi\u00F3 disponible a Google Play per disfrutar de les \u00FAltimes novetats, millores i correcci\u00F3 d'errors.</p>");
                content.append("<p><a href='").append(applink).append("'>Actualitzar</a></p>");
                break;

            case SiteLanguage.FINNISH:
                title.append("Update to the latest version of NewsUp");
                description.append("Click for more info");
                content.append("<p>The app version you are using is no longer supported and it will stop working on 1st January 2017.</p>");
                content.append("<p>We recommend you update to the latest version available in Google Play in order to enjoy new features, improvements and error fixes.</p>");
                content.append("<p>As you are viewing a Finnish publication, you must know that Finnish publications are not available by default in the latest versions. However you can get free access to them by entering the code HYV\u00C4\u00C4SUOMI in \"Settings->Enter PRO code\"</p>");
                content.append("<p><a href='").append(applink).append("'>Update</a></p>");
                break;

            default:
                title.append("Update to the latest version of NewsUp");
                description.append("Click for more info");
                content.append("<p>The app version you are using is no longer supported and it will stop working on 1st January 2017.</p>");
                content.append("<p>We recommend you update to the latest version available in Google Play in order to enjoy new features, improvements and error fixes.</p>");
                content.append("<p><a href='").append(applink).append("'>Update</a></p>");
        }

        News news = new News(title.toString(), applink, description.toString(), System.currentTimeMillis(), new Tags());
        news.content = content.toString();
        news.enclosures = new Enclosures();
        return news;
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

            String site_request = req.getParameter("site");

            String[] parts = site_request.split(",");

            int site_code = Integer.parseInt(parts[0]);
            Site site = Data.sites.getSiteByCode(site_code);
            Data.stats.count(site, req.getRemoteAddr(), "0.16.3");

            int[] sections = new int[parts.length - 1];
            for (int i = 0; i < sections.length; i++) {
                sections[i] = Integer.parseInt(parts[i + 1]);
            }

            NewsArray news = site.readNewsHeaders(sections);
            news.add(generateNotificationNews(site));
            site.news.addAll(news);

            resp.getWriter().println(BackendParser.toEntry(news).toString());

        } else if (req.getParameter("content") != null) {

            Site site = Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site")));
            String link = req.getParameter("link");
            long date = Long.parseLong(req.getParameter("date"));

            News bait = new News("", link, "", date, null);
            News prey = site.news.ceiling(bait);

            if (prey != null && prey.compareTo(bait) == 0) {

                if (prey.content == null || prey.content.isEmpty())
                    site.readNewsContent(prey);

                resp.getWriter().println(prey.content == null ? "" : prey.content);
            }
        }

    }

    @Override
    public void init() throws ServletException
    {
        super.init();
    }

}
