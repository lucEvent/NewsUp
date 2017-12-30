package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class EuropaPress extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:
     * [category, description, enclosure, guid, item, link, pubdate, title]
     * [category, description, guid, item, link, pubdate, title]
     */

    public EuropaPress()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements img = doc.select(".schema_foto img:not([itemprop='articleBody'] .schema_foto img)");
        org.jsoup.select.Elements e = doc.select("[itemprop='articleBody']");

        if (e.isEmpty())
            return;

        e.select(".FechaPublicacionNoticia,.captionv2,script,.related-content-no-image,.related-content,button").remove();
        e.select("[onclick]").removeAttr("onclick");
        e.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

        news.content = (finalFormat(img, true) + finalFormat(e, true))
                .replace("Cargando el vídeo....", "");
    }

    protected org.jsoup.nodes.Document getDocument(String link)
    {
        try {
            return org.jsoup.Jsoup.connect(link).get();
        } catch (Exception ignored) {
        }
        return super.getDocument(link);
    }

}
