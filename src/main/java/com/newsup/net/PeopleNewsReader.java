package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class PeopleNewsReader extends NewsReader {

    public PeopleNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Latest News", 0, "http://rss.people.com/web/people/rss/topheadlines/index.xml"));
        SECTIONS.add(new Section("StyleWatch News", 0, "http://feeds.feedburner.com/people/stylewatch/offtherack"));
        SECTIONS.add(new Section("Pets", 0, "http://rss.people.com/web/people/rss/pets/index.xml"));
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".post-body");
        if (e.isEmpty()) {
            e = doc.select(".content");
            e.select("script,header,.social_article,#partner-content,.emote-wrap,#article-comments").remove();
        } else {
            e.select("script,div").remove();
        }

        news.content = e.html();
    }

}
