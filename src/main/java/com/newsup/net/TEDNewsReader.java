package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class TEDNewsReader extends NewsReader {

    public TEDNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main talks", 0, "http://blog.ted.com/feed/"));
/*        SECTIONS.add(new Section("Talks Videos", 0, "https://www.ted.com/talks/rss"));
        SECTIONS.add(new Section("Themes", 0, null));
        SECTIONS.add(new Section("The Creative Spark", 1, "http://www.ted.com/themes/rss/id/1"));
        SECTIONS.add(new Section("Animals That Amaze", 1, "http://www.ted.com/themes/rss/id/2"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/3"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/4"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/5"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/6"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/7"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/8"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/9"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/10"));
        SECTIONS.add(new Section("", 1, "http://www.ted.com/themes/rss/id/"));
*/

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.clean(news.description, org.jsoup.safety.Whitelist.none());
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.select.Elements ee = doc.select(".entry-content");
            ee.select(".meta").remove();

            news.content = ee.outerHtml();
        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
        }
        return news;
    }

}

