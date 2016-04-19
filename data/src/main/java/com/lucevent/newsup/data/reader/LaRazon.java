package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class LaRazon extends com.lucevent.newsup.data.util.NewsReader {

    public LaRazon() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.content = news.description;
        news.description = "";
        return news;
    }

}