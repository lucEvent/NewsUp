package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Vice extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:category, media:thumbnail, pubdate, title]

    public Vice()
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
    protected long parseDate(Element prop)
    {
        return Date.toDate(prop.text().substring(0, 24));
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        for (Element e : doc.select("[style]"))
            e.removeAttr("style");
        for (Element e : doc.select("iframe"))
            e.attr("frameborder", "0");
        doc.select("h1,h2").tagName("h3");

        return doc.html().replace("src=\"/", "src=\"http:/");
    }

}
