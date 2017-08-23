package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaPatilla extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public LaPatilla()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://www.lapatilla.com/site/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return super.parseDescription(prop).trim().replace("[...]", "...");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("script,figcaption,br,.image_credits,.image_author").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html().replace("<p>&nbsp;</p>", "");
    }

}
