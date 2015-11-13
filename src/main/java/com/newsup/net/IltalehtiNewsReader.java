package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import java.io.IOException;
import java.net.URL;

public class IltalehtiNewsReader extends NewsReader {

    public IltalehtiNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Uutiset", 0, "http://www.iltalehti.fi/rss/uutiset.xml"));
        SECTIONS.add(new Section("Kotimaan uutiset", 0, "http://www.iltalehti.fi/rss/kotimaa.xml"));
        SECTIONS.add(new Section("Ulkomaan uutiset", 0, "http://www.iltalehti.fi/rss/ulkomaat.xml"));

        SECTIONS.add(new Section("Urheilu-uutiset", 0, "http://www.iltalehti.fi/rss/urheilu.xml"));
        SECTIONS.add(new Section("Formula 1", 1, "http://www.iltalehti.fi/rss/formulat.xml"));
        SECTIONS.add(new Section("Jalkapallo", 1, "http://www.iltalehti.fi/rss/jalkapallo.xml"));
        SECTIONS.add(new Section("Jääkiekko", 1, "http://www.iltalehti.fi/rss/jaakiekko.xml"));
        SECTIONS.add(new Section("Ralli", 1, "http://www.iltalehti.fi/rss/ralli.xml"));

        SECTIONS.add(new Section("Viihde", 0, "http://www.iltalehti.fi/rss/viihde.xml"));
        SECTIONS.add(new Section("Musiikki", 1, "http://www.iltalehti.fi/rss/musiikki.xml"));
        SECTIONS.add(new Section("Kuninkaalliset", 1, "http://www.iltalehti.fi/rss/kuninkaalliset.xml"));
        SECTIONS.add(new Section("Leffat", 1, "http://www.iltalehti.fi/rss/leffat.xml"));

        SECTIONS.add(new Section("Autot", 0, "http://www.iltalehti.fi/rss/autot.xml"));
        SECTIONS.add(new Section("Digi", 0, "http://www.iltalehti.fi/rss/digi.xml"));
        SECTIONS.add(new Section("Terveys", 0, "http://www.iltalehti.fi/rss/terveys.xml"));
        SECTIONS.add(new Section("Tyyli.com", 0, "http://www.iltalehti.fi/rss/tyylicom.xml"));
        SECTIONS.add(new Section("Asuminen", 0, "http://www.iltalehti.fi/rss/asuminen.xml"));
        SECTIONS.add(new Section("Matkailu", 0, "http://www.iltalehti.fi/rss/matkailu.xml"));

    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink) {
        try {
            return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("isense");

        if (!e.isEmpty()) {
            e.select(".author,.important-articles-t,.kp-share-area").remove();

            for (org.jsoup.nodes.Element style : e.select("[style]")) {
                style.attr("style", "");
            }

            news.content = e.html();
        }
    }

}
