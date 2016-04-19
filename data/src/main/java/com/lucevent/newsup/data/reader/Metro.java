package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import java.io.IOException;

public class Metro extends com.lucevent.newsup.data.util.NewsReader {

    public Metro() {
        super();
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (IOException e) {
            debug("[" + e.getClass().getSimpleName() + "] Reintentando");
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).timeout(10000).get();
        } catch (IOException e) {
            debug("[" + e.getClass().getSimpleName() + "] No se ha podido leer: " + pagelink);
        }
        return null;
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = news.description.replace("...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        doc.select("script").remove();

        org.jsoup.select.Elements imgs = doc.select(".image-component > img");
        if (!imgs.isEmpty()) {
            org.jsoup.nodes.Element img = imgs.get(0);
            String src = img.attr("src");
            if (src != null && !src.isEmpty()) {
                img.attr("src", "http://www.metro.se/" + src);
            }
            news.content = img.outerHtml();
        }
        org.jsoup.select.Elements e = doc.select(".article-body");
        for (org.jsoup.nodes.Element img : e.select("img")) {
            img.attr("src", "http://www.metro.se" + img.attr("src"));
        }

        news.content += e.html();
    }

}
