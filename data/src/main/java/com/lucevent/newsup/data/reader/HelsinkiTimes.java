package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class HelsinkiTimes extends com.lucevent.newsup.data.util.NewsReader {

    public HelsinkiTimes() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".item-page > p,.item-page .thumbnail img");

        if (!e.isEmpty()) {
            org.jsoup.select.Elements imgs = e.select("img");
            for (org.jsoup.nodes.Element img : imgs) {
                img.attr("src", "http://www.helsinkitimes.fi" + img.attr("src"));
            }

            news.content = e.outerHtml();
        }
    }

}