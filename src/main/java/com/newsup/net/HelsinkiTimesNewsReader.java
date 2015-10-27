package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class HelsinkiTimesNewsReader extends NewsReader {

    public HelsinkiTimesNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main news", 0, "http://www.helsinkitimes.fi/?format=feed&type=rss"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            news.content = doc.select(".item-page > p").outerHtml();

        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
        }
        return news;
    }

}