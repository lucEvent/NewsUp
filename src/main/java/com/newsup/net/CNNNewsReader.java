package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class CNNNewsReader extends NewsReader {

    public CNNNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Top Stories", 0, "http://rss.cnn.com/rss/edition.rss"));
        SECTIONS.add(new Section("Most Recent", 0, "http://rss.cnn.com/rss/cnn_latest.rss"));
        SECTIONS.add(new Section("World", 0, "http://rss.cnn.com/rss/edition_world.rss"));
        SECTIONS.add(new Section("Africa", 1, "http://rss.cnn.com/rss/edition_africa.rss"));
        SECTIONS.add(new Section("Americas", 1, "http://rss.cnn.com/rss/edition_americas.rss"));
        SECTIONS.add(new Section("Asia", 1, "http://rss.cnn.com/rss/edition_asia.rss"));
        SECTIONS.add(new Section("Europe", 1, "http://rss.cnn.com/rss/edition_europe.rss"));
        SECTIONS.add(new Section("Middle East", 1, "http://rss.cnn.com/rss/edition_meast.rss"));
        SECTIONS.add(new Section("U.S.", 1, "http://rss.cnn.com/rss/edition_us.rss"));

        SECTIONS.add(new Section("Money", 0, "http://rss.cnn.com/rss/money_news_international.rss"));
        SECTIONS.add(new Section("Technology", 0, "http://rss.cnn.com/rss/edition_technology.rss"));
        SECTIONS.add(new Section("Science & Space", 0, "http://rss.cnn.com/rss/edition_space.rss"));
        SECTIONS.add(new Section("Entertainment", 0, "http://rss.cnn.com/rss/edition_entertainment.rss"));
        SECTIONS.add(new Section("Politics", 0, "http://rss.cnn.com/rss/cnn_allpolitics.rss"));
        SECTIONS.add(new Section("Health", 0, "http://rss.cnn.com/rss/cnn_health.rss"));
        SECTIONS.add(new Section("Travel", 0, "http://rss.cnn.com/rss/edition_travel.rss"));
        SECTIONS.add(new Section("Living", 0, "http://rss.cnn.com/rss/cnn_living.rss"));
        SECTIONS.add(new Section("Markets", 0, "http://rss.cnn.com/rss/money_markets.rss"));

        SECTIONS.add(new Section("World Sport", 0, "http://rss.cnn.com/rss/edition_sport.rss"));
        SECTIONS.add(new Section("Football", 1, "http://rss.cnn.com/rss/edition_football.rss"));
        SECTIONS.add(new Section("Golf", 1, "http://rss.cnn.com/rss/edition_golf.rss"));
        SECTIONS.add(new Section("Motorsport", 1, "http://rss.cnn.com/rss/edition_motorsport.rss"));
        SECTIONS.add(new Section("Tennis", 1, "http://rss.cnn.com/rss/edition_tennis.rss"));

        SECTIONS.add(new Section("Video", 0, "http://rss.cnn.com/rss/cnn_freevideo.rss"));

        SECTIONS.add(new Section("Student News", 0, "http://rss.cnn.com/services/podcasting/studentnews/rss.xml"));
        SECTIONS.add(new Section("iReports on CNN", 0, "http://rss.ireport.com/feeds/oncnn.rss"));
        SECTIONS.add(new Section("The Buzz", 0, "http://rss.cnn.com/cnnmoneymorningbuzz"));
        SECTIONS.add(new Section("Small Business", 0, "http://rss.cnn.com/rss/money_smbusiness.rss"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {

        news.content = doc.select(".zn,.zn-body-text,.zn-body").get(0).html();

    }

}
