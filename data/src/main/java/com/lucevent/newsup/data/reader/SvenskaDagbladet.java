package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

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
        org.jsoup.select.Elements e = doc.select(".Deck,.Body");

        e.select(".Body-ad,.AdPositionData,.Body-pull,figcaption,.Quote,.ExternalLink").remove();

        news.content = e.html();
    }

}
