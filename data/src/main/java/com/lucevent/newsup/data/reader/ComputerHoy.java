package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;


public class ComputerHoy extends com.lucevent.newsup.data.util.NewsReader {

    public ComputerHoy() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements img = doc.select("[itemprop=\"image\"]");
        org.jsoup.select.Elements e = doc.select("#ab_stickyid");

        if (e.isEmpty()) {
            e = doc.select("article > p");

            if (e.isEmpty()) {
                return;
            }
        } else {
            e.select(".adcuadrado,blockquote").remove();
        }

        for (org.jsoup.nodes.Element iframe : e.select("iframe")) {
            String src = iframe.attr("src");
            if (!src.startsWith("http:")) {
                iframe.attr("src", "http:" + src);
            }
        }
        for (org.jsoup.nodes.Element style : e.select("[style]")) {
            style.removeAttr("style");
        }

        news.content = img.outerHtml() + e.html();

    }

}