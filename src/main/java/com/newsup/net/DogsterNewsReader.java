package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class DogsterNewsReader extends NewsReader {

    public DogsterNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("News", 0, "http://www.dogster.com/feed/"));
        SECTIONS.add(new Section("The Scoop", 0, "http://www.dogster.com/the-scoop/feed/"));
        SECTIONS.add(new Section("Lifestyle", 0, "http://www.dogster.com/lifestyle/feed/"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
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