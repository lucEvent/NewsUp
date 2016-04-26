package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Expressen extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags:  author, description, guid, item, link, pubdate, title]

    public Expressen()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected String parseDescription(org.jsoup.nodes.Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        String img = "";
        org.jsoup.select.Elements media = doc.select(".b-article__media,.b-slideshow__slider").select("img");
        for (org.jsoup.nodes.Element i : media) {
            String src = i.attr("template-src");
            img += "<img src=\"http:" + src + "\">";
        }
        org.jsoup.select.Elements content = doc.select(".b-text_article-body");

        if (content.isEmpty()) {
            content = doc.select(".b-text_article");

            if (content.isEmpty()) {
                content = doc.select(".b-text");

                if (content.isEmpty()) {
                    content = doc.select(".text--article-preamble,.text--article-body");

                    if (content.isEmpty() && img.isEmpty()) {
                        return;
                    }
                }
            }
        }
        news.content = img + content;
    }

}
