package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Dogster extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.wp-caption-text{font-size:12px;padding:2px 10px;display:block;}</style>";

    // Tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public Dogster()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});

        this.style = SITE_STYLE;
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("p").first().text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");
        doc.select("[class^='m_'").removeAttr("class");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html();
    }

}