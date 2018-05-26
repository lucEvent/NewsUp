package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.Data;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SiteSearchEngine {

    public static String search(String request) throws Exception
    {
        Elements dd = getPage("http://www.google.com/search?start=0&num=4&q=" + request.replace(" ", "+")).select("cite");

        if (!dd.isEmpty()) {
            request = dd.first().text();
        } else {
            request = request.toLowerCase().replace(" ", "");
            if (!request.contains("."))
                request = request + ".com";
        }
        if (request.endsWith("/"))
            request = request.substring(0, request.length() - 1);

        //  System.out.println("RS:" + request);

        RequestedSite site = Data.getRequestedSite(request);
        if (site == null) {
            // First try HTTP
            String site_url = request;
            if (!request.startsWith("http"))
                site_url = "http://" + site_url;

            Document d = getPage(site_url);

            // extract info
            String rss_url = extractRssUrl(d);
            if (rss_url == null || rss_url.isEmpty())
                throw new Exception("rss_url not found");

            String icon = extractIcon(d);
            int color = extractColor(d);
            String name = extractName(d, request);
            int info = SiteLanguage.VARIOUS | SiteCountry.VARIOUS | SiteCategory.VARIOUS;// TODO: 18/05/2018

            site = Data.requestedSites.createSite(request, name, site_url, rss_url, icon, info, color);
        }

        return "{\"code\":" + site.code +
                ",\"name\":\"" + site.name +
                "\", \"url\":\"" + site.url +
                "\", \"rss\":\"" + site.rss_url +
                "\", \"icon\":\"" + site.icon_url +
                "\", \"info\":\"" + site.info +
                "\", \"color\":" + site.color + "}";
    }

    private static Document getPage(String url) throws IOException
    {
        //    System.out.println("URL->" + url);
        return org.jsoup.Jsoup.connect(url)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                .referrer("http://www.google.com/")
                .validateTLSCertificates(false)
                .get();
    }

    private static String extractIcon(Document d)
    {
        Elements icons = d.select("link[rel=icon],link[rel='shortcut icon']");
        return icons.isEmpty() ? "" : icons.last().attr("href");
    }

    private static String extractRssUrl(Document d)
    {
        Elements links = d.select("link[type='application/rss+xml']");
        if (links.isEmpty()) {
            return null;
        }
        return links.first().attr("href");
    }

    private static int extractColor(Document d)
    {
        Elements colors = d.select("meta[name='theme-color']");
        String color_s = colors.isEmpty() ? "" : colors.first().attr("content");
        return color_s.isEmpty() ? /*0x8BC34A*/ -1 : Integer.parseInt(color_s.replace("#", ""), 16);
    }

    private static String extractName(Document d, String request)
    {
        Elements etitle = d.select("title");
        if (etitle.isEmpty()) {
            int last_dot_index = request.lastIndexOf(".");
            String site_name = request.substring(0, last_dot_index == -1 ? request.length() : last_dot_index);
            site_name = site_name.replace("http://", "").replace("https://", "").replace("www.", "");
            return Character.toUpperCase(site_name.charAt(0)) + site_name.substring(1);
        }

        String title = etitle.first().text();
        int c = title.indexOf("-");
        if (c != -1)
            title = title.substring(0, c).trim();
        title = title.toLowerCase();
        char[] letters = title.toCharArray();
        boolean capitalize = true;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == ' ') {
                capitalize = true;
                continue;
            }
            if (capitalize) {
                letters[i] = Character.toUpperCase(letters[i]);
                capitalize = false;
            }
        }
        return new String(letters);
    }

}
