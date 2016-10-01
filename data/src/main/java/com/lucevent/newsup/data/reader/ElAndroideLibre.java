package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class ElAndroideLibre extends com.lucevent.newsup.data.util.NewsReader {

    // tags:  [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public ElAndroideLibre()
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

    @Override
    protected String parseDescription(Element prop)
    {
        String dscr = org.jsoup.Jsoup.parse(prop.text()).getElementsByTag("p").text();
        int index = dscr.indexOf("[…]");
        if (index != -1)
            dscr = dscr.substring(0, index);
        return dscr;
    }

    @Override
    protected String parseContent(Element prop)
    {
        return prop.text().replace("src=\"/", "src=\"http:/");
    }

}
