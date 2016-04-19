package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class LaVanguardia extends com.lucevent.newsup.data.util.NewsReader {

    public LaVanguardia() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        int idash = news.description.indexOf("- ");
        if (idash != -1) news.description = news.description.substring(idash + 2);
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"]");

        for (org.jsoup.nodes.Element style : e.select("[style]")) {
            style.removeAttr("style");
        }

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}
