package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class TheAtlanticNewsReader extends NewsReader {

    public TheAtlanticNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("The Atlantic", 0, "http://feeds.feedburner.com/TheAtlantic"));
        SECTIONS.add(new Section("Politics", 1, "http://feeds.feedburner.com/AtlanticPoliticsChannel"));
        SECTIONS.add(new Section("Business", 1, "http://feeds.feedburner.com/AtlanticBusinessChannel"));
        SECTIONS.add(new Section("Culture", 1, "http://feeds.feedburner.com/AtlanticCulture"));
        SECTIONS.add(new Section("Global", 1, "http://feeds.feedburner.com/AtlanticCulture"));
        SECTIONS.add(new Section("Technology", 1, "http://feeds.feedburner.com/AtlanticScienceAndTechnology"));
        SECTIONS.add(new Section("U.S.", 1, "http://feeds.feedburner.com/AtlanticNational"));
        SECTIONS.add(new Section("Health", 1, "http://feeds.feedburner.com/AtlanticFood"));
        SECTIONS.add(new Section("Video", 1, "http://feeds.feedburner.com/AtlanticVideo"));
        SECTIONS.add(new Section("Education", 1, "http://feeds.feedburner.com/AtlanticEducationChannel"));
        SECTIONS.add(new Section("Science", 1, "http://feeds.feedburner.com/AtlanticScienceChannel"));
        SECTIONS.add(new Section("Photo", 1, "http://feeds.feedburner.com/theatlantic/infocus"));
        SECTIONS.add(new Section("Notes", 1, "http://feeds.feedburner.com/TheAtlanticNotes"));

        SECTIONS.add(new Section("The Wire", 0, "http://feeds.feedburner.com/TheAtlanticWire"));
        SECTIONS.add(new Section("CityLab", 0, "http://feeds.feedburner.com/TheAtlanticCities"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            doc.select("script").remove();

            org.jsoup.select.Elements e = doc.select("picture > img,.content");
            news.content = e.outerHtml();

        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }
}
