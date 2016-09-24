package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class USAToday extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [content:encoded, description, guid, item, link, pubdate, title]
     * [content:encoded, description, enclosure, feedburner:origlink, guid, item, link, pubdate, title]
     */

    public USAToday()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String dscr = Jsoup.parse(prop.text()).text();
        return dscr.substring(0, Math.min(300, dscr.length()));
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".story-image,.body-text,.presto-h2,.presto-h3,.oembed-graphiq,.oembed-youtube,.ui-pluto-video-wrapper-inner,.caption-text:not(.short-caption),.oembed-asset-photo-image,.interactive-map-iframe");
        article.select("h2").tagName("h3");
        news.content = article.outerHtml().replace("height=\"100%\"", "").replace("width=\"100%\"", "");
    }

}
