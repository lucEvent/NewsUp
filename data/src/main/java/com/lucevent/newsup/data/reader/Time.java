package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class Time extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: 	[category, content:encoded, dc:creator, description, guid, item, link, media:content, media:thumbnail, media:title, pubdate, title]

    public Time()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("[width=\"1\"],object").remove();
        return doc.select("body").html();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), prop.attr("medium"), "0");
    }

}
