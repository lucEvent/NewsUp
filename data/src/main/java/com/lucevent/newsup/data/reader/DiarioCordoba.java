package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import java.io.IOException;
import java.net.URL;

public class DiarioCordoba extends com.lucevent.newsup.data.util.NewsReader {

    public DiarioCordoba() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.title = news.title.replace("<![CDATA[", "").replace("]]>", "");
        news.description = news.description.replace("<p>", "").replace("</p>", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {

        org.jsoup.select.Elements e = doc.select(".bxGaleriaNoticia img,#CuerpoDeLaNoticia");

        if (e.isEmpty()) {
            return;
        }

        news.content = e.outerHtml();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink) {
        try {
            return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(rsslink);
    }

}