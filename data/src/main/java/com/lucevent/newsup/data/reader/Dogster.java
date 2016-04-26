package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class Dogster extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // Tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public Dogster()
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
        return org.jsoup.Jsoup.parse(prop.text()).select("p").get(0).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        for (org.jsoup.nodes.Element e : doc.getElementsByAttribute("style"))
            e.attr("style", "");
        return doc.html();
    }

}