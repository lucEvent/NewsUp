package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Dogster extends com.lucevent.newsup.data.util.NewsReader {

    public Dogster() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document description = org.jsoup.Jsoup.parse(news.description);
        news.description = description.select("p").get(0).text();

        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
        for (org.jsoup.nodes.Element e : doc.getElementsByAttribute("style")) {
            e.attr("style", "");
        }
        news.content = doc.html();
        return news;
    }

}