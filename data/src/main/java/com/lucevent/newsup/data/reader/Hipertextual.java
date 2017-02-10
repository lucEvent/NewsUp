package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Hipertextual extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [                           dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     */

    public Hipertextual()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (news.content.isEmpty()) {
            news.content = news.description;
            news.description = "";
        }

        Document doc = org.jsoup.Jsoup.parse(news.content);

        doc.select(".wp-caption-text,aside,q,script[src*='twitter'],script[src*='instagram'],script[src*='swipe.js']").remove();
        doc.select("h1,h2").tagName("h3");

        Elements galleries = doc.select(".galleryWrapper");
        for (Element gallery : galleries)
            gallery.html(gallery.select("img").outerHtml());

        if (!galleries.isEmpty())
            doc.select("script").remove();

        news.content = "<base href='https://hipertextual.com'>" + doc.body().html();
        return news;
    }

}