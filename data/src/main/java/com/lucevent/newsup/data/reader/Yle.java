package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

public class Yle extends com.lucevent.newsup.data.util.NewsReader {

    public Yle() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty()) {
            if (org.jsoup.Jsoup.parseBodyFragment(content).text().length() != 0) {

                String img = "";
                for (Enclosure e : news.enclosures) {
                    img += e.html();
                }
                news.content = "<meta charset=\"UTF-8\">" + img + content;
            }
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".text");

        if (!e.isEmpty())
            news.content = e.html();
    }

}