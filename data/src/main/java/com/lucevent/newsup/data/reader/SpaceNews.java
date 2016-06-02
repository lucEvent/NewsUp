package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class SpaceNews extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, content:encoded, dc:creator, description,            guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]
     */

    public SpaceNews()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text().replace("SpaceNews.com", "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        return prop.text().replace("style=", "none=");
    }

}