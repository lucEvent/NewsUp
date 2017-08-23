package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class _20Minutos extends com.lucevent.newsup.data.util.NewsReader {

    //tags: dc:creator, dc:date, description, link, title

    public _20Minutos()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_DC_DATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "http://www.20minutos.es/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("body > br, body > img, body > a").remove();
        doc.select("h1,h2").tagName("h3");

        return doc.select("body").html();
    }

}