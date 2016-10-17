package com.lucevent.newsup.net;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.Tags;

public final class NewsReader {

    private static final int HASH_TITLE = 110371416;
    private static final int HASH_LINK = 3321850;
    private static final int HASH_DATE = 3076014;
    private static final int HASH_DESCRIPTION = -1724546052;
    private static final int HASH_CATEGORIES = 1296516636;
    private static final int HASH_CONTENT = 951530617;
    private static final int HASH_ENCLOSURE = 1432853874;

    private static final String query_index = "http://newsup-2406.appspot.com/app?news&" + (AppSettings.DEBUG ? "nc&" : "") + "site=";
    private static final String query_content = "http://newsup-2406.appspot.com/app?content&site=";

    public NewsReader()
    {
    }

    public final NewsArray readNewsHeaders(Site site, int[] sections)
    {
        StringBuilder sectArray = new StringBuilder(sections.length * 3);
        for (int section : sections) sectArray.append(',').append(section);

        String query = query_index + site.code + sectArray;
        AppSettings.printlog("Query: " + query);

        NewsArray res = readRssPage(query);
        if (!res.isEmpty())
            site.prior_news = res.get(0);
        return res;
    }

    private NewsArray readRssPage(String rsslink)
    {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray res = new NewsArray();

        for (org.jsoup.nodes.Element item : doc.select("item")) {
            String title = "", link = "", description = "", content = "", categories = "";
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
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, new Tags(categories));
                news.enclosures = enclosures;
                if (!content.isEmpty())
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

    public final News readNewsContent(Site site, News news)
    {
        String query = query_content + site.code + "&date=" + news.date + "&link=" + news.link;

        org.jsoup.nodes.Document doc = getDocument(query);
        if (doc != null) {
            String content = doc.html();

            if (!content.isEmpty())
                news.content = content;
            else
                AppSettings.printerror("[NR] CONTENT NOT FOUND OF " + news.link, null);
        }
        return news;
    }

}