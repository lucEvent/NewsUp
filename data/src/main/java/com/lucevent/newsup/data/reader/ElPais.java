package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class ElPais extends com.lucevent.newsup.data.util.NewsReader {

    public ElPais() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty() && !content.contains("Seguir leyendo")) {
            news.content = content;
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("#cuerpo_noticia");
        org.jsoup.select.Elements img = doc.select(".contenedor_fotonoticia_compartir");
        if (!e.isEmpty() || !img.isEmpty()) {
            String simg = "";
            if (!img.isEmpty()) simg = img.select("img").outerHtml();

            String mas = doc.select("div[id$=\"|despiece\"]").outerHtml();
            String links = doc.select("div[id$=\"|apoyos\"]").outerHtml();

            e.select("div[id$=\"|despiece\"],div[id$=\"|apoyos\"],div[id$=\"|html\"]").remove();
            e.select("script").remove();

            news.content = simg + e.outerHtml() + mas + links;
        } else {
            e = doc.select(".entry-content");
            e.select("script").remove();
            if (!e.text().isEmpty()) {
                news.content = e.html();
            }
        }
    }

}