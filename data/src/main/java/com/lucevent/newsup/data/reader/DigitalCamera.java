package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DigitalCamera extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public DigitalCamera()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.digitalcamera.es/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("script").remove();
        doc.select("[style]").removeAttr("style");
        doc.select("[id]").removeAttr("id");
        doc.select("h1,h2").tagName("h3");
        doc.select(".wp-caption-text").tagName("figcaption");
        return NewsStylist.cleanComments(doc.body().html().replace("<p>&nbsp;</p>", ""));
    }

}
