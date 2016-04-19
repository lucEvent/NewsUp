package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class RacoCatala extends com.lucevent.newsup.data.util.NewsReader {

    public RacoCatala() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.content = news.description;
        news.description = "";
        return news;
    }

}
