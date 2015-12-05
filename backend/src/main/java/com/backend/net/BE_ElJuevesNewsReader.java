package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;
import java.net.URL;

public class BE_ElJuevesNewsReader extends BE_NewsReader {

    public BE_ElJuevesNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Principal", "http://www.eljueves.es/feeds/rss.html"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".post > p:not(.extra-info)");

        if (e.isEmpty()) {
            return;
        }
        for (org.jsoup.nodes.Element img : e.select("img")) {
            String src = img.attr("src");
            if (!src.startsWith("http:")) {
                img.attr("src", "http://www.eljueves.es" + src);
            }
        }

        news.content = e.outerHtml();

    }

    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            return org.jsoup.Jsoup.parse(new URL(pagelink).openStream(), "ISO-8859-1", pagelink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(pagelink);
    }

}