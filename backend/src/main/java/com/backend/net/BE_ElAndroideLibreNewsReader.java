package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_ElAndroideLibreNewsReader extends BE_NewsReader {

    public BE_ElAndroideLibreNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Principal", "http://feeds.feedburner.com/elandroidelibre"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).getElementsByTag("p").text().replace("[...]", "");

        org.jsoup.select.Elements doc = org.jsoup.Jsoup.parseBodyFragment(content).getElementsByTag("body");
        doc.select("[clear=\"all\"] ~ *,br").remove();
        news.content = doc.html().replace("<br>", "");
        return news;
    }

}
