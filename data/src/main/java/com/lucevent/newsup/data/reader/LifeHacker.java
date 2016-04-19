package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class LifeHacker extends com.lucevent.newsup.data.util.NewsReader {

    public LifeHacker() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(news.description);
        doc.select("img").last().remove();
        doc.select("small,.core-inset").remove();

        news.content = doc.html();
        news.description = "";
        return news;
    }

}
