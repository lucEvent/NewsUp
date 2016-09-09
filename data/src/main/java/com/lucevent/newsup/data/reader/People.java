package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class People extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [author, category, description,       item, link, media:content, pubdate, title]
     * [dc:creator, category, content:encoded, description, guid, item, link, media:content, pubdate, title]
     * [author, category, description, guid, item, link, media:content, pubdate, title]
     */

    public People()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseTitle(Element prop)
    {
        return prop.text().replace("<em>", "").replace("</em>", "").replace("&#8211", "-");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select("#main-article .article-image-wrapper .node-image,#main-article .field-type-text-with-summary");

        if (e.isEmpty()) {
            e = doc.select("div[class=content]");

            if (e.isEmpty()) {
                e = doc.select(".post-body");

                if (e.isEmpty()) {
                    e = doc.select("article[class=content]");

                    if (e.isEmpty())
                        return;
                    else
                        e.select("header,.byline,[class=\"social article\"],.brightcovePlayerInline,#partner-content,.emote-wrap,#article-comments").remove();

                } else
                    e.select("div").remove();

            } else
                e.select(".byline,[class=\"social article\"],.brightcovePlayerInline").remove();

        } else
            e.select("object").remove();

        for (org.jsoup.nodes.Element img : e.select("noscript"))
            img.parent().html(img.html());

        for (org.jsoup.nodes.Element img : e.select("noscript"))
            img.parent().html(img.html());

        for (org.jsoup.nodes.Element video : e.select("iframe")) {
            String src = video.attr("src");
            if (!src.startsWith("http"))
                video.attr("src", "http:" + src);
        }

        for (org.jsoup.nodes.Element video : e.select(".youtube"))
            try {
                String src = video.select("embed").first().attr("src").replace("/v/", "/embed/");
                video.html("<iframe src=\"" + src + "\" frameborder=\"0\" allowfullscreen></iframe>");
            } catch (Exception ignored) {
            }
        news.content = e.html();
    }

}
