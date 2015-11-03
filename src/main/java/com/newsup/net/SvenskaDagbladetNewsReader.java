package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class SvenskaDagbladetNewsReader extends NewsReader {

    public SvenskaDagbladetNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Huvudnyheter", 0, "http://www.svd.se/?service=rss"));
        SECTIONS.add(new Section("Articles", 0, "http://www.svd.se/feed/articles.rss"));

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

        org.jsoup.select.Elements e = doc.select(".Deck,.Body");

        e.select(".Body-ad,.AdPositionData,.Body-pull,figcaption,.Quote,.ExternalLink").remove();

        news.content = e.html();
        return news;
    }

}
