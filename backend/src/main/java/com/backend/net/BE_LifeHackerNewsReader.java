package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_LifeHackerNewsReader extends BE_NewsReader {

    public BE_LifeHackerNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Main", "http://feeds.gawker.com/lifehacker/vip"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(news.description);
        doc.select("img").last().remove();
        doc.select("small,.core-inset").remove();

        news.content = doc.html();
        news.description = "";
        return news;
    }

}
