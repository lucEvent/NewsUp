package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import java.io.IOException;
import java.net.URL;

public class ElPeriodicoCa extends com.lucevent.newsup.data.util.NewsReader {

    public ElPeriodicoCa() {
        super();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink) {
        if (rsslink.equals("http://www.elperiodico.cat/ca/rss/rss_portada.xml")) {
            try {
                return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty()) {
            news.content = content;
        }
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        doc.select("script").remove();

        org.jsoup.select.Elements intro = doc.select(".ep-video,.unit > .ep-img,.unit > .ep-galeria");
        intro.select(".carousel").remove();
        intro = intro.select("img");

        org.jsoup.select.Elements content = doc.select(".cuerpo-noticia,.cuerpo-opinion");

        content.select(".fecha,.carousel,.thumb-pie,.cred").remove();
        content = content.select("p,a > img,h2,h3,h4,h5,h6");

        news.content = intro.outerHtml() + content.outerHtml();
        if (news.content.length() < 80) {
            news.content = null;
        }
    }

}
