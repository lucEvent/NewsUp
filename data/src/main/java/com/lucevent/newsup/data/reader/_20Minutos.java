package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class _20Minutos extends com.lucevent.newsup.data.util.NewsReader {

    public _20Minutos() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("body > br, body > img, body > a").remove();

        news.description = "";
        news.content = doc.select("body").html();
        return news;
    }

}