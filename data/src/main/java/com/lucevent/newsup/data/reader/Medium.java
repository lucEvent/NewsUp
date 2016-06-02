package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Medium extends com.lucevent.newsup.data.util.NewsReader {

    // Tags:      [dc:creator, description, guid, item, link, pubdate, title]

    public Medium()
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
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).select(".medium-feed-snippet").html();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select("main section .section-content");

        for (org.jsoup.nodes.Element title : e.select("h3")) {
            title.remove();
            break;
        }

        for (org.jsoup.nodes.Element fig : e.select("figure")) {
            org.jsoup.select.Elements img = fig.select("noscript");
            fig.html(img.html());
        }

        news.content = e.html();
    }

}
