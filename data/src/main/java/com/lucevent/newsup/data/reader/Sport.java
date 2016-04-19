package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Sport extends com.lucevent.newsup.data.util.NewsReader {

    public Sport() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).getElementsByTag("p").text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements img = doc.select(".sp-img,.sp-video").select("img");

        org.jsoup.select.Elements content = doc.select(".cuerpo-noticia");
        content.select(".sp-autor").remove();

        if (!content.isEmpty()) {
            news.content = img.outerHtml() + content.html();
        } else {
            content = doc.select(".cuerpo-opinion");
            content.select(".firma").remove();

            if (!content.isEmpty()) {
                news.content = content.html();
            }
        }
    }

}