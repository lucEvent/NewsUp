package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SvenskaDagbladet extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, description, guid, item, link, pubdate, title]

    public SvenskaDagbladet()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://www.svd.se/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements e = doc.select(".Deck,.Body");
        e.select(".Body-ad,.AdPositionData,.Body-pull,figcaption,.Quote,.ExternalLink,.Ad,script,.ThumbnailList,.paywall-loader,a[href='/premium'],.Figure-expandIcon,.scrbbl-embed,[data-loader-url]").remove();

        NewsStylist.cleanAttributes(e.select("img[srcset]"), "srcset");

        e.select("h1,h2").tagName("h3");
        e.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

        news.content = e.outerHtml();
    }

}
