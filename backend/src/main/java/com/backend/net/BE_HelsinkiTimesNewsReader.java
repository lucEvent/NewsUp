package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_HelsinkiTimesNewsReader extends BE_NewsReader {

    public BE_HelsinkiTimesNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("News", "http://www.helsinkitimes.fi/?format=feed&type=rss"));
        SECTIONS.add(new BE_Section("Domestic", "http://www.helsinkitimes.fi/finland/finland-news/domestic.feed?type=rss"));
        SECTIONS.add(new BE_Section("Politics", "http://www.helsinkitimes.fi/finland/finland-news/politics.feed?type=rss"));
        SECTIONS.add(new BE_Section("Business", "http://www.helsinkitimes.fi/business.feed?type=rss"));
        SECTIONS.add(new BE_Section("Columns", "http://www.helsinkitimes.fi/columns.feed?type=rss"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".item-page > p,.item-page .thumbnail img");

        if (!e.isEmpty()) {
            org.jsoup.select.Elements imgs = e.select("img");
            for (org.jsoup.nodes.Element img : imgs) {
                img.attr("src", "http://www.helsinkitimes.fi" + img.attr("src"));
            }

            news.content = e.outerHtml();
        }
    }

}