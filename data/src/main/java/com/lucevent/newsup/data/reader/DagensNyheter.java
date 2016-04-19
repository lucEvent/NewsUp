package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;


public class DagensNyheter extends com.lucevent.newsup.data.util.NewsReader {

    public DagensNyheter() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".article_preamble,.article_text");

        if (e.isEmpty()) {
            e = doc.select(".m-webtv-preview-container,.m-webtv-preamble");

            if (e.isEmpty()) {
                e = doc.select(".content > .excerpt,.content > p");

                if (e.isEmpty()) return;

            } else {
                org.jsoup.select.Elements imgs = doc.select("img");
                for (org.jsoup.nodes.Element img : imgs) {
                    String src = "http://www.dn.se" + img.attr("src");
                    img.attr("src", src);
                }
            }
        }
        news.content = e.outerHtml();
    }

}
