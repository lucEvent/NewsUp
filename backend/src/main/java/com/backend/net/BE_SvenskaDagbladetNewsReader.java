package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_SvenskaDagbladetNewsReader extends BE_NewsReader {

    public BE_SvenskaDagbladetNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Huvudnyheter", "http://www.svd.se/?service=rss"));
        SECTIONS.add(new BE_Section("Articles", "http://www.svd.se/feed/articles.rss"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".Deck,.Body");

        e.select(".Body-ad,.AdPositionData,.Body-pull,figcaption,.Quote,.ExternalLink").remove();

        news.content = e.html();
    }

}
