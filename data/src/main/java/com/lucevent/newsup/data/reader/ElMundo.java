package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class ElMundo extends com.lucevent.newsup.data.util.NewsReader {

    public ElMundo() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("a, img").remove();
        news.description = doc.text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("div[itemprop=\"articleBody\"],#tamano");
        e.select("aside, footer, meta, time").remove();

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}

