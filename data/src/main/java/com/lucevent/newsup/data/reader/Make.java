package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import java.io.IOException;

public class Make extends com.lucevent.newsup.data.util.NewsReader {

    public Make() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).select("p:nth-of-type(1)").text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("article");

        if (e.isEmpty()) {
            e = doc.select(".hentry > .row > .span8");

            if (e.isEmpty()) {
                return;
            }

        } else {
            e.select(".related-topics,.row-fluid,.ctx-clearfix,.ctx-sidebar-container,hr,#ctx-sl-subscribe,#ctx-module,#pubexchange_below_content").remove();
        }

        for (org.jsoup.nodes.Element ns : e.select("noscript")) {
            ns.tagName("p");
        }
        for (org.jsoup.nodes.Element style : e.select("[style~=width]")) {
            style.attr("style", "");
        }

        news.content = e.html();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            String ua = "Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36";
            return org.jsoup.Jsoup.connect(pagelink).userAgent(ua).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(pagelink);
    }

}
