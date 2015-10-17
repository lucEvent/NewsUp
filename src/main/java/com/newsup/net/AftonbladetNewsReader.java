package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class AftonbladetNewsReader extends NewsReader {

    public AftonbladetNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Startsidan", 0, "http://www.aftonbladet.se/rss.xml"));
        SECTIONS.add(new Section("Senaste Nytt", 0, "http://www.aftonbladet.se/senastenytt/rss.xml"));
        SECTIONS.add(new Section("Nyheter", 0, "http://www.aftonbladet.se/nyheter/rss.xml"));
        SECTIONS.add(new Section("Sportbladet", 0, "http://www.aftonbladet.se/sportbladet/rss.xml"));
        SECTIONS.add(new Section("Fotboll", 1, "http://www.aftonbladet.se/sportbladet/fotboll/rss.xml"));
        SECTIONS.add(new Section("Hockey", 1, "http://www.aftonbladet.se/sportbladet/hockey/rss.xml"));
        SECTIONS.add(new Section("Nöjesbladet", 0, "http://www.aftonbladet.se/nojesbladet/rss.xml"));
        SECTIONS.add(new Section("Klick!", 0, "http://www.aftonbladet.se/nojesbladet/klick/rss.xml"));
        SECTIONS.add(new Section("Sofis Mode", 0, "http://www.aftonbladet.se/sofismode/rss.xml"));
        SECTIONS.add(new Section("Ledare", 0, "http://www.aftonbladet.se/ledare/rss.xml"));
        SECTIONS.add(new Section("Kultur", 0, "http://www.aftonbladet.se/kultur/rss.xml"));
        SECTIONS.add(new Section("Hälsa", 0, "http://www.aftonbladet.se/halsa/rss.xml"));
        SECTIONS.add(new Section("Wellness", 0, "http://www.aftonbladet.se/wellness/rss.xml"));
        SECTIONS.add(new Section("Bil", 0, "http://www.aftonbladet.se/bil/rss.xml"));
        SECTIONS.add(new Section("Min ekonomi", 0, "http://www.aftonbladet.se/minekonomi/rss.xml"));
        SECTIONS.add(new Section("Kolumnister", 0, "http://www.aftonbladet.se/nyheter/kolumnister/robertaschberg/rss.xml"));
        SECTIONS.add(new Section("Hjälp & Info", 0, "http://www.aftonbladet.se/hjalpinfo/rss.xml"));

    }


    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        return new News(title, link, "", date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element e = doc.select("article").get(0);

            org.jsoup.select.Elements ads = e.select(".abShareMenu,.abArticleBlockMedium,.abItem,.abBtn,h1,script,.abArticleTopImageBackground");
            ads.remove();

            news.content = e.html();

        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
            e.printStackTrace();
        }

        return news;
    }

}
