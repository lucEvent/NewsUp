package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class LifeHackerNewsReader extends NewsReader {

    public LifeHackerNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main", 0, "http://feeds.gawker.com/lifehacker/vip"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(news.description);
        doc.select("img").last().remove();
        news.content = doc.html();
        news.description = "";
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}
