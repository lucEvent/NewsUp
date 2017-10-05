package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LifeScienceSweden extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [description, guid, item, link, pubdate, title]

    public LifeScienceSweden()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{},
                "https://www.lifesciencesweden.se/",
                "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("article > h3,article > .body-text");
        article.select("script,style,.more-about").remove();

        article.select("[style]").removeAttr("style");
        article.select(".popup-gallery div").tagName("figcaption");

        NewsStylist.repairLinks(article);

        news.content = article.outerHtml();
    }

}