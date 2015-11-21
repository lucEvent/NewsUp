package com.newsup.net.dev;

import com.newsup.net.NewsReaderDeprecated;

public class HuffingtonPostArabicNewsReader extends NewsReaderDeprecated {

    public HuffingtonPostArabicNewsReader() {
        super();

        SECTIONS.add(new SectionDeprecated("عربي(Arabic)", 0, "http://www.huffpostarabi.com/feeds/verticals/arabi/news.xml"));
        SECTIONS.add(new SectionDeprecated("إعلام جديد", 1, "http://www.huffpostarabi.com/news/ilam-jdyd/feed/"));
        SECTIONS.add(new SectionDeprecated("ثقافة", 1, "http://www.huffpostarabi.com/news/ar-culture/feed/"));
        SECTIONS.add(new SectionDeprecated("تكنولوجيا", 1, "http://www.huffpostarabi.com/news/ar-technology/feed/"));
        SECTIONS.add(new SectionDeprecated("هو و هي", 1, "http://www.huffpostarabi.com/news/ar-relationships/feed/"));
        SECTIONS.add(new SectionDeprecated("مرأة", 1, "http://www.huffpostarabi.com/news/ar-eve/feed/"));
        SECTIONS.add(new SectionDeprecated("لايف ستايل", 1, "http://www.huffpostarabi.com/news/ar-lifestyle/feed/"));
        SECTIONS.add(new SectionDeprecated("فن", 1, "http://www.huffpostarabi.com/news/ar-entertainment/feed/"));
        SECTIONS.add(new SectionDeprecated("منوعات", 1, "http://www.huffpostarabi.com/news/mnwat/feed/"));
        SECTIONS.add(new SectionDeprecated("مدونات", 1, "http://www.huffpostarabi.com/feeds/verticals/arabi/blog.xml"));

    }

}
