package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

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
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text().replace("Más información", "");
    }

    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        Elements main = doc.select("#main");

        Elements article = null;
        String finalcontent;
        if (news.link.contains("eljueves.es/vineta-del-dia/")) {
            article = main.select(".vineta");
            article.select(".flex-direction-nav").remove();
            finalcontent = article.html();
        } else if (news.link.contains("eljueves.es/news/") || news.link.contains("eljueves.es/jarticulos/")) {
            article = main.select(".row .sub-title,.row .detailMedia,.row #detail");

            if (article.isEmpty()) {
                article = main.select(".vineta img");
            }
            finalcontent = article.outerHtml();
        } else if (news.link.contains("eljueves.es/manda-guevos/")) {

            article = main.select("article").get(0).select(".row .sub-title,.row .detailMedia,.row #detail");
            finalcontent = article.html();
        } else if (news.link.contains("eljueves.es/temazo/")) {

            article = main.select("#slider-temazo .slide");
            article.select(".relacionats").remove();
            finalcontent = article.html().replace("data-src=\"/", "src=\"http://www.eljueves.es/");
        } else if (news.link.contains("eljueves.es/blogs/")) {
            article = main.select("article").get(0).select("#detail");
            finalcontent = article.html();
        } else {
            //todo
            finalcontent = main.outerHtml();
        }

        news.content = finalcontent.replace("src=\"/", "src=\"http://www.eljueves.es/");
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(pagelink).openStream(), "ISO-8859-1", pagelink, new Parser(new XmlTreeBuilder()));
        } catch (IOException ignore) {
            return super.getDocument(pagelink);
        }
    }

}