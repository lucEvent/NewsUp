package com.lucevent.newsup.data.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class VilaWeb extends com.lucevent.newsup.data.util.NewsReader {

    public VilaWeb()
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
        String descr = Jsoup.parse(prop.text()).text();
        return descr.substring(0, 300 < descr.length() ? 300 : descr.length()) + "...";
    }

}
