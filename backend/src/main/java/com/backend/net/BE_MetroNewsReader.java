package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;

public class BE_MetroNewsReader extends BE_NewsReader {

    public BE_MetroNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Nyheter", "http://www.metro.se/rss.xml?c=1292335191-0"));
        SECTIONS.add(new BE_Section("Senaste nytt", "http://www.metro.se/rss.xml"));
        SECTIONS.add(new BE_Section("Toppnyheter", "http://www.metro.se/rss.xml?c=1300413663-0"));

        SECTIONS.add(new BE_Section("Sverige", "http://www.metro.se/rss.xml?c=1292335191-25"));
        SECTIONS.add(new BE_Section("Sverige-topp", "http://www.metro.se/rss.xml?c=1292335191-29"));

        SECTIONS.add(new BE_Section("Världen", "http://www.metro.se/rss.xml?c=1292335191-13"));

        SECTIONS.add(new BE_Section("Sport", "http://www.metro.se/rss.xml?c=1292335191-1"));
        SECTIONS.add(new BE_Section("Sport-topp", "http://www.metro.se/rss.xml?c=1292335191-32"));

        SECTIONS.add(new BE_Section("Nöje", "http://www.metro.se/rss.xml?c=1292335191-5"));
        SECTIONS.add(new BE_Section("Teknik", "http://www.metro.se/rss.xml?c=1292335191-4"));
        SECTIONS.add(new BE_Section("Viralgranskaren", "http://www.metro.se/rss.xml?c=1394586029-0"));
        SECTIONS.add(new BE_Section("Kolumner", "http://www.metro.se/rss.xml?c=1292335191-14"));

    }

    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (IOException e) {
            debug("[" + e.getClass().getSimpleName() + "] Reintentando");
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).timeout(10000).get();
        } catch (IOException e) {
            debug("[" + e.getClass().getSimpleName() + "] No se ha podido leer: " + pagelink);
        }
        return null;
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = news.description.replace("...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        doc.select("script").remove();

        org.jsoup.select.Elements imgs = doc.select(".image-component > img");
        if (!imgs.isEmpty()) {
            org.jsoup.nodes.Element img = imgs.get(0);
            String src = img.attr("src");
            if (src != null && !src.isEmpty()) {
                img.attr("src", "http://www.metro.se/" + src);
            }
            news.content = img.outerHtml();
        }
        org.jsoup.select.Elements e = doc.select(".article-body");
        for (org.jsoup.nodes.Element img : e.select("img")) {
            img.attr("src", "http://www.metro.se" + img.attr("src"));
        }

        news.content += e.html();
    }

}
