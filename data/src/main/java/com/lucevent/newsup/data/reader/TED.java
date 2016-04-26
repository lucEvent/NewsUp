package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TED extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * Tags
     * [category, comments, content:encoded, dc:creator, description, guid, item, link, media:content, media:thumbnail, media:title, pubdate, title]
     * [category, description, enclosure, guid, item, jwplayer:talkid, link, media:content, media:thumbnail, pubdate, title]
     */
    public TED()
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
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Element content = org.jsoup.Jsoup.parse(prop.text()).body();
        for (org.jsoup.nodes.Element e : content.select("[style]"))
            e.removeAttr("style");

        content.select("[rel=\"nofollow\"] ~ *,[rel=\"nofollow\"]").remove();
        return content.html();
    }

    @Override
    protected News applySpecialCase(News news, String content)
    {
        if (content.isEmpty() && !news.enclosures.isEmpty())
            news.content = news.enclosures.get(0).html() + "<p>" + news.description + "</p>";

        return news;
    }

}
