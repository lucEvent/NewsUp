package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class PeopleNewsReader extends NewsReader {

    public PeopleNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Latest News", 0, "http://rss.people.com/web/people/rss/topheadlines/index.xml"));
        SECTIONS.add(new Section("StyleWatch News", 0, "http://feeds.feedburner.com/people/stylewatch/offtherack"));
        SECTIONS.add(new Section("Pets", 0, "http://rss.people.com/web/people/rss/pets/index.xml"));
    }

    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = org.jsoup.Jsoup.parse(description).text();
        return new News(title, link, description, date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.select.Elements e = doc.select(".post-body");
            if (e.isEmpty()) {
                e = doc.select(".content");
                e.select("script,header,.social_article,#partner-content,.emote-wrap,#article-comments").remove();
            } else {
                e.select("script,div").remove();
            }

            news.content = e.html();
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
