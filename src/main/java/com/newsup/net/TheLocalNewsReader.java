package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class TheLocalNewsReader extends NewsReader {

    public TheLocalNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Austria", 0, "http://www.thelocal.at/feeds/rss.php"));
        SECTIONS.add(new Section("Denmark", 0, "http://www.thelocal.dk/feeds/rss.php"));
        SECTIONS.add(new Section("France", 0, "http://www.thelocal.fr/feeds/rss.php"));
        SECTIONS.add(new Section("Germany", 0, "http://www.thelocal.de/feeds/rss.php"));
        SECTIONS.add(new Section("Italy", 0, "http://www.thelocal.it/feeds/rss.php"));
        SECTIONS.add(new Section("Norway", 0, "http://www.thelocal.no/feeds/rss.php"));
        SECTIONS.add(new Section("Spain", 0, "http://www.thelocal.es/feeds/rss.php"));
        SECTIONS.add(new Section("Sweden", 0, "http://www.thelocal.se/feeds/rss.php"));
        SECTIONS.add(new Section("Switzerland", 0, "http://www.thelocal.ch/feeds/rss.php"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.date += (-2 * 3600000);
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;
            org.jsoup.nodes.Element element = doc.select("article").get(0);

            StringBuilder content = new StringBuilder();
            org.jsoup.nodes.Element elems = element.select(".articleTeaser").get(0);
            do {
                content.append(elems.outerHtml());
                elems = elems.nextElementSibling();
            } while (elems != null);

            news.content = content.toString();

        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

}
