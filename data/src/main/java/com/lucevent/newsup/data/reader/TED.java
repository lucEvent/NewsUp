package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TED extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, description, guid, item, link, media:content, media:thumbnail, pubdate, title | enclosure, jwplayer:talkid ]
     * [category, description, guid, item, link, media:content, media:thumbnail, pubdate, title | content:encoded, dc:creator, media:title]
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
                new int[]{TAG_ENCLOSURE, "media:thumbnail".hashCode()},
                "https://www.ted.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("[rel='nofollow'] ~ *,[rel='nofollow'],.wp-caption-text,img[src^='http://pixel.wp.com']").remove();

        doc.select(".wp-caption").tagName("p");
        doc.select("[style]").removeAttr("style");
        doc.select("h1,h2").tagName("h3");
        doc.select("iframe").attr("frameborder", "0");

        NewsStylist.wpcomwidget(doc.select("form[id]"));
        doc.select("form,script").remove();

        NewsStylist.cleanAttributes(doc.select("img"), "src");
        return doc.body().html();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String type = prop.attr("type");
        if (!type.contains("video"))
            type = "image";

        return new Enclosure(prop.attr("url"), type, prop.attr("length"));
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (news.content.isEmpty() && !news.enclosures.isEmpty()) {
            for (int i = 0; i < news.enclosures.size(); i++) {
                Enclosure e = news.enclosures.get(i);
                if (e.isVideo()) {
                    news.enclosures.remove(i);
                    news.content = e.html();
                    break;
                }
            }
            news.content += "<p>" + news.description + "</p>";
        }
        return news;
    }

}
