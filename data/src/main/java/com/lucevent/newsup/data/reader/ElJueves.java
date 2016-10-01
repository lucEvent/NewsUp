package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;

public class ElJueves extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [description, enclosure, guid, item, link, pubdate, title]

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
        String finalcontent;
        if (news.link.contains(".es/news/") || news.link.contains(".es/jarticulos/")
                || news.link.contains(".es/vloggers/")) {
            article = main.select(".row .sub-title,.row .detailMedia,.row #detail");

            if (article.isEmpty())
                article = main.select(".vineta img");

            finalcontent = article.outerHtml();
        } else if (news.link.contains(".es/manda-guevos/")) {

            article = main.select("article").get(0).select(".row .sub-title,.row .detailMedia,.row #detail");
            finalcontent = article.html();
        } else if (news.link.contains(".es/temazo/") || news.link.contains(".es/sexo/")) {

            article = main.select("#slider-temazo .slide");
            article.select(".relacionats").remove();
            finalcontent = article.html().replace("data-src=\"/", "src=\"http://www.eljueves.es/");
        } else if (news.link.contains(".es/blogs/")) {
            article = main.select("article").get(0).select("#detail");
            finalcontent = article.html();
        } else {
            //todo
            finalcontent = main.outerHtml();
        }

        news.content = finalcontent.replace("src=\"/", "src=\"http://www.eljueves.es/");
    }

}