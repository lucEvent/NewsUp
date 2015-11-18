package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_DogsterNewsReader extends BE_NewsReader {

    public BE_DogsterNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("News", "http://www.dogster.com/feed/"));
        SECTIONS.add(new BE_Section("The Scoop", "http://www.dogster.com/the-scoop/feed/"));
        SECTIONS.add(new BE_Section("Lifestyle", "http://www.dogster.com/lifestyle/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document description = org.jsoup.Jsoup.parse(news.description);
        news.description = description.select("p").get(0).text();

        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
        for (org.jsoup.nodes.Element e : doc.getElementsByAttribute("style")) {
            e.attr("style", "");
        }
        news.content = doc.html();
        return news;
    }

}