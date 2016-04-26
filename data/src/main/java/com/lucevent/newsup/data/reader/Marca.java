package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Marca extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags:  category, dc:creator, description, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, title

    public Marca()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text().replace("Leer", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
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
