package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.util.Enclosure;

import java.util.ArrayList;

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
        SECTIONS.add(new Section("Ledare", 0, "http://www.aftonbladet.se/ledare/rss.xml"));
        SECTIONS.add(new Section("Kultur", 0, "http://www.aftonbladet.se/kultur/rss.xml"));
        SECTIONS.add(new Section("Wellness", 0, "http://www.aftonbladet.se/wellness/rss.xml"));
        SECTIONS.add(new Section("Bil", 0, "http://www.aftonbladet.se/bil/rss.xml"));
        SECTIONS.add(new Section("Kolumnister", 0, "http://www.aftonbladet.se/nyheter/kolumnister/rss.xml"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        news.enclosures = new ArrayList<Enclosure>();
        org.jsoup.select.Elements imgs = doc.select("img");
        if (!imgs.isEmpty()) {
            news.enclosures.add(new Enclosure(imgs.get(0).attr("src"), "image/jpg", "0"));
        }

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".player__poster-image,.abLeadText,.abBodyText");
        if (!e.isEmpty()) {
            String img = "";
            if (!news.enclosures.isEmpty()) img = news.enclosures.get(0).html();

            news.content = img + e.html();
        }
    }

}
