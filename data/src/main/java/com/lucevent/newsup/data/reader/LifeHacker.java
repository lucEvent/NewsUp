package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class LifeHacker extends com.lucevent.newsup.data.util.NewsReader {

    // tags: category, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title

    public LifeHacker()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{"feedburner:origlink".hashCode()},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }
    
    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("img").last().remove();
        doc.select("small,.core-inset").remove();
        return doc.html();
    }

}
