package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class Discover extends com.lucevent.newsup.data.util.NewsReader {

    public Discover() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".entry");
        if (e.isEmpty()) {
            e = doc.select(".segment");

            if (e.isEmpty()) {
                return;
            } else {
                org.jsoup.select.Elements imgs = e.select("img");

                for (org.jsoup.nodes.Element img : imgs) {
                    String src = img.attr("src");
                    String start = "http://discovermagazine.com";
                    if (!src.contains(start)) img.attr("src", start + src);
                }
            }
        } else {
            e.select(".navigation,h1,.meta,.shareIcons,blockquote,.categories,#disqus_thread,.fb-post").remove();
        }
        org.jsoup.select.Elements imgs = e.select("[style]");
        for (org.jsoup.nodes.Element img : imgs) {
            img.attr("style", "");
        }
        news.content = e.outerHtml();
    }

}