package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
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
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("p").first().text().replace(" […]", "...");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script").remove();
        doc.select("h1,h2").tagName("h3");

        for (Element e : doc.select("img")) {
            e.attr("src", e.attr("src").replace("-300x200", ""));
            NewsStylist.cleanAttributes(e, "src");
        }
        for (Element e : doc.select("a[href*='youtube']:has(img)")) {
            e.html(Enclosure.iframe(e.attr("href").replace("watch?v=", "embed/")));
            e.removeAttr("href");
            e.tagName("div");
        }
        return doc.body().html();
    }

}
