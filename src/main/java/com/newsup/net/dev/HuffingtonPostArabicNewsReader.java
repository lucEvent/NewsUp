package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.net.NewsReader;

public class HuffingtonPostArabicNewsReader extends NewsReader {

    public HuffingtonPostArabicNewsReader() {
        super();

        SECTIONS.add(new Section("عربي(Arabic)", 0, "http://www.huffpostarabi.com/feeds/verticals/arabi/news.xml"));
        SECTIONS.add(new Section("إعلام جديد", 1, "http://www.huffpostarabi.com/news/ilam-jdyd/feed/"));
        SECTIONS.add(new Section("ثقافة", 1, "http://www.huffpostarabi.com/news/ar-culture/feed/"));
        SECTIONS.add(new Section("تكنولوجيا", 1, "http://www.huffpostarabi.com/news/ar-technology/feed/"));
        SECTIONS.add(new Section("هو و هي", 1, "http://www.huffpostarabi.com/news/ar-relationships/feed/"));
        SECTIONS.add(new Section("مرأة", 1, "http://www.huffpostarabi.com/news/ar-eve/feed/"));
        SECTIONS.add(new Section("لايف ستايل", 1, "http://www.huffpostarabi.com/news/ar-lifestyle/feed/"));
        SECTIONS.add(new Section("فن", 1, "http://www.huffpostarabi.com/news/ar-entertainment/feed/"));
        SECTIONS.add(new Section("منوعات", 1, "http://www.huffpostarabi.com/news/mnwat/feed/"));
        SECTIONS.add(new Section("مدونات", 1, "http://www.huffpostarabi.com/feeds/verticals/arabi/blog.xml"));

    }

}
