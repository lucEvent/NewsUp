package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Discover extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // Tags: [description, guid, item, link, media:content, media:thumbnail, pubdate, title]

    public Discover()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.text(), "image", "0");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".entry");
        if (e.isEmpty()) {
            e = doc.select(".segment");

            if (e.isEmpty()) {
                return;
            } else {
                org.jsoup.select.Elements imgs = e.select("img");

                for (org.jsoup.nodes.Element img : imgs) {
                    String src = img.attr("src");
                    String start = "http://discovermagazine.com";
                    if (!src.contains(start)) img.attr("src", start + src);
                }
            }
        } else {
            e.select(".navigation,h1,.meta,.shareIcons,blockquote,.categories,#disqus_thread,.fb-post").remove();
        }
        org.jsoup.select.Elements imgs = e.select("[style]");
        for (org.jsoup.nodes.Element img : imgs) {
            img.attr("style", "");
        }
        news.content = e.outerHtml();
    }

}