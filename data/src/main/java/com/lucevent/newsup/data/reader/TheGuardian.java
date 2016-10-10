package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheGuardian extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category, dc:creator, dc:date, description, guid, item, link, media:content, media:credit,                    pubdate, title]
     * [category, dc:creator, dc:date, description, guid, item, link, media:content, media:credit, media:description, pubdate, title]
     * [category, dc:creator, dc:date, description, guid, item, link,                                                 pubdate, title]
     **/

    public TheGuardian()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements graph = doc.select(".media-primary img:not(#article img),.media-primary video:not(#article video)");

        Elements article = doc.select("[itemprop=\"articleBody\"]:not([itemprop=\"articleBody\"] [itemprop=\"articleBody\"])");
        if (article.isEmpty()) {
            article = doc.select("#article");
            article.select("script,[itemprop=\"headline\"],.meta__extras,.submeta").remove();
            article.select("li").tagName("p");
        }
        article.select("script,[class^=\"block-share\"],[class^=\"share-modal\"],aside").remove();
        if (news.link.contains("/video/"))
            article.select(".u-cf").remove();

        for (Element e : article.select("figure")) {
            Elements img = e.select("picture,video");
            if (!img.isEmpty())
                e.html(img.outerHtml());
        }
        article.select("h1,h2").tagName("h3");

        news.content = graph.outerHtml() + article.html();
    }

}
