package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FullMusculo extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:copyright, media:description, media:thumbnail, media:title, post-id, pubdate, title]

    public FullMusculo()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("div").text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select(".wp-embedded-content,iframe[src*='adsystem'],.aalb-pg-ad-unit,script").remove();

        doc.select("[style]").removeAttr("style");
        doc.select("[align]").removeAttr("align");
        doc.select("h1,h2").tagName("h3");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return NewsStylist.cleanComments(doc.body().html());
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(pagelink);
    }

}
