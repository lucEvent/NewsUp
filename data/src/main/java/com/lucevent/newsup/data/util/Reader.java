package com.lucevent.newsup.data.util;

public abstract class Reader {

    protected static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0.1; GT-I9300 Build/MOB30Z) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36";

    public String style = "";

    public abstract NewsArray readRssHeader(String rss_link, int site_code, int section_code);

    public final News readContent(News news)
    {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc != null) {
            readNewsContent(doc, news);
        }
        return news;
    }

    protected abstract void readNewsContent(org.jsoup.nodes.Document doc, News news);

    protected final org.jsoup.nodes.Document jsoupParse(org.jsoup.nodes.Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text());
    }

    protected final org.jsoup.nodes.Document jsoupParse(String data)
    {
        return org.jsoup.Jsoup.parse(data);
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + " | " + e.getClass().getSimpleName() + "] Can't read page. Trying again");
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + " | " + e.getClass().getSimpleName() + "] Couldn't read page: " + pagelink);
        }
        return null;
    }

}
