package com.lucevent.newsup.data.reader;

public class Verne extends com.lucevent.newsup.data.util.NewsReader {

    //tags: item, title, link, guid, pubDate, dc:creator, description, category, content:encoded, enclosure, comments

    public Verne()
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

}