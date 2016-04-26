package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class BBC extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: 	[description, guid, item, link, media:thumbnail, pubdate, title]

    public BBC()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select("[property=\"articleBody\"]");

        if (e.isEmpty()) {
            e = doc.select(".story-body");
            if (e.isEmpty()) {
                e = doc.select("#emp-content > .emp-description");
                if (e.isEmpty()) {
                    e = doc.select(".main_content_wrapper");
                    if (e.isEmpty()) {
                        e = doc.select("#main-content .emp-decription");
                        if (e.isEmpty()) {
                            e = doc.select(".storybody");

                            if (e.isEmpty()) {
                                return;
                            } else {
                                e.select(".videoInStoryB,#socialBookMarks,.mvtb,script").remove();
                            }
                        }
                    } else {
                        e.select("h1,.titlebar,.livestats-tracking,.secondary_content_container,.related_topics").remove();
                    }
                }
            } else {
                e.select("h1,.date,.ad_wrapper,#article-sidebar,#headline,.introduction,#also-related-links,.share-tools-footer,.bbccom_advert_placeholder").remove();
            }
        }
        org.jsoup.select.Elements styles = e.select("[style]");
        for (org.jsoup.nodes.Element style : styles) {
            style.attr("style", "");
        }
        org.jsoup.select.Elements imgs = e.select(".js-delayed-image-load");
        for (org.jsoup.nodes.Element img : imgs) {
            String src = img.attr("data-src");
            img.html("<img src=\"" + src + "\" >");
        }
        news.content = e.html();
    }

}
