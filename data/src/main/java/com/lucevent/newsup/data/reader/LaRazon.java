package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;

public class LaRazon extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [description, enclosure, guid, item, link, media:content, media:thumbnail, media:title, pubdate, source, title]
     * [description, enclosure, guid, item, link, media:content, media:title, pubdate, source, title]
     * [description, guid, item, link, media:content, media:title, pubdate, source, title]
     */

    public LaRazon()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_MEDIA_CONTENT},
                "http://www.larazon.es/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text().replace("\u201D", "'"));
        doc.select("script").remove();
        doc.select("[style]").removeAttr("style");
        NewsStylist.repairLinks(doc.body());
        return doc.body().html();
    }

}