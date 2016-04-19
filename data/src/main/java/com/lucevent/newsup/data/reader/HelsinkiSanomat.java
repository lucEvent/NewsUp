package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class HelsinkiSanomat extends com.lucevent.newsup.data.util.NewsReader {

    public HelsinkiSanomat() {
        super();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements intro = doc.select(".scalable-article-top > .main-image-area > .img-wrapper");
        org.jsoup.select.Elements content = doc.select(".article-text-content");

        if (content.isEmpty()) {
            content = doc.select(".additional-article");

            if (content.isEmpty()) {
                content = doc.select(".entry");

                if (content.isEmpty()) {
                    return;
                }

            } else {
                org.jsoup.nodes.Element article = content.get(0);

                intro = article.select(".entry-top > .main-image-area > .img-wrapper");
                for (org.jsoup.nodes.Element img : intro.select("img")) {
                    img.attr("src", img.attr("lazy-src"));
                }
                content = article.select(".entry-content > .text");
            }
        } else {
            content.select(".related-article,.embedded-ad,.credit,script").remove();
        }
        for (org.jsoup.nodes.Element style : intro.select("[style]")) {
            style.attr("style", "");
        }
        for (org.jsoup.nodes.Element style : content.select("[style]")) {
            style.attr("style", "");
        }

        news.content = intro.outerHtml() + content.html();
    }

}