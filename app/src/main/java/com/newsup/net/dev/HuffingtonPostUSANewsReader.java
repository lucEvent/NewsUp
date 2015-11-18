package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class HuffingtonPostUSANewsReader extends NewsReader {

    public HuffingtonPostUSANewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Main", 0, "http://www.huffingtonpost.com/feeds/index.xml"));
        SECTIONS.add(new SectionDeprecated("Arts & Culture", 0, "http://www.huffingtonpost.com/feeds/verticals/arts/index.xml"));
        SECTIONS.add(new SectionDeprecated("Black Voices", 0, "http://www.huffingtonpost.com/feeds/verticals/black-voices/index.xml"));
        SECTIONS.add(new SectionDeprecated("Books", 0, "http://www.huffingtonpost.com/feeds/verticals/books/index.xml"));
        SECTIONS.add(new SectionDeprecated("Business", 0, "http://www.huffingtonpost.com/feeds/verticals/business/index.xml"));
        SECTIONS.add(new SectionDeprecated("Celebrity", 0, "http://www.huffingtonpost.com/feeds/verticals/celebrity/index.xml"));
        SECTIONS.add(new SectionDeprecated("College", 0, "http://www.huffingtonpost.com/feeds/verticals/college/index.xml"));
        SECTIONS.add(new SectionDeprecated("Comedy", 0, "http://www.huffingtonpost.com/feeds/verticals/comedy/index.xml"));
        SECTIONS.add(new SectionDeprecated("Crime", 0, "http://www.huffingtonpost.com/feeds/verticals/crime/index.xml"));
        SECTIONS.add(new SectionDeprecated("Education", 0, "http://www.huffingtonpost.com/feeds/verticals/education/index.xml"));
        SECTIONS.add(new SectionDeprecated("Entertainment", 0, "http://www.huffingtonpost.com/feeds/verticals/entertainment/index.xml"));
        SECTIONS.add(new SectionDeprecated("Good News", 0, "http://www.huffingtonpost.com/feeds/verticals/good-news/index.xml"));
        SECTIONS.add(new SectionDeprecated("Green", 0, "http://www.huffingtonpost.com/feeds/verticals/green/index.xml"));
        SECTIONS.add(new SectionDeprecated("HPSN", 0, "http://www.huffingtonpost.com/feeds/verticals/hpsn/index.xml"));
        SECTIONS.add(new SectionDeprecated("Health News", 0, "http://www.huffingtonpost.com/feeds/verticals/health-news/index.xml"));
        SECTIONS.add(new SectionDeprecated("Health and Fitness", 0, "http://www.huffingtonpost.com/feeds/verticals/health-fitness/index.xml"));
        SECTIONS.add(new SectionDeprecated("Healthy Living", 0, "http://www.huffingtonpost.com/feeds/verticals/healthy-living/index.xml"));
        SECTIONS.add(new SectionDeprecated("Home", 0, "http://www.huffingtonpost.com/feeds/verticals/huffpost-home/index.xml"));
        SECTIONS.add(new SectionDeprecated("Huffington", 0, "http://www.huffingtonpost.com/feeds/verticals/huffington/index.xml"));
        SECTIONS.add(new SectionDeprecated("Huffpost Code", 0, "http://www.huffingtonpost.com/feeds/verticals/huffpost-code/index.xml"));
        SECTIONS.add(new SectionDeprecated("Impact", 0, "http://www.huffingtonpost.com/feeds/verticals/impact/index.xml"));
        SECTIONS.add(new SectionDeprecated("OWN", 0, "http://www.huffingtonpost.com/feeds/verticals/own/index.xml"));
        SECTIONS.add(new SectionDeprecated("Parents", 0, "http://www.huffingtonpost.com/feeds/verticals/parents/index.xml"));
        SECTIONS.add(new SectionDeprecated("Politics", 0, "http://www.huffingtonpost.com/feeds/verticals/politics/index.xml"));
        SECTIONS.add(new SectionDeprecated("Religion", 0, "http://www.huffingtonpost.com/feeds/verticals/religion/index.xml"));
        SECTIONS.add(new SectionDeprecated("Science", 0, "http://www.huffingtonpost.com/feeds/verticals/science/index.xml"));
        SECTIONS.add(new SectionDeprecated("Small Business", 0, "http://www.huffingtonpost.com/feeds/verticals/small-business/index.xml"));
        SECTIONS.add(new SectionDeprecated("Sports", 0, "http://www.huffingtonpost.com/feeds/verticals/sports/index.xml"));
        SECTIONS.add(new SectionDeprecated("Style", 0, "http://www.huffingtonpost.com/feeds/verticals/style/index.xml"));
        SECTIONS.add(new SectionDeprecated("TED Weekends", 0, "http://www.huffingtonpost.com/feeds/verticals/tedweekends/index.xml"));
        SECTIONS.add(new SectionDeprecated("TV", 0, "http://www.huffingtonpost.com/feeds/verticals/tv/index.xml"));
        SECTIONS.add(new SectionDeprecated("Taste", 0, "http://www.huffingtonpost.com/feeds/verticals/taste/index.xml"));
        SECTIONS.add(new SectionDeprecated("Technology", 0, "http://www.huffingtonpost.com/feeds/verticals/technology/index.xml"));
        SECTIONS.add(new SectionDeprecated("Teen", 0, "http://www.huffingtonpost.com/feeds/verticals/teen/index.xml"));
        SECTIONS.add(new SectionDeprecated("Travel", 0, "http://www.huffingtonpost.com/feeds/verticals/travel/index.xml"));
        SECTIONS.add(new SectionDeprecated("Weird News", 0, "http://www.huffingtonpost.com/feeds/verticals/weird-news/index.xml"));
        SECTIONS.add(new SectionDeprecated("Women", 0, "http://www.huffingtonpost.com/feeds/verticals/women/index.xml"));
        SECTIONS.add(new SectionDeprecated("World Post", 0, "http://www.huffingtonpost.com/feeds/verticals/world/index.xml"));

    }

}
