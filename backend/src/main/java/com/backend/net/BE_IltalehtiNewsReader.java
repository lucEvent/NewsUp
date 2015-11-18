package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;
import java.net.URL;

public class BE_IltalehtiNewsReader extends BE_NewsReader {

    public BE_IltalehtiNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Uutiset", "http://www.iltalehti.fi/rss/uutiset.xml"));
        SECTIONS.add(new BE_Section("Kotimaan uutiset", "http://www.iltalehti.fi/rss/kotimaa.xml"));
        SECTIONS.add(new BE_Section("Ulkomaan uutiset", "http://www.iltalehti.fi/rss/ulkomaat.xml"));

        SECTIONS.add(new BE_Section("Urheilu-uutiset", "http://www.iltalehti.fi/rss/urheilu.xml"));
        SECTIONS.add(new BE_Section("Formula 1", "http://www.iltalehti.fi/rss/formulat.xml"));
        SECTIONS.add(new BE_Section("Jalkapallo", "http://www.iltalehti.fi/rss/jalkapallo.xml"));
        SECTIONS.add(new BE_Section("Jääkiekko", "http://www.iltalehti.fi/rss/jaakiekko.xml"));
        SECTIONS.add(new BE_Section("Ralli", "http://www.iltalehti.fi/rss/ralli.xml"));

        SECTIONS.add(new BE_Section("Viihde", "http://www.iltalehti.fi/rss/viihde.xml"));
        SECTIONS.add(new BE_Section("Musiikki", "http://www.iltalehti.fi/rss/musiikki.xml"));
        SECTIONS.add(new BE_Section("Kuninkaalliset", "http://www.iltalehti.fi/rss/kuninkaalliset.xml"));
        SECTIONS.add(new BE_Section("Leffat", "http://www.iltalehti.fi/rss/leffat.xml"));

        SECTIONS.add(new BE_Section("Autot", "http://www.iltalehti.fi/rss/autot.xml"));
        SECTIONS.add(new BE_Section("Digi", "http://www.iltalehti.fi/rss/digi.xml"));
        SECTIONS.add(new BE_Section("Terveys", "http://www.iltalehti.fi/rss/terveys.xml"));
        SECTIONS.add(new BE_Section("Tyyli.com", "http://www.iltalehti.fi/rss/tyylicom.xml"));
        SECTIONS.add(new BE_Section("Asuminen", "http://www.iltalehti.fi/rss/asuminen.xml"));
        SECTIONS.add(new BE_Section("Matkailu", "http://www.iltalehti.fi/rss/matkailu.xml"));

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
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
