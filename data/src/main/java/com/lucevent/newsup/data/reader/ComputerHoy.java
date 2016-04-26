package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;


public class ComputerHoy extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: [dc:creator, description, guid, item, link, pubdate, title]

    public ComputerHoy()
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
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements img = doc.select("[itemprop=\"image\"]");
        org.jsoup.select.Elements e = doc.select("#ab_stickyid");

        if (e.isEmpty()) {
            e = doc.select("article > p");

            if (e.isEmpty())
                return;

        } else {
            e.select(".adcuadrado,blockquote").remove();
        }

        for (org.jsoup.nodes.Element iframe : e.select("iframe")) {
            String src = iframe.attr("src");
            if (!src.startsWith("http:"))
                iframe.attr("src", "http:" + src);
        }
        for (org.jsoup.nodes.Element style : e.select("[style]"))
            style.removeAttr("style");

        news.content = img.outerHtml() + e.html();
    }

}