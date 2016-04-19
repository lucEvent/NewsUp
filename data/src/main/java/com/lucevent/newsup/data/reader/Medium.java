package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Medium extends com.lucevent.newsup.data.util.NewsReader {

    public Medium() {
        super();
        HASH_LINK = "guid".hashCode();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).select(".medium-feed-snippet").html();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("main section .section-content");

        for (org.jsoup.nodes.Element title : e.select("h3")) {
            title.remove();
            break;
        }

        for (org.jsoup.nodes.Element fig : e.select("figure")) {
            org.jsoup.select.Elements img = fig.select("noscript");
            fig.html(img.html());
        }

        news.content = e.html();
    }

}
