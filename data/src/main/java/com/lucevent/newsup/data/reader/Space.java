package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Space extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [description, item, link, media:content, pubdate, source, title]

    public Space()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{"source".hashCode()},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements article = doc.select(".article-hero,.article-content");

        article.select(".ooyala_embed_player,script,p > em").remove();

        if (article.isEmpty()) {
            article = doc.select(".multiPageItem");

            if (article.isEmpty()) {
                article = doc.select(".hd");
                article.select(".ad_companion,h1,.byline,.pure-g").remove();
                news.content = article.html();

            } else
                news.content = article.first().html();

        } else
            news.content = article.html();

        news.content = news.content.replace("src=\"//w", "src=\"http://w");
    }

}
