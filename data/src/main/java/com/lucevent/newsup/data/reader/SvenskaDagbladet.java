package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class SvenskaDagbladet extends com.lucevent.newsup.data.util.NewsReader {

    public SvenskaDagbladet() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".Deck,.Body");

        e.select(".Body-ad,.AdPositionData,.Body-pull,figcaption,.Quote,.ExternalLink").remove();

        news.content = e.html();
    }

}
