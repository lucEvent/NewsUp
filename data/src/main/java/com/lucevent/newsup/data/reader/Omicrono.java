package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Omicrono extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]

    public Omicrono()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{"feedburner:origlink".hashCode()},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        Document doc = jsoupParse(prop);
        Elements e = doc.select("p");
        if (!e.isEmpty())
            return e.first().text();
        return doc.text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select(".blockquoteLink,.feedflare,[width='1'],.blockquoteRelated").remove();

        doc.select("h1,h2").tagName("h3");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html();
    }

}
