package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Marca extends com.lucevent.newsup.data.util.NewsReader {

    public Marca() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        news.description = news.description.replace("Leer", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        doc.select("script").remove();

        org.jsoup.select.Elements ee = doc.select(".news-item");

        if (ee.isEmpty()) {

            ee = doc.select("#contenido-noticia");

            if (!ee.isEmpty()) {

                org.jsoup.select.Elements img = doc.select(".cubrereproductor noscript");
                org.jsoup.select.Elements content = doc.select(".cuerpo_articulo > p");

                if (content.isEmpty()) {
                    news.content = ee.select(".bloque-foto img").outerHtml();
                } else {
                    news.content = img.html() + content.outerHtml();
                }
            }
        } else {

            org.jsoup.select.Elements img = doc.select("figure");
            org.jsoup.select.Elements content = doc.select("[itemprop=\"articleBody\"] > p");

            if (!content.isEmpty()) {

                String simage = img.html().replace("\"//", "\"http://").replace("noscript", "p");
                news.content = simage + content.outerHtml();

            }
        }
        news.content = news.content.replace("style=\"", "none=\"");
    }

}
