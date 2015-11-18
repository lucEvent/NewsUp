package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_ExpressenNewsReader extends BE_NewsReader {

    public BE_ExpressenNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Nyheter", "http://expressen.se/rss/nyheter"));
        SECTIONS.add(new BE_Section("VästSverige GT", "http://gt.se/rss/nyheter"));
        SECTIONS.add(new BE_Section("SydSverige KVP", "http://kvp.se/rss/nyheter"));

        SECTIONS.add(new BE_Section("Sport", "http://expressen.se/rss/sport"));
        SECTIONS.add(new BE_Section("Fotboll", "http://expressen.se/rss/fotboll"));
        SECTIONS.add(new BE_Section("Hockey", "http://expressen.se/rss/hockey"));

        SECTIONS.add(new BE_Section("Nöjesnyheter", "http://expressen.se/rss/noje"));
        SECTIONS.add(new BE_Section("Debatt", "http://expressen.se/rss/debatt"));
        SECTIONS.add(new BE_Section("Ledare", "http://expressen.se/rss/ledare"));
        SECTIONS.add(new BE_Section("Kultur", "http://expressen.se/rss/kultur"));
        SECTIONS.add(new BE_Section("Hälsa & Skönhet", "http://expressen.se/rss/halsa"));
        SECTIONS.add(new BE_Section("Leva & Bo", "http://expressen.se/rss/leva-och-bo"));
        SECTIONS.add(new BE_Section("Motor", "http://expressen.se/rss/motor"));
        SECTIONS.add(new BE_Section("Res", "http://expressen.se/rss/res"));
        SECTIONS.add(new BE_Section("Dokument", "http://expressen.se/rss/dokument"));
        SECTIONS.add(new BE_Section("Extra", "http://expressen.se/rss/extra"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
