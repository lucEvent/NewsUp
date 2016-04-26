package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TheAtlantic extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * Tags
     * [author, category, content, entry, id, link, media:content, name, published, summary, title]
     * [content:encoded, description, feedburner:origlink, guid, item, link, pubdate, title]
     * [category, description, feedburner:origlink, guid, item, link, pubdate, title]
     */

    public TheAtlantic()
    {
        super(TAG_ITEM_ITEMS_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION, TAG_SUMMARY},
                new int[]{TAG_CONTENT_ENCODED, TAG_CONTENT},
                new int[]{TAG_PUBDATE, TAG_PUBLISHED},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseTitle(Element prop)
    {
        return prop.text().replace("<em>", "").replace("</em>", "").replace("<i>", "").replace("</i>", "");
    }

    @Override
    protected String parseLink(Element prop)
    {
        String link = prop.text();
        if (link.isEmpty())
            link = prop.attr("href");
        return link;
    }

    @Override
    protected String parseCategory(Element prop)
    {
        String category = prop.text();
        if (category.isEmpty())
            category = prop.attr("term");
        return category;
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), prop.attr("medium"), prop.attr("width"));
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("[clear=\"all\"] ~ *,[clear=\"all\"],.callout,.partner-box,img[height=\"1\"]").remove();
        return doc.html();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".embed-code,[itemprop=\"description\"]");

        if (!e.isEmpty())
            news.content = e.html().replace("&lt;", "<").replace("&quot;", "\"").replace("&gt;", ">");

        else {
            e = doc.select("picture > img,.caption");
            if (!e.isEmpty())
                news.content = e.outerHtml().replace("data-src", "src");
        }
    }

}
