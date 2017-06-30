package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Discover extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [description, guid, item, link, media:content, media:thumbnail, pubdate, title]

    public Discover()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_MEDIA_CONTENT});

        this.style = NewsStylist.base("http://discovermagazine.com");
    }

    @Override
    protected String parseLink(Element prop)
    {
        return prop.text();
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.text(), "image", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        if (doc.baseUri().contains("/galleries/")) {
            Elements article = doc.select(".gallery");

            Elements description = article.select(".description");
            Elements gallery = article.select(".display").select(".photo table img,.credit,h2,.caption");

            article.select("[style]").removeAttr("style");
            article.select("h1,h2").tagName("h3");
            article.select(".caption,.credit").tagName("figcaption");

            news.content = description.outerHtml() + gallery.outerHtml();
            return;
        }

        Elements article = doc.select(".entry > p,.entry img:not(.entry > p img,.wp-smiley),.entry > blockquote,.entry h4");

        if (article.isEmpty()) {
            article = doc.select(".segment .mediaContainer,.segment .content");
            article.select(".content").tagName("p");
        }
        article.select("script,.mobile").remove();

        article.select("[style]").removeAttr("style");
        article.select("h1,h2").tagName("h3");

        for (Element e : article.select("span[title^='ctx_ver']"))
            e.parent().html("");

        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(article);

        news.content = article.outerHtml().replace("<p>&nbsp;</p>", "");
    }

}