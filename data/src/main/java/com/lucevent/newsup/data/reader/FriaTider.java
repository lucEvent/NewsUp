package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class FriaTider extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: [comments, dc:creator, description, guid, item, link, pubdate, title]

    public FriaTider()
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
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".field-items,.standfirst");
        e.select(".image-credit").remove();

        if (!e.isEmpty())
            news.content = e.html();
    }

}
