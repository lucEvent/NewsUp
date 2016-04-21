package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class ElAndroideLibre extends com.lucevent.newsup.data.util.NewsReader {

    public ElAndroideLibre() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).getElementsByTag("p").text().replace("[...]", "");

        org.jsoup.select.Elements doc = org.jsoup.Jsoup.parseBodyFragment(content).getElementsByTag("body");
        doc.select("[clear=\"all\"] ~ *,br,.feedflare,[width=\"1\"]").remove();
        news.content = doc.html();
        return news;
    }

}
