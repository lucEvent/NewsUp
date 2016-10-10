package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheTimesOfIndia extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category, cmsid, dc:creator, description, guid, item, link,                                                                           pubdate, title]
     * [                             description, guid, item, link,                                                                           pubdate, title]
     * [                             description, guid, item, link, media:category, media:content, media:rating, media:text, media:thumbnail, pubdate, title]
     **/

    public TheTimesOfIndia()
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
    protected String parseLink(Element prop)
    {
        String link = prop.text();
        if (!link.startsWith("http:"))
            link = "http://timesofindia.indiatimes.com/" + link;
        return link;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements imgs = doc.select(".agencymainimg img");
        Element img = null;
        if (!imgs.isEmpty()) {
            img = imgs.first();
            img.attr("src", "http://timesofindia.indiatimes.com/" + img.attr("data-src"));
        }
        Elements articles = doc.select(".article-txt");
        Element article;
        if (articles.isEmpty()) {
            articles = doc.select(".article .content");

            if (!articles.isEmpty()) {

                for (Element e : articles.select("[style]"))
                    e.removeAttr("style");
                for (Element e : articles.select("[width],[height]")) {
                    e.removeAttr("width");
                    e.removeAttr("height");
                }
                news.content = articles.outerHtml().replace("src=\"../..", "src=\"http://blogs.timesofindia.indiatimes.com/");
            }
            return;
        }
        article = articles.get(0);
        article.select("script,style,.forcehide,.ad1,.readalso").remove();

        news.content = (img == null ? "" : img.outerHtml()) + article.outerHtml().replace("src=\"/", "src=\"http://timesofindia.indiatimes.com/");
        news.content = news.content.replace("<br>", "<p></p>");
    }

}
