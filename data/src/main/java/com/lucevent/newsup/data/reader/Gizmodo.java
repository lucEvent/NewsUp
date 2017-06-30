package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Gizmodo extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public Gizmodo()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);

        doc.select("h1,h2").tagName("h3");
        doc.select(".pullquote").tagName("blockquote");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return NewsStylist.cleanComments(doc.body().html());
    }

}
