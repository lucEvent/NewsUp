package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class ElDiario extends com.lucevent.newsup.data.util.NewsReader {

    public ElDiario() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("a,img[width=\"1\"],br").remove();
        news.description = "";
        news.content = doc.select("body").html();
        return news;
    }

}
