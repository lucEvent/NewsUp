package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

public class Aftonbladet extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: category, description, guid, item, link, pubdate, title

    public Aftonbladet()
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
    protected News applySpecialCase(News news, String content)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        news.enclosures = new Enclosures();
        org.jsoup.select.Elements imgs = doc.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.get(0).attr("src"), "image/jpg", "0"));

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".player__poster-image,.abLeadText,.abBodyText");
        if (!e.isEmpty()) {
            String img = "";
            if (!news.enclosures.isEmpty()) img = news.enclosures.get(0).html();

            news.content = img + e.html();
        }
    }

}
