package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class Clipset extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public Clipset()
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
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        String content = prop.text();
        int index = content.lastIndexOf("<p>");
        if (index != -1)
            content = content.substring(0, index);

        return content.replace("width=\"","x=\"").replace("height=\"","y=\"");
    }

}
