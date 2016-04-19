package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Gizmodo extends com.lucevent.newsup.data.util.NewsReader {

    public Gizmodo() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text().replace(" Read more...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {

        org.jsoup.select.Elements e = doc.select(".entry-content");
        if (e.isEmpty()) {
            e = doc.select(".single-article__content");

            if (e.isEmpty()) {
                e = doc.select("#content_post");

                if (e.isEmpty()) {
                    return;
                }
            }
        } else {
            e.select(".recommended,.ad-mobile,.referenced-wide,[x-inset=\"hidden\"]").remove();

            for (org.jsoup.nodes.Element iframe : e.select(".core-inset")) {
                String id = iframe.attr("id");
                if (id.startsWith("youtube")) {
                    String src = iframe.attr("data-recommend-id").replace("youtube://", "https://www.youtube.com/embed/");
                    iframe.attr("src", src);
                } else if (id.startsWith("vimeo")) {
                    String src = iframe.attr("data-recommend-id").replace("vimeo://", "https://player.vimeo.com/video/");
                    iframe.attr("src", src);
                } else {
                    iframe.remove();
                }
            }
        }
        news.content = e.html();
    }

}
