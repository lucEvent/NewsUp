package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

public class Aftonbladet extends com.lucevent.newsup.data.util.NewsReader {

    public Aftonbladet() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        news.enclosures = new Enclosures();
        org.jsoup.select.Elements imgs = doc.select("img");
        if (!imgs.isEmpty()) {
            news.enclosures.add(new Enclosure(imgs.get(0).attr("src"), "image/jpg", "0"));
        }

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".player__poster-image,.abLeadText,.abBodyText");
        if (!e.isEmpty()) {
            String img = "";
            if (!news.enclosures.isEmpty()) img = news.enclosures.get(0).html();

            news.content = img + e.html();
        }
    }

}
