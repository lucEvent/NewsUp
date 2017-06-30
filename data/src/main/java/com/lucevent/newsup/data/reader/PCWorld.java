package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PCWorld extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [author, categories, category, dc:creator, description, enclosure, item, link, pubdate, title]
     * [author, categories, category, dc:creator, description, enclosure, item, link, media:content, pubdate, title]
     * [ description, enclosure, guid, item, link, media:category, media:content, media:credit, media:description, media:keywords, media:thumbnail, media:title, pubdate, title]
     */

    public PCWorld()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY, "media:category".hashCode()},
                new int[]{TAG_ENCLOSURE, "media:content".hashCode(), "media:thumbnail".hashCode()});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select(".jumpTag").remove();
        return doc.text();
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (news.link.contains("pcworld.com/video"))
            for (Enclosure e : news.enclosures)
                if (e.isVideo()) {
                    news.content = e.html() + "<p>" + news.description + "</p>";
                    news.enclosures.remove(e);
                    break;
                }
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("article");

        if (!article.isEmpty()) {
            Elements elems = article.select(".hero-img img,[itemprop='articleBody'],[itemprop='reviewBody']");

            if (elems.isEmpty())
                article = article.select(".product-hub-panoramic,.product-hub-articleBody");
            else
                article = elems;

            article.select("script,aside,.end-note,.credit,.product-list").remove();

        } else {

            article = doc.select("#slides");

            if (!article.isEmpty()) {
                article = article.select(".carousel-items .slide");

                for (Element slide : article) {
                    NewsStylist.cleanAttributes(slide);
                    slide.tagName("p");
                    slide.html(slide.select("img:not(.lazyslide),.title,.body").outerHtml());
                }
                article.select(".title").tagName("h3");
            }

        }
        article.select("h1,h2").tagName("h3");
        article.select("[style]:not(.instagram-media,.instagram-media *").removeAttr("style");

        news.content = article.outerHtml();
    }

}