package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class Xataka extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * Tags
     * [dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]
     * [dc:creator, description,                      guid, item, link, pubdate, title]
     */

    public Xataka()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("h4 ~ *,h4,[clear=\"all\"] ~ *").remove();
        return doc.body().html();
    }

}
