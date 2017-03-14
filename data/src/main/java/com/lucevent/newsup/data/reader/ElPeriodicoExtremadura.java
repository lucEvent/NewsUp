package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ElPeriodicoExtremadura extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public ElPeriodicoExtremadura()
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
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".bxGaleriaNoticia img,#CuerpoDeLaNoticia");
        article.select(".Banner,script").remove();

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        article.select("h1,h2").tagName("h3");
        article.select("[style]").removeAttr("style");

        news.content = article.outerHtml();
    }

}
