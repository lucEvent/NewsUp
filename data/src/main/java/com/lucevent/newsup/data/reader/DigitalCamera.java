package com.lucevent.newsup.data.reader;

public class DigitalCamera extends com.lucevent.newsup.data.util.NewsReader {

    //tags: item, title, link, comments, pubDate, dc:creator, category, guid, description, content:encoded

    public DigitalCamera()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

}
