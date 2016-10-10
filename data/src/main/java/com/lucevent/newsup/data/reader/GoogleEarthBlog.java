package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GoogleEarthBlog extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public GoogleEarthBlog()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{"comments".hashCode()},
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
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        for (Element e : doc.select("[style]"))
            e.removeAttr("style");
        for (Element e : doc.select("[width],[height]"))
            e.removeAttr("width").removeAttr("height");

        return doc.html().replace("/gelogoicon.gif\"", "/gelogoicon.gif\" style=\"width:4%\"");
    }

}
