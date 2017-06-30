package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class UniverseToday extends com.lucevent.newsup.data.util.NewsReader {

    //tags:[category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public UniverseToday()
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
        return jsoupParse(prop).select("p").first().text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select(".wp-caption-text").tagName("figcaption");
        doc.select("[style]").removeAttr("style");
        doc.select("iframe").attr("frameborder", "0");

        return doc.body().html();
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .timeout(10000)
                    .get();
        } catch (Exception ignored) {
        }
        return null;
    }

}