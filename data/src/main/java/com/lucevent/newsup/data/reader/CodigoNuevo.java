package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class CodigoNuevo extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public CodigoNuevo()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("p").first().text().replace(" […]", "...");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Element article = jsoupParse(prop);
        article.select("script").remove();

        for (Element e : article.select("img")) {
            e.attr("src", e.attr("src").replace("-300x200", ""));
            cleanAttributes(e, "src");
        }
        for (Element e : article.select("a[href*='youtube']:has(img)")) {
            e.html(insertIframe(e.attr("href").replace("watch?v=", "embed/")));
            e.removeAttr("href");
            e.tagName("div");
        }

        return finalFormat(article, false);
    }

}
