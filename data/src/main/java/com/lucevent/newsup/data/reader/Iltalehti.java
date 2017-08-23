package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;

public class Iltalehti extends com.lucevent.newsup.data.util.NewsReader {

    // Tags:[description, guid, item, link, pubdate, title]

    public Iltalehti()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{},
                "http://www.iltalehti.fi/",
                "");
    }

    @Override
    protected Document getDocument(String rsslink)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
        } catch (Exception ignored) {
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("isense");

        if (!article.isEmpty()) {
            article.select(".author,.important-articles-t,.kp-share-area,.kuvateksti,.fb-comments__outer-container,.recommended-toaster,#tuoreimmat,script").remove();

            article.select("[style]").removeAttr("style");
            article.select("h1,h2").tagName("h3");

            NewsStylist.cleanAttributes(article.select("img"), "src");

            news.content = NewsStylist.cleanComments(article.html());
        }
    }

}
