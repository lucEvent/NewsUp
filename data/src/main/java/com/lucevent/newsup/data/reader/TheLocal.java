package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class TheLocal extends com.lucevent.newsup.data.util.NewsReader {

    public TheLocal() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.date += (-2 * 3600000);
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements content = doc.select("#main_picture_article > img,.articleTeaser,.articleContent");

        org.jsoup.select.Elements imgs = content.select("img");
        for (org.jsoup.nodes.Element img : imgs) {
            String src = "http://www.thelocal.com" + img.attr("src");
            img.attr("src", src);
            img.attr("style", "");
        }

        news.content = content.outerHtml();
    }

}
