package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class TED extends com.lucevent.newsup.data.util.NewsReader {

    public TED() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        if (!content.isEmpty()) {
            org.jsoup.nodes.Element edoc = org.jsoup.Jsoup.parse(content).body();

            for (org.jsoup.nodes.Element e : edoc.select("[style]")) {
                e.removeAttr("style");
            }

            edoc.select("[rel=\"nofollow\"] ~ *,[rel=\"nofollow\"]").remove();
            news.content = edoc.html();

        } else if (!news.enclosures.isEmpty()) {
            news.content = news.enclosures.get(0).html() + "<p>" + news.description + "</p>";
        }
        return news;
    }

}

