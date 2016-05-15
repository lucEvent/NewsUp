package com.lucevent.newsup.data.reader;

public class Hipertextual extends com.lucevent.newsup.data.util.NewsReader_v2 {

    //tags: item, title, link, description, dc:creator, pubDate, guid

    public Hipertextual()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});

    }

}