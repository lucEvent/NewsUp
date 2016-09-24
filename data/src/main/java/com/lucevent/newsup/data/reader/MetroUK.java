package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetroUK extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, section, title]
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate,          title]
     * [category, content:encoded, dc:creator, description,            guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate,          title]
     **/

    public MetroUK()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        Elements c = doc.select(".article-body");

        c.select(".thumbnail-link,.mor-link,.ad-slot-container,.item-share-buttons,.zone-post-strip,.metro-sassy-poll,.metro-email-signup,form").remove();

        news.content = c.html();
    }

}
