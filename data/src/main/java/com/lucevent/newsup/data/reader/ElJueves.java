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

    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        Elements main = doc.select("#main");

        Elements article;
        if (news.link.contains("/news/") || news.link.contains("/jarticulos/")
                || news.link.contains("/vloggers/") || news.link.contains("/articulos/")
                || news.link.contains("/opinion/")) {
            article = main.select(".row .sub-title,.row .detailMedia,.row #detail");

            if (article.isEmpty())
                article = main.select(".vineta img");

            article.select("h2").tagName("h4");

        } else if (news.link.contains("/manda-guevos/")) {

            article = main.select("article").get(0).select(".row .sub-title,.row .detailMedia,.row #detail");

        } else if (news.link.contains("/temazo/") || news.link.contains("/sexo/") || news.link.contains("/mmmh/")) {

            article = main.select("#slider-temazo .slide");
            article.select(".relacionats").remove();

            for (Element e : article.select("[data-src]"))
                e.attr("src", e.attr("data-src")).removeAttr("data-src");


        } else if (news.link.contains("/blogs/")) {
            article = main.select("article").get(0).select("#detail");

        } else {
            return;
        }

        news.content = NewsStylist.base("http://www.eljueves.es/") + NewsStylist.cleanComments(article.outerHtml());
    }

}