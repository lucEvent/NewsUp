package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;
import java.net.URL;

public class BE_HelsinkiSanomatNewsReader extends BE_NewsReader {

    public BE_HelsinkiSanomatNewsReader() {
        super();

        SECTIONS = new BE_Sections();

        SECTIONS.add(new BE_Section("Uutiset", "http://www.hs.fi/uutiset/rss/"));

        SECTIONS.add(new BE_Section("Uutiset osastoittain", null));
        SECTIONS.add(new BE_Section("Kotimaa", "http://www.hs.fi/rss/?osastot=kotimaa"));
        SECTIONS.add(new BE_Section("Politiikka", "http://www.hs.fi/rss/?osastot=politiikka"));
        SECTIONS.add(new BE_Section("Kaupunki", "http://www.hs.fi/rss/?osastot=kaupunki"));
        SECTIONS.add(new BE_Section("Ulkomaat", "http://www.hs.fi/rss/?osastot=ulkomaat"));
        SECTIONS.add(new BE_Section("Talous", "http://www.hs.fi/rss/?osastot=talous"));
        SECTIONS.add(new BE_Section("Urheilu", "http://www.hs.fi/rss/?osastot=urheilu"));
        SECTIONS.add(new BE_Section("Kulttuuri", "http://www.hs.fi/rss/?osastot=kulttuuri"));

        SECTIONS.add(new BE_Section("Teemat", null));
        SECTIONS.add(new BE_Section("Ruoka", "http://www.hs.fi/rss/?osastot=ruoka"));
        SECTIONS.add(new BE_Section("Elämä & Terveys", "http://www.hs.fi/uutiset/osastoittain/rss?osastot=elama,koti,terveys,tyyli,matka,ihmiset"));
        SECTIONS.add(new BE_Section("Tiede", "http://www.hs.fi/rss/?osastot=tiede"));
        SECTIONS.add(new BE_Section("Autot", "http://www.hs.fi/rss/?osastot=autot"));
        SECTIONS.add(new BE_Section("Tekniikka", "http://www.hs.fi/rss/?osastot=tekniikka"));
        SECTIONS.add(new BE_Section("Sunnuntai", "http://www.hs.fi/rss/?osastot=sunnuntai"));
        SECTIONS.add(new BE_Section("Kuukausiliite", "http://www.hs.fi/rss/?osastot=kuukausiliite"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements intro = doc.select(".scalable-article-top > .main-image-area > .img-wrapper");
        org.jsoup.select.Elements content = doc.select(".article-text-content");

        if (content.isEmpty()) {
            content = doc.select(".additional-article");

            if (content.isEmpty()) {
                content = doc.select(".entry");

                if (content.isEmpty()) {
                    return;
                }

            } else {
                org.jsoup.nodes.Element article = content.get(0);

                intro = article.select(".entry-top > .main-image-area > .img-wrapper");
                for (org.jsoup.nodes.Element img : intro.select("img")) {
                    img.attr("src", img.attr("lazy-src"));
                }
                content = article.select(".entry-content > .text");
            }
        } else {
            content.select(".related-article,.embedded-ad,.credit,script").remove();
        }
        for (org.jsoup.nodes.Element style : intro.select("[style]")) {
            style.attr("style", "");
        }
        for (org.jsoup.nodes.Element style : content.select("[style]")) {
            style.attr("style", "");
        }

        news.content = intro.outerHtml() + content.html();
    }

}