package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.select.Elements;

public class DagensNyheter extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [dc:date, description, guid, item, link, pubdate, title]
     * [dc:creator, description, guid, item, link, pubdate, title, ]
     */

    public DagensNyheter()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{},
                "http://www.dn.se/",
                "");
    }

    @Override
    protected String parseDescription(org.jsoup.nodes.Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements imgs = doc.select(".article__header-img noscript");
        Elements preamble = doc.select(".article__body .article__lead");
        Elements content = doc.select(".article__body .article__body-content");
        content.select("style,.ad-outer-container,script,.scrbbl-embed,.ad-container").remove();
        content.select("h1,h2").tagName("h3");
        content.select("[style]").removeAttr("style");

        NewsStylist.cleanAttributes(content.select("img"), "src");
        NewsStylist.repairLinks(content);

        news.content = preamble.html() + imgs.html() + content.html();
    }

}
