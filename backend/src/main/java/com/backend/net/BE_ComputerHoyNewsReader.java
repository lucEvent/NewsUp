package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_ComputerHoyNewsReader extends BE_NewsReader {

    public BE_ComputerHoyNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Principal", "http://computerhoy.com/rss.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements img = doc.select("[itemprop=\"image\"]");
        org.jsoup.select.Elements e = doc.select("#ab_stickyid");

        if (e.isEmpty()) {
            e = doc.select("article > p");

            if (e.isEmpty()) {
                return;
            }
        } else {
            e.select(".adcuadrado,blockquote").remove();
        }

        for (org.jsoup.nodes.Element iframe : e.select("iframe")) {
            String src = iframe.attr("src");
            if (!src.startsWith("http:")) {
                iframe.attr("src", "http:" + src);
            }
        }
        for (org.jsoup.nodes.Element style : e.select("[style]")) {
            style.removeAttr("style");
        }

        news.content = img.outerHtml() + e.html();

    }

}