package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeknikensVarld extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, media:content, media:credit, media:description, media:thumbnail, media:title, pubdate, title]

    public TeknikensVarld()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT},
                "");
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), prop.attr("medium"), "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".article-content");
        doc.select("script,.lureToFB,.related-gallery,.btdm-factbox,.pdf-shop").remove();

        doc.select(".wp-caption-text").tagName("figcaption");
        doc.select("[style]").removeAttr("style");

        cleanAttributes(doc.select("img"), "src");

        news.content = finalFormat(article, false);
    }

}
