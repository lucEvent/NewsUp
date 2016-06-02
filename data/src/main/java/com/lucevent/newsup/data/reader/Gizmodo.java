package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Gizmodo extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]
     * [category, comments, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, slash:comments, title, wfw:commentrss]
     * [category, dc:creator, description, guid, item, link, pubdate, title]
     * [ dc:creator, description, feedburner:origlink, guid, item, link, media:thumbnail, pubdate, title]
     */

    public Gizmodo()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text().replace(" Read more", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {

        org.jsoup.select.Elements e = doc.select(".entry-content");
        if (e.isEmpty()) {
            e = doc.select(".single-article__content");

            if (e.isEmpty()) {
                e = doc.select("#content_post");

                if (e.isEmpty()) {
                    return;
                }
            }
        } else {
            e.select(".recommended,.ad-mobile,.referenced-wide,[x-inset=\"hidden\"]").remove();

            for (org.jsoup.nodes.Element iframe : e.select(".core-inset")) {
                String id = iframe.attr("id");
                if (id.startsWith("youtube")) {
                    String src = iframe.attr("data-recommend-id").replace("youtube://", "https://www.youtube.com/embed/");
                    iframe.attr("src", src);
                } else if (id.startsWith("vimeo")) {
                    String src = iframe.attr("data-recommend-id").replace("vimeo://", "https://player.vimeo.com/video/");
                    iframe.attr("src", src);
                } else {
                    iframe.remove();
                }
            }
        }
        news.content = e.html();
    }

}
