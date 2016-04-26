package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class RollingStone extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: [content:encoded, description, enclosure, guid, item, link, pubdate, title]

    public RollingStone()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements img = doc.select(".article-img-holder img,.article-img-holder iframe");
        org.jsoup.select.Elements content = doc.select(".article-content,.long-list-item");

        if (content.isEmpty()) {
            img = doc.select("article .asset-container img,article .asset-container iframe");
            content = doc.select("article .body-text");

            if (content.isEmpty())
                return;
            else
                content.select(".related-article").remove();

        } else {
            content.select(".related-article,.social-icons-container,.related-topics,.sidebar").remove();
        }

        news.content = img.outerHtml() + content.outerHtml();
    }

}
