package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Time extends com.lucevent.newsup.data.util.NewsReader {

    public Time() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();

        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(content);
        doc.select("[width=\"1\"],object").remove();
        news.content = doc.select("body").html();
        return news;
    }

}
