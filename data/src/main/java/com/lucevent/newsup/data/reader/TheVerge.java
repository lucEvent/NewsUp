package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TheVerge extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [author, content, entry, id, link, name, published, title, updated]

    public TheVerge()
    {
        super(TAG_ITEM_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_ID},
                new int[]{},
                new int[]{TAG_CONTENT},
                new int[]{TAG_UPDATED},
                new int[]{},
                new int[]{},
                "https://www.theverge.com/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select("figure cite,.caption").tagName("figcaption");
        doc.select("[style]:not(.instagram-media [style]").removeAttr("style");

        for (Element e : doc.select(".c-float-right,.c-float-left"))
            e.tagName("blockquote").removeAttr("class");

        return doc.body().html();
    }

}
