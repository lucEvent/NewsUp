package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_TEDNewsReader extends BE_NewsReader {

    public BE_TEDNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Main talks", "http://blog.ted.com/feed/"));
/*        SECTIONS.add(new BE_Section("Talks Videos", "https://www.ted.com/talks/rss"));
        SECTIONS.add(new BE_Section("Themes", null));
        SECTIONS.add(new BE_Section("The Creative Spark", "http://www.ted.com/themes/rss/id/1"));
        SECTIONS.add(new BE_Section("Animals That Amaze", "http://www.ted.com/themes/rss/id/2"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/3"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/4"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/5"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/6"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/7"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/8"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/9"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/10"));
        SECTIONS.add(new BE_Section("", "http://www.ted.com/themes/rss/id/"));
*/
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.clean(news.description, org.jsoup.safety.Whitelist.none());
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements ee = doc.select(".entry-content");
        ee.select(".meta").remove();

        news.content = ee.outerHtml();
    }

}

