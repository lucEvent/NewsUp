package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class ElDiario extends com.lucevent.newsup.data.util.NewsReader_v2 {

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
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("a,img[width=\"1\"],br").remove();
        return doc.select("body").html();
    }

}
