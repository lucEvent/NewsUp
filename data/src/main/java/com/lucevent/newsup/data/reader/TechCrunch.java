package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TechCrunch extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category,  dc:creator, description,            feedburner:origlink, guid, item, link, media:content, media:thumbnail, media:title, pubdate,  title, twitter, ]
     * [category,  dc:creator, description,            feedburner:origlink, guid, item, link, media:content, media:thumbnail, media:title, pubdate,  title, ]
     * [category,  dc:creator, description, enclosure, feedburner:origlink, guid, item, link, media:content, media:thumbnail, media:title, pubdate,  title, twitter, ]
     * [category,  dc:creator, description, enclosure, feedburner:origlink, guid, item, link, media:content, media:thumbnail, media:title, pubdate,  title, ]
     * [category,  dc:creator, description,                                 guid, item, link, media:content, media:thumbnail, media:title, pubdate,  title, twitter, ]
     * [category,  dc:creator, description,                                 guid, item, link, media:content, media:thumbnail, media:title, pubdate,  title, ]
     **/

    public TechCrunch()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("article .article-entry");

        if (article.isEmpty()) {
            article = doc.select(".textwidget");

            if (article.isEmpty()) {
                article = doc.select(".slide");
                article.select(".slide-last").html("");
            }

        }
        article.select("script,.aside-related-articles,.controls,.slideshow .enter-wrapper,.inset-section,.social-share,.inset-ad,.contributor-byline").remove();
        article.select("div").tagName("p");

        for (Element img : article.select("img")) {
            String src = img.attr("data-full-size-image");
            if (!src.isEmpty())
                img.attr("src", src);

            img.removeAttr("data-full-size-image");
            img.removeAttr("width");
            img.removeAttr("height");
        }
        for (Element e : article.select("h2"))
            e.tagName("h3");
        for (Element e : article.select("[style]"))
            e.removeAttr("style");

        news.content = article.html();
    }

}
