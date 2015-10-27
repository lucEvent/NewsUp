package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class SvDNewsReader extends NewsReader {

    public SvDNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Huvudnyheter", 0, "http://www.svd.se/?service=rss"));
        SECTIONS.add(new Section("Articles", 0, "http://www.svd.se/feed/articles.rss"));

    }


    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }


    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element e = doc.select(".Body").get(0);

            org.jsoup.select.Elements ads = e.select(".Body-ad");
            for (org.jsoup.nodes.Element ad : ads) {
                ad.remove();
            }
            e.select(".Body-pull").remove();

            news.content = e.html();

        } catch (Exception e) {
            debug("[ERROR] tit:" + news.title);
            e.printStackTrace();
        }

        return news;
    }

}
