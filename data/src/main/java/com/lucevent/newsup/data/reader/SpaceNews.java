package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class SpaceNews extends com.lucevent.newsup.data.util.NewsReader {

    public SpaceNews() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text().replace("SpaceNews.com", "");
        if (content.length() > 0) {
            news.content = content.replace("style=", "none=");
        }
        return news;
    }

}