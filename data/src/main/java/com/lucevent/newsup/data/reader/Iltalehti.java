package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

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
                "");
    }

    @Override
    protected Document getDocument(String url)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
        } catch (Exception ignored) {
        }
        return super.getDocument(url);
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("isense");

        if (!article.isEmpty()) {
            article.select(".author,.important-articles-t,.kp-share-area,.kuvateksti,.fb-comments__outer-container,.recommended-toaster,#tuoreimmat,script,.kainalo").remove();

            article.select("[style]").removeAttr("style");
            cleanAttributes(article.select("img"), "src");

            news.content = finalFormat(article, false);
        }
    }

}
