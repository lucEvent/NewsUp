package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;

public class ElDiario extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [author, description, enclosure, guid, item, link, media:content, media:keywords, media:thumbnail, media:title, pubdate, title]
     * [author, description, guid, item, link, pubdate, title]
     * [description, enclosure, guid, item, link, media:content, media:keywords, media:thumbnail, media:title, pubdate, title]
     */

    public ElDiario()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{"media:keywords".hashCode()},
                new int[]{TAG_ENCLOSURE},
                "http://www.eldiario.es/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = jsoupParse(prop.text());
        doc.select("a,img[width='1'],br,script").remove();
        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");
        NewsStylist.repairLinks(doc.body());
        return doc.select("body").html();
    }

}
