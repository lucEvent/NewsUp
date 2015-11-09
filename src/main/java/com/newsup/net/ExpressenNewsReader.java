package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class ExpressenNewsReader extends NewsReader {

    public ExpressenNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Nyheter", 0, "http://expressen.se/rss/nyheter"));
        SECTIONS.add(new Section("VästSverige GT", 0, "http://gt.se/rss/nyheter"));
        SECTIONS.add(new Section("SydSverige KVP", 0, "http://kvp.se/rss/nyheter"));

        SECTIONS.add(new Section("Sport", 0, "http://expressen.se/rss/sport"));
        SECTIONS.add(new Section("Fotboll", 1, "http://expressen.se/rss/fotboll"));
        SECTIONS.add(new Section("Hockey", 1, "http://expressen.se/rss/hockey"));

        SECTIONS.add(new Section("Nöjesnyheter", 0, "http://expressen.se/rss/noje"));
        SECTIONS.add(new Section("Debatt", 0, "http://expressen.se/rss/debatt"));
        SECTIONS.add(new Section("Ledare", 0, "http://expressen.se/rss/ledare"));
        SECTIONS.add(new Section("Kultur", 0, "http://expressen.se/rss/kultur"));
        SECTIONS.add(new Section("Hälsa & Skönhet", 0, "http://expressen.se/rss/halsa"));
        SECTIONS.add(new Section("Leva & Bo", 0, "http://expressen.se/rss/leva-och-bo"));
        SECTIONS.add(new Section("Motor", 0, "http://expressen.se/rss/motor"));
        SECTIONS.add(new Section("Res", 0, "http://expressen.se/rss/res"));
        SECTIONS.add(new Section("Dokument", 0, "http://expressen.se/rss/dokument"));
        SECTIONS.add(new Section("Extra", 0, "http://expressen.se/rss/extra"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        String img = "";
        org.jsoup.select.Elements media = doc.select(".b-article__media,.b-slideshow__slider").select("img");
        for (org.jsoup.nodes.Element i : media) {
            String src = i.attr("template-src");
            img += "<img src=\"http:" + src + "\">";
        }
        org.jsoup.select.Elements content = doc.select(".b-text_article-body");

        if (content.isEmpty()) {
            content = doc.select(".b-text_article");

            if (content.isEmpty()) {
                content = doc.select(".b-text");

                if (content.isEmpty()) {
                    content = doc.select(".text--article-preamble,.text--article-body");

                    if (content.isEmpty() && img.isEmpty()) {
                        return;
                    }
                }
            }
        }
        news.content = img + content;
    }

}
