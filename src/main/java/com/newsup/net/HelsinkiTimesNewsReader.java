package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class HelsinkiTimesNewsReader extends NewsReader {

    public HelsinkiTimesNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("News", 0, "http://www.helsinkitimes.fi/?format=feed&type=rss"));
        SECTIONS.add(new Section("Domestic", 1, "http://www.helsinkitimes.fi/finland/finland-news/domestic.feed?type=rss"));
        SECTIONS.add(new Section("Politics", 1, "http://www.helsinkitimes.fi/finland/finland-news/politics.feed?type=rss"));
        SECTIONS.add(new Section("Business", 0, "http://www.helsinkitimes.fi/business.feed?type=rss"));
        SECTIONS.add(new Section("Columns", 0, "http://www.helsinkitimes.fi/columns.feed?type=rss"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc == null) return news;

        org.jsoup.select.Elements e = doc.select(".item-page > p,.item-page .thumbnail img");

        if (e.isEmpty()) {
            debug("NO SE HA PODIDO LEER EL CONTENIDO: " + news.link);
            return news;
        } else {
            org.jsoup.select.Elements imgs = e.select("img");
            for (org.jsoup.nodes.Element img : imgs) {
                img.attr("src", "http://www.helsinkitimes.fi" + img.attr("src"));
            }
        }
        news.content = e.outerHtml();
        return news;
    }

}