package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class SkyAndTelescope extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * [category, content:encoded, dc:creator, description,            guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]
     */

    public SkyAndTelescope()
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
        org.jsoup.select.Elements description = org.jsoup.Jsoup.parse(prop.text()).select("p");
        if (!description.isEmpty()) {
            return description.get(0).text();
        }
        return "";
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        for (org.jsoup.nodes.Element e : doc.getElementsByAttribute("style"))
            e.attr("style", "");
        return doc.select("body").html();
    }

}
