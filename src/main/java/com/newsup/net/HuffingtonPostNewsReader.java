package com.newsup.net;


import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class HuffingtonPostNewsReader extends NewsReader {

    public HuffingtonPostNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main", 0, "http://www.huffingtonpost.com/feeds/index.xml"));
        SECTIONS.add(new Section("España", 0, "http://www.huffingtonpost.es/feeds/verticals/spain/index.xml"));
        SECTIONS.add(new Section("Arts & Culture", 0, "http://www.huffingtonpost.com/feeds/verticals/arts/index.xml"));
        SECTIONS.add(new Section("Black Voices", 0, "http://www.huffingtonpost.com/feeds/verticals/black-voices/index.xml"));
        SECTIONS.add(new Section("Books", 0, "http://www.huffingtonpost.com/feeds/verticals/books/index.xml"));
        SECTIONS.add(new Section("Business", 0, "http://www.huffingtonpost.com/feeds/verticals/business/index.xml"));
        SECTIONS.add(new Section("Celebrity", 0, "http://www.huffingtonpost.com/feeds/verticals/celebrity/index.xml"));
        SECTIONS.add(new Section("College", 0, "http://www.huffingtonpost.com/feeds/verticals/college/index.xml"));
        SECTIONS.add(new Section("Comedy", 0, "http://www.huffingtonpost.com/feeds/verticals/comedy/index.xml"));
        SECTIONS.add(new Section("Crime", 0, "http://www.huffingtonpost.com/feeds/verticals/crime/index.xml"));
        SECTIONS.add(new Section("Education", 0, "http://www.huffingtonpost.com/feeds/verticals/education/index.xml"));
        SECTIONS.add(new Section("Entertainment", 0, "http://www.huffingtonpost.com/feeds/verticals/entertainment/index.xml"));
        SECTIONS.add(new Section("Good News", 0, "http://www.huffingtonpost.com/feeds/verticals/good-news/index.xml"));
        SECTIONS.add(new Section("Green", 0, "http://www.huffingtonpost.com/feeds/verticals/green/index.xml"));
        SECTIONS.add(new Section("HPSN", 0, "http://www.huffingtonpost.com/feeds/verticals/hpsn/index.xml"));
        SECTIONS.add(new Section("Health News", 0, "http://www.huffingtonpost.com/feeds/verticals/health-news/index.xml"));
        SECTIONS.add(new Section("Health and Fitness", 0, "http://www.huffingtonpost.com/feeds/verticals/health-fitness/index.xml"));
        SECTIONS.add(new Section("Healthy Living", 0, "http://www.huffingtonpost.com/feeds/verticals/healthy-living/index.xml"));
        SECTIONS.add(new Section("Home", 0, "http://www.huffingtonpost.com/feeds/verticals/huffpost-home/index.xml"));
        SECTIONS.add(new Section("Huffington", 0, "http://www.huffingtonpost.com/feeds/verticals/huffington/index.xml"));
        SECTIONS.add(new Section("Huffpost Code", 0, "http://www.huffingtonpost.com/feeds/verticals/huffpost-code/index.xml"));
        SECTIONS.add(new Section("Impact", 0, "http://www.huffingtonpost.com/feeds/verticals/impact/index.xml"));
        SECTIONS.add(new Section("OWN", 0, "http://www.huffingtonpost.com/feeds/verticals/own/index.xml"));
        SECTIONS.add(new Section("Parents", 0, "http://www.huffingtonpost.com/feeds/verticals/parents/index.xml"));
        SECTIONS.add(new Section("Politics", 0, "http://www.huffingtonpost.com/feeds/verticals/politics/index.xml"));
        SECTIONS.add(new Section("Religion", 0, "http://www.huffingtonpost.com/feeds/verticals/religion/index.xml"));
        SECTIONS.add(new Section("Science", 0, "http://www.huffingtonpost.com/feeds/verticals/science/index.xml"));
        SECTIONS.add(new Section("Small Business", 0, "http://www.huffingtonpost.com/feeds/verticals/small-business/index.xml"));
        SECTIONS.add(new Section("Sports", 0, "http://www.huffingtonpost.com/feeds/verticals/sports/index.xml"));
        SECTIONS.add(new Section("Style", 0, "http://www.huffingtonpost.com/feeds/verticals/style/index.xml"));
        SECTIONS.add(new Section("TED Weekends", 0, "http://www.huffingtonpost.com/feeds/verticals/tedweekends/index.xml"));
        SECTIONS.add(new Section("TV", 0, "http://www.huffingtonpost.com/feeds/verticals/tv/index.xml"));
        SECTIONS.add(new Section("Taste", 0, "http://www.huffingtonpost.com/feeds/verticals/taste/index.xml"));
        SECTIONS.add(new Section("Technology", 0, "http://www.huffingtonpost.com/feeds/verticals/technology/index.xml"));
        SECTIONS.add(new Section("Teen", 0, "http://www.huffingtonpost.com/feeds/verticals/teen/index.xml"));
        SECTIONS.add(new Section("Travel", 0, "http://www.huffingtonpost.com/feeds/verticals/travel/index.xml"));
        SECTIONS.add(new Section("Weird News", 0, "http://www.huffingtonpost.com/feeds/verticals/weird-news/index.xml"));
        SECTIONS.add(new Section("Women", 0, "http://www.huffingtonpost.com/feeds/verticals/women/index.xml"));
        SECTIONS.add(new Section("World Post", 0, "http://www.huffingtonpost.com/feeds/verticals/world/index.xml"));

    }

    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        News res = new News(title, link, "", date, categories);
        res.content = Jsoup.clean(description, Whitelist.basic());
        return res;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

    @Override
    protected void debug(String text) {
        android.util.Log.d("##HuffingtonPostNR##", text);
    }

}
