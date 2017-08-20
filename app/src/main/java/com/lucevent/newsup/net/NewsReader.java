package com.lucevent.newsup.net;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public final class NewsReader {

    private static final int HASH_TITLE = 110371416;
    private static final int HASH_LINK = 3321850;
    private static final int HASH_DATE = 3076014;
    private static final int HASH_DESCRIPTION = -1724546052;
    private static final int HASH_CATEGORIES = 1296516636;
    private static final int HASH_CONTENT = 951530617;
    private static final int HASH_ENCLOSURE = 1432853874;
    private static final int HASH_SECTION = 1970241253;

    public static final int NUM_SERVERS = 8;
    private static final String[] SERVER_IDS = {"newsup-1", "newsup-2", "newsup-3", "newsup-4", "newsup-5", "newsup-1", "newsup-2", "newsup-3"};

    private static final String query_index = "http://newsup-2406.appspot.com/appv2?news&site=%s%s&v=%s" + (AppSettings.DEBUG ? "&nc" : "");
    private static final String query_content = "http://%s.appspot.com/appv2?content&site=%d&l=%s";
    private final String version;

    public NewsReader(String version)
    {
        this.version = version;
    }

    public final NewsArray readNewsHeaders(int site_code, int[] sections_codes)
    {
        StringBuilder sectArray = new StringBuilder(sections_codes.length * 3);
        for (int section_code : sections_codes)
            if (section_code != -1)
                sectArray.append(',').append(section_code);

        String rsslink = String.format(query_index, site_code, sectArray.toString(), version);
        AppSettings.printlog("Query: " + rsslink);

        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray res = new NewsArray();

        for (org.jsoup.nodes.Element item : doc.select("item")) {
            String title = "", link = "", description = "", content = "", categories = "";
            int section = 0;
            long date = 0;
            Enclosures enclosures = new Enclosures();

            for (org.jsoup.nodes.Element prop : item.children()) {

                switch (prop.tagName().hashCode()) {
                    case HASH_TITLE:
                        title = prop.html();
                        break;
                    case HASH_LINK:
                        link = prop.html();
                        break;
                    case HASH_DATE:
                        date = Long.parseLong(prop.html());
                        break;
                    case HASH_DESCRIPTION:
                        description = prop.text();
                        break;
                    case HASH_CATEGORIES:
                        categories = prop.html();
                        break;
                    case HASH_CONTENT:
                        content = prop.html();
                        break;
                    case HASH_ENCLOSURE:
                        enclosures.add(new Enclosure(prop.text(), "image", ""));
                        break;
                    case HASH_SECTION:
                        section = Integer.parseInt(prop.text());
                        break;
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, new Tags(categories), site_code, section, 0);
                news.enclosures = enclosures;
                news.content = content;

                res.add(news);
            }
        }
        return res;
    }

    private org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink).parser(org.jsoup.parser.Parser.xmlParser()).timeout(10000).get();
        } catch (Exception e) {
            AppSettings.printerror("[NR] Can't read url: " + pagelink, e);
        }
        return null;
    }

    private String getQueryRawContent(String query)
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(query).openStream()));

            int length;
            char[] buffer = new char[2048];

            StringBuilder sb = new StringBuilder();
            while ((length = in.read(buffer, 0, buffer.length)) > 0)
                sb.append(buffer, 0, length);

            in.close();

            return sb.toString();
        } catch (Exception e) {
            AppSettings.printerror("[NR] Can't read url: " + query, e);
        }
        return "";
    }

    public final News readNewsContent(int server, News news)
    {
        String query = String.format(query_content, SERVER_IDS[server], news.site_code, news.link);
        String content = getQueryRawContent(query);

        if (!content.isEmpty())
            news.content = content;
        else
            AppSettings.printerror("[NR] CONTENT NOT FOUND OF " + news.link, null);

        return news;
    }

}