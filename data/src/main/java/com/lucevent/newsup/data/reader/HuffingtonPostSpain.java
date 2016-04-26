package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class HuffingtonPostSpain extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * Tags:
     * [author, description, enclosure, guid, item, link, pubdate, title]
     * [author, content, entry, id, link, name, published, title, updated, uri]
     */

    public HuffingtonPostSpain()
    {
        super(TAG_ITEM_ITEMS_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_ID, TAG_GUID},
                new int[]{TAG_CONTENT},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE, TAG_UPDATED},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String description = org.jsoup.Jsoup.parse(prop.text()).text();
        int index = description.indexOf("Leer más:");
        if (index != -1)
            description = description.substring(0, index);
        return description;
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Element body = org.jsoup.Jsoup.parse(prop.text()).getElementsByTag("body").get(0);
        org.jsoup.select.Elements ee = body.children();

        int index = ee.indexOf(ee.select("blockquote").last()) - 1;
        if (index >= 0)
            for (; index < ee.size(); ++index)
                ee.get(index).remove();

        String content = body.outerHtml();
        index = content.indexOf("<hh--");
        if (index != -1)
            content = content.substring(0, index);

        return content;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script,.entry-text > a").remove();

        org.jsoup.select.Elements cnt = doc.select(".top-media,.entry-text");
        cnt.select(".tag-cloud,[style^=\"display: n\"],.comment-button,.read-more,.corrections,.extra-content,.slideshow-thumb").remove();

        org.jsoup.nodes.Element e = cnt.select("blockquote").last();
        if (e != null) {
            String bq = e.html();
            if (bq.contains("ADEMÁS") || bq.contains("TE PUEDE INTERESAR") ||
                    bq.contains("AVÍSANOS") || bq.contains("MÁS SOBRE") ||
                    bq.contains("MÁS PARA")) {
                e.remove();
            }
        }
        e = cnt.select("p").last();
        if (e != null) {
            if (e.html().contains("big.assets.huffingtonpost")) {
                e.remove();
            }
        }
        cnt.select("img[src^=\"http://big.assets\"]");

        news.content = cnt.html();
    }

}