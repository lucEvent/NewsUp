package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Sport extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.score{width:100%;background-color:#f4f4f4;display:table;position:relative;margin:1.5rem 0;}.score div{color:#fff;display" +
            ":table-cell;vertical-align:middle;padding:.5rem;}.score .result{color:#282828;font-size:2rem;line-height:4.6rem;text-align:center;margin-bottom:0;}.score p img{w" +
            "idth:3.5rem;height:3.5rem;margin:0 .5rem;vertical-align:middle;display:inline-block;}</style>";

    // tags:  description, guid, item, link, pubdate, title

    public Sport()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{},
                SITE_STYLE);
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.getElementsByTag("p").text();

        Elements img = doc.select("img");
        if (!img.isEmpty()) {
            news.enclosures.add(new Enclosure(img.get(0).attr("src"), "image", ""));
        }
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements articles = doc.select("div.middle");

        if (articles.isEmpty())
            return;

        Element article = articles.first();
        article.select("script,.sp-add,.box-left-55,.sp-socialbox,.relations,.player-zeta,ul.options,ul.list").remove();
        article.select("span[style]").removeAttr("style");

        Elements gallery = article.select(".sp-galeria");
        if (!gallery.isEmpty()) {
            article.select("figure.thumb").remove();

            Elements items = gallery.select("figure.slider-item");
            items.select(".options").remove();
            items.removeAttr("data-slider-item-url").removeAttr("data-slider-item-description").removeAttr("data-slider-item-date");
            gallery.html(items.outerHtml());
        }
        article.select("img").removeAttr("alt").removeAttr("title");

        news.content = finalFormat(article, false);
    }

}