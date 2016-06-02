package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

import java.io.IOException;

public class Make extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, dc:creator, description,            guid, item, link, pubdate,  title ]
     * [category, dc:creator, description, enclosure, guid, item, link, pubdate,  title ]
     */

    public Make()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).select("p:nth-of-type(1)").text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select("article");

        if (e.isEmpty()) {
            e = doc.select(".hentry > .row > .span8");

            if (e.isEmpty())
                return;

        } else
            e.select(".related-topics,.row-fluid,.ctx-clearfix,.ctx-sidebar-container,hr,#ctx-sl-subscribe,#ctx-module,#pubexchange_below_content").remove();

        for (org.jsoup.nodes.Element ns : e.select("noscript"))
            ns.tagName("p");

        for (org.jsoup.nodes.Element style : e.select("[style~=width]"))
            style.attr("style", "");

        news.content = e.html();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink).userAgent(USER_AGENT).get();
        } catch (IOException ignored) {
        }
        return super.getDocument(pagelink);
    }

}
