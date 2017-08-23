package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SkyAndTelescope extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category, content:encoded, dc:creator, description,            guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]
     */

    public SkyAndTelescope()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "http://www.skyandtelescope.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String dscr = jsoupParse(prop).select("p").first().text();
        return dscr.startsWith("The post") ? "" : dscr;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script").remove();
        doc.select("[style]").removeAttr("style");
        doc.select("h1,h2").tagName("h3");

        for (Element e : doc.select(".wp-caption-text"))
            e.tagName("figcaption")
                    .removeAttr("class");

        Element article = doc.body();
        NewsStylist.repairLinks(article);
        return article.html();
    }

}
