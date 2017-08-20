package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

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
                new int[]{});

        this.style = NewsStylist.base("https://hipertextual.com/");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).text();
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

        article.select("aside").tagName("blockquote");
        article.select("h1,h2").tagName("h3");

        for (Element e : article.select("figure:has(noscript)")) {
            e.html(e.select("noscript").html());
        }


        news.content = article.outerHtml();
    }

}