package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;


public class DigitalTrends extends com.lucevent.newsup.data.util.NewsReader {

    public DigitalTrends() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).getElementsByTag("p").get(0).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".dt-video-container,.dt-iframe-header-media,.attachment-dt_header_media,.attachment-dt_header_media_full_width,article");

        if (!e.isEmpty()) {
            news.content = e.outerHtml();
        }
    }

}
