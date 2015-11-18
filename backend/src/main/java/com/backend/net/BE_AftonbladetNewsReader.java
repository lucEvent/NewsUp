package com.backend.net;

import com.backend.kernel.BE_Enclosure;
import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.util.ArrayList;

public class BE_AftonbladetNewsReader extends BE_NewsReader {

    public BE_AftonbladetNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Startsidan", "http://www.aftonbladet.se/rss.xml"));
        SECTIONS.add(new BE_Section("Senaste Nytt", "http://www.aftonbladet.se/senastenytt/rss.xml"));
        SECTIONS.add(new BE_Section("Nyheter", "http://www.aftonbladet.se/nyheter/rss.xml"));
        SECTIONS.add(new BE_Section("Sportbladet", "http://www.aftonbladet.se/sportbladet/rss.xml"));
        SECTIONS.add(new BE_Section("Fotboll", "http://www.aftonbladet.se/sportbladet/fotboll/rss.xml"));
        SECTIONS.add(new BE_Section("Hockey", "http://www.aftonbladet.se/sportbladet/hockey/rss.xml"));
        SECTIONS.add(new BE_Section("Nöjesbladet", "http://www.aftonbladet.se/nojesbladet/rss.xml"));
        SECTIONS.add(new BE_Section("Klick!", "http://www.aftonbladet.se/nojesbladet/klick/rss.xml"));
        SECTIONS.add(new BE_Section("Ledare", "http://www.aftonbladet.se/ledare/rss.xml"));
        SECTIONS.add(new BE_Section("Kultur", "http://www.aftonbladet.se/kultur/rss.xml"));
        SECTIONS.add(new BE_Section("Wellness", "http://www.aftonbladet.se/wellness/rss.xml"));
        SECTIONS.add(new BE_Section("Bil", "http://www.aftonbladet.se/bil/rss.xml"));
        SECTIONS.add(new BE_Section("Kolumnister", "http://www.aftonbladet.se/nyheter/kolumnister/rss.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        news.enclosures = new ArrayList<BE_Enclosure>();
        org.jsoup.select.Elements imgs = doc.select("img");
        if (!imgs.isEmpty()) {
            news.enclosures.add(new BE_Enclosure(imgs.get(0).attr("src"), "image/jpg", "0"));
        }

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".player__poster-image,.abLeadText,.abBodyText");
        if (!e.isEmpty()) {
            String img = "";
            if (!news.enclosures.isEmpty()) img = news.enclosures.get(0).html();

            news.content = img + e.html();
        }
    }

}
