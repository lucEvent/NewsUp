package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TheGeekHammer extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public TheGeekHammer()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://thegeekhammer.com/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script,style,.yuzo_related_post").remove();

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return NewsStylist.cleanComments(doc.body().html());
    }

}
