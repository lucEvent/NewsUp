package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

public class Yle extends com.lucevent.newsup.data.util.NewsReader {

    //Tags:[category, content:encoded, description, enclosure, guid, item, link, pubdate, title]

    public Yle()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.content.isEmpty())
            if (org.jsoup.Jsoup.parse(news.content).text().length() != 0) {

                String img = "";
                for (Enclosure e : news.enclosures)
                    img += e.html();

                news.content = "<meta charset=\"UTF-8\">" + img + news.content;
            }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".text");

        if (!e.isEmpty())
            news.content = e.html();
    }

}