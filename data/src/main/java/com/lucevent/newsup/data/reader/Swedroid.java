package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Swedroid extends com.lucevent.newsup.data.util.NewsReader {

    public Swedroid() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        int s = news.description.indexOf("[…]");
        if (s != -1) {
            news.description = news.description.substring(s);
        }
        if (content.length() > 0) {
            news.content = content.replace("style=", "none=");
        }
        return news;
    }

}