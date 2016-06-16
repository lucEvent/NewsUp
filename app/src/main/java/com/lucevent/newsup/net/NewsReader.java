package com.lucevent.newsup.net;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.Tags;

public final class NewsReader {

    private static final int HASH_TITLE = "title".hashCode();
    private static final int HASH_LINK = "link".hashCode();
    private static final int HASH_DATE = "date".hashCode();
    private static final int HASH_DESCRIPTION = "description".hashCode();
    private static final int HASH_CATEGORIES = "categories".hashCode();
    private static final int HASH_CONTENT = "content".hashCode();

    private static final String query_index = "http://newsup-2406.appspot.com/app?news&site=";
    private static final String query_content = "http://newsup-2406.appspot.com/app?content&site=";

    public NewsReader()
    {
    }

    public final NewsArray readNewsHeader(Site site, int[] sections)
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

            for (org.jsoup.nodes.Element prop : item.children()) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE)
                    title = prop.html();

                else if (taghash == HASH_LINK)
                    link = prop.html();

                else if (taghash == HASH_DATE)
                    date = Long.parseLong(prop.html());

                else if (taghash == HASH_DESCRIPTION)
                    description = prop.text();

                else if (taghash == HASH_CATEGORIES)
                    categories = prop.html();

                else if (taghash == HASH_CONTENT)
                    content = prop.html();

            }
            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, new Tags(categories));

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
            AppSettings.printerror("[NR] Can't read url: " + pagelink);
            e.printStackTrace();
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
                AppSettings.printerror("[NR] CONTENT NOT FOUND OF " + news.link);
        }
        return news;
    }

}