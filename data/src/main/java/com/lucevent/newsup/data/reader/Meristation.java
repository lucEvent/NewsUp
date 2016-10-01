package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Meristation extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [dc:creator, description, guid, item, link, pubdate, title]

    public Meristation()
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
    protected News onNewsRead(News news)
    {
        Document d = Jsoup.parse(news.description);

        news.description = d.select(".field-field-seo-description .field-items").text();

        d.select("h2").tagName("h3");
        d.select(".field,.darwin-phpbb-comments-link").remove();
        news.content = d.outerHtml();

        return news;
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
            System.out.println("[" + e.getClass().getSimpleName() + "] Trying again");
        }
        return super.getDocument(pagelink);
    }

}
