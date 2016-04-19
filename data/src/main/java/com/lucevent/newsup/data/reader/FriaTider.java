package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class FriaTider extends com.lucevent.newsup.data.util.NewsReader {

    public FriaTider() {
        super();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".field-items,.standfirst");
        e.select(".image-credit").remove();

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}
