package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;

public class FriaTider extends com.lucevent.newsup.data.util.NewsReader {

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
                new int[]{},
                "http://www.friatider.se/",
                "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".field-items,.standfirst");
        e.select(".image-credit,script").remove();

        e.select("#bargraph").attr("style", "height:400px;");
        if (!e.isEmpty())
            news.content = NewsStylist.cleanComments(e.html());
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink).timeout(10000).userAgent(USER_AGENT).get();
        } catch (Exception ignored) {
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).timeout(10000).get();
        } catch (Exception ignored) {
        }
        return null;
    }

}
