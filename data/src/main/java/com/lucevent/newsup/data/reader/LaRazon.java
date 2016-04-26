package com.lucevent.newsup.data.reader;

public class LaRazon extends com.lucevent.newsup.data.util.NewsReader_v2 {

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
                new int[]{TAG_MEDIA_CONTENT});
    }
    
}