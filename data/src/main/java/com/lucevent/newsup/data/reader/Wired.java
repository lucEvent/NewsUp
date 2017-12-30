package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Wired extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, dc:creator, dc:modified, dc:publisher, description, guid, item, link, media:content, media:keywords, media:thumbnail, pubdate, title]

    public Wired()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"media:thumbnail".hashCode()},
                "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("main .article-lede-component__photo,.article-body-component");

        if (!article.isEmpty()) {
            article.select(".inset-left-component,.image-embed-component,.related-cne-video-component,.mid-banner-wrap").remove();
        } else {
            article = doc.select("main header .wp-caption,main .content");
            article.select(".ui-social-wrapper,.clearfix,#related,.visually-hidden,#article-tags").remove();
        }
        article.select("[data-reactid]").removeAttr("data-reactid");
        article.select(".special-carve").tagName("blockquote");

        news.content = finalFormat(article, false);
    }

}
