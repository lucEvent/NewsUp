package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;

public class ElJueves extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [        description,            guid, item, link, pubdate, title]
     * [author, description, enclosure, guid, item, link, pubdate, title]
     */

    public ElJueves()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});

        this.style = NewsStylist.base("http://www.eljueves.es/");
    }

    @Override
    protected String parseTitle(Element prop)
    {
        Document d = org.jsoup.Jsoup.parse(prop.text());
        d.charset(Charset.forName("ISO-8859-1"));
        return d.text();
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (news.link.contains(".es/vineta-del-dia/") || news.link.contains(".es/juevflix/")) {
            news.content = news.description;
            news.description = "";
        } else {
            news.description = org.jsoup.Jsoup.parse(news.description).text();
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        Elements main = doc.select("#main");

        String tag = NewsStylist.subStringBetween(news.link, "http://www.eljueves.es/", "/", false);
        Elements article;
        switch (tag) {
            case "news":
            case "opinion":
                // jarticulos/vloggers/articulos
                article = main.select(".fgs-slider .slide[id] img");
                if (!article.isEmpty()) {
                    for (Element img : article.select("img[data-src]"))
                        img.attr("src", img.attr("data-src"));
                    break;
                }

                article = main.select(".vineta img");
                if (!article.isEmpty())
                    break;

                article = main.select(".sub-title,.detailMedia img,#detail");
                article.select("h2").tagName("h4");
                article.select("[style]").removeAttr("style");
                break;
            case "manda-guevos":

                article = main.select("article").get(0).select(".sub-title,.detailMedia,#detail");

                for (Element e : article.select("figure.detalle-img"))
                    e.html(e.select("img").outerHtml());

                break;
            case "mmmh":
            case "temazo":
                // sexo

                article = main.select(".fgs-slider .slide[id] img");

                for (Element img : article.select("img[data-src]"))
                    img.attr("src", img.attr("data-src"));

                break;
            case "blogs":

                article = main.select("article").get(0).select("#detail");
                break;
            default:
                return;
        }

        NewsStylist.cleanAttributes(article.select("img"), "src");
        news.content = NewsStylist.cleanComments(article.outerHtml());
    }

}