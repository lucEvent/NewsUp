package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class AndroidAuthority extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, media:content, pubdate, title]
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, pubdate, title]
     */

    public AndroidAuthority()
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
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select(".aa_button_wrapper,.aa_see_also_block,.cbc-latest-videos").remove();
        return doc.html();
    }

}
