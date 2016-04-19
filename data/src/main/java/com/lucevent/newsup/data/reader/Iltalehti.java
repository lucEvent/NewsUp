package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import java.io.IOException;
import java.net.URL;

public class Iltalehti extends com.lucevent.newsup.data.util.NewsReader {

    public Iltalehti() {
        super();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink) {
        try {
            return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("isense");

        if (!e.isEmpty()) {
            e.select(".author,.important-articles-t,.kp-share-area").remove();

            for (org.jsoup.nodes.Element style : e.select("[style]")) {
                style.attr("style", "");
            }

            news.content = e.html();
        }
    }

}
