package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;


public class DigitalTrends extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: [category, dc:creator, description, enclosure, guid, item, link, pubdate, thumbnail, title]

    public DigitalTrends()
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
        return org.jsoup.Jsoup.parseBodyFragment(prop.text()).getElementsByTag("p").get(0).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".dt-video-container,.dt-iframe-header-media,.attachment-dt_header_media,.attachment-dt_header_media_full_width,article");

        if (!e.isEmpty())
            news.content = e.outerHtml();
    }

}
