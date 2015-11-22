package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_TEDNewsReader extends BE_NewsReader {

    public BE_TEDNewsReader() {
        super(true);

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Main talks", "http://blog.ted.com/feed/"));
        SECTIONS.add(new BE_Section("Talks Videos", "https://www.ted.com/talks/rss"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        if (!content.isEmpty()) {
            org.jsoup.nodes.Element edoc = org.jsoup.Jsoup.parse(content).body();

            for (org.jsoup.nodes.Element e : edoc.select("[style]")) {
                e.removeAttr("style");
            }

            edoc.select("[rel=\"nofollow\"] ~ *,[rel=\"nofollow\"]").remove();
            news.content = edoc.html();

        } else if (!news.enclosures.isEmpty()) {
            news.content = news.enclosures.get(0).html() + "<p>" + news.description + "</p>";
        }
        return news;
    }

}

