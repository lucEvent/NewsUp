package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class MetroNewsReader extends NewsReader {

    public MetroNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Nyheter", 0, "http://www.metro.se/rss.xml?c=1292335191-0"));
        SECTIONS.add(new Section("Senaste nytt", 0, "http://www.metro.se/rss.xml"));
        SECTIONS.add(new Section("Toppnyheter", 0, "http://www.metro.se/rss.xml?c=1300413663-0"));

        SECTIONS.add(new Section("Sverige", 0, "http://www.metro.se/rss.xml?c=1292335191-25"));
        SECTIONS.add(new Section("Sverige-topp", 1, "http://www.metro.se/rss.xml?c=1292335191-29"));

        SECTIONS.add(new Section("Världen", 0, "http://www.metro.se/rss.xml?c=1292335191-13"));

        SECTIONS.add(new Section("Sport", 0, "http://www.metro.se/rss.xml?c=1292335191-1"));
        SECTIONS.add(new Section("Sport-topp", 1, "http://www.metro.se/rss.xml?c=1292335191-32"));

        SECTIONS.add(new Section("Nöje", 0, "http://www.metro.se/rss.xml?c=1292335191-5"));
        SECTIONS.add(new Section("Teknik", 0, "http://www.metro.se/rss.xml?c=1292335191-4"));
        SECTIONS.add(new Section("Viralgranskaren", 0, "http://www.metro.se/rss.xml?c=1394586029-0"));
        SECTIONS.add(new Section("Kolumner", 0, "http://www.metro.se/rss.xml?c=1292335191-14"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = news.description.replace("...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
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
