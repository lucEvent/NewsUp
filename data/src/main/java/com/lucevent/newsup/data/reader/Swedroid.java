package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class Swedroid extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title, enclosure]
     */

    public Swedroid()
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
        String description = org.jsoup.Jsoup.parse(prop.text()).text();

        int s = description.indexOf("[…]");
        if (s != -1) {
            description = description.substring(s);
        }
        return description;
    }

    @Override
    protected String parseContent(Element prop)
    {
        String content = prop.text();
        if (content.length() > 0)
            content = content.replace("style=", "none=");
        return content;
    }

}