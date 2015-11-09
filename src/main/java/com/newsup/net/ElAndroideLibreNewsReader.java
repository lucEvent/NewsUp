package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.Jsoup;

public class ElAndroideLibreNewsReader extends NewsReader {

    public ElAndroideLibreNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Principal", 0, "http://feeds.feedburner.com/elandroidelibre"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = Jsoup.parse(news.description).getElementsByTag("p").text().replace("[...]", "");

        org.jsoup.select.Elements doc = org.jsoup.Jsoup.parseBodyFragment(content).getElementsByTag("body");
        doc.select("[clear=\"all\"] ~ *,br").remove();
        news.content = doc.html().replace("<br>", "");
        return news;
    }

}
