package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CosmoNoticias extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.wp-caption-text{display:block;font-size:14px;color:grey;padding:2px 15px;}</style>";

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public CosmoNoticias()
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
        return jsoupParse(prop).text().replace(" … Sigue leyendo →", "...");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);

        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");
        doc.select("[onclick]").removeAttr("onclick");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html();
    }

}
