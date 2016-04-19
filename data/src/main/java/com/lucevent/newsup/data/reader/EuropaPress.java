package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import java.io.IOException;

public class EuropaPress extends com.lucevent.newsup.data.util.NewsReader {

    public EuropaPress() {
        super();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {

        org.jsoup.select.Elements img = doc.select(".schema_foto img:not([itemprop=\"articleBody\"] .schema_foto img)");
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"]");

        if (e.isEmpty()) {

            return;

        } else {

            e.select(".FechaPublicacionNoticia,.captionv2,script").remove();

        }
        news.content = img.outerHtml() + e.outerHtml();
        news.content = news.content.replace("Cargando el vídeo....", "");
    }

    protected org.jsoup.nodes.Document getDocument(String link) {
        try {
            return org.jsoup.Jsoup.connect(link).get();
        } catch (IOException e) {
        }
        return super.getDocument(link);
    }

}
