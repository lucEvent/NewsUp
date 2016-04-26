package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class ElJueves extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: [description, enclosure, guid, item, link, pubdate, title]

    public ElJueves()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".post > p:not(.extra-info)");

        if (e.isEmpty())
            return;

        for (org.jsoup.nodes.Element img : e.select("img")) {
            String src = img.attr("src");
            if (!src.startsWith("http:")) {
                img.attr("src", "http://www.eljueves.es" + src);
            }
        }
        news.content = e.outerHtml();
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(pagelink).openStream(), "ISO-8859-1", pagelink);
        } catch (IOException ignore) {
        }
        return super.getDocument(pagelink);
    }

}