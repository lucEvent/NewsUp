package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Xataka extends com.lucevent.newsup.data.util.NewsReader {

    public Xataka() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(news.description);

        doc.select("h4 ~ *,h4,[clear=\"all\"] ~ *").remove();
        news.content = doc.body().html();
        news.description = "";
        return news;
    }

}
