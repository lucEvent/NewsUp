package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Hipertextual extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [dc:creator, description, guid, item, link, pubdate, title]

    public Hipertextual()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{},
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing Description
        Element article = jsoupParse(news.description);
        news.description = article.text();
        // end

        // Parsing enclosures
        Elements imgs = article.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("main");

        if (article.isEmpty()) {
            // ARTICLE EMPTY!!!!
            return;
        }

        article = article.select(".headlineSingle__lead,.articleHead,.historia");
        article.select("script,.wrapperBanner").remove();

        article.select("aside,q").tagName("blockquote");

        for (Element e : article.select("figure:has(noscript)"))
            e.html(e.select("noscript").html());

        news.content = finalFormat(article, true);
    }

}