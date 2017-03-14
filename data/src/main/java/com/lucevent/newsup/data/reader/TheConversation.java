package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TheConversation extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [author, description, guid, item, link, pubdate, title]

    public TheConversation()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script,[width='1'],figcaption").remove();
        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");
        return doc.body().html();
    }

}
