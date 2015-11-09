package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class HelsinkiSanomatNewsReader extends NewsReader {

    public HelsinkiSanomatNewsReader() {
        super();

        SECTIONS = new SectionList();

        SECTIONS.add(new Section("Uutiset", 0, "http://www.hs.fi/uutiset/rss/"));

        SECTIONS.add(new Section("Uutiset osastoittain", 0, null));
        SECTIONS.add(new Section("Kotimaa", 1, "http://www.hs.fi/rss/?osastot=kotimaa"));
        SECTIONS.add(new Section("Politiikka", 1, "http://www.hs.fi/rss/?osastot=politiikka"));
        SECTIONS.add(new Section("Kaupunki", 1, "http://www.hs.fi/rss/?osastot=kaupunki"));
        SECTIONS.add(new Section("Ulkomaat", 1, "http://www.hs.fi/rss/?osastot=ulkomaat"));
        SECTIONS.add(new Section("Talous", 1, "http://www.hs.fi/rss/?osastot=talous"));
        SECTIONS.add(new Section("Urheilu", 1, "http://www.hs.fi/rss/?osastot=urheilu"));
        SECTIONS.add(new Section("Kulttuuri", 1, "http://www.hs.fi/rss/?osastot=kulttuuri"));

        SECTIONS.add(new Section("Teemat", 0, null));
        SECTIONS.add(new Section("Ruoka", 1, "http://www.hs.fi/rss/?osastot=ruoka"));
        SECTIONS.add(new Section("Elämä & Terveys", 1, "http://www.hs.fi/uutiset/osastoittain/rss?osastot=elama,koti,terveys,tyyli,matka,ihmiset"));
        SECTIONS.add(new Section("Kuluttaja", 1, "http://www.hs.fi/rss/?osastot=kuluttaja"));
        SECTIONS.add(new Section("Tiede", 1, "http://www.hs.fi/rss/?osastot=tiede"));
        SECTIONS.add(new Section("Autot", 1, "http://www.hs.fi/rss/?osastot=autot"));
        SECTIONS.add(new Section("Tekniikka", 1, "http://www.hs.fi/rss/?osastot=tekniikka"));
        SECTIONS.add(new Section("Työelämä", 1, "http://www.hs.fi/rss/?osastot=ty%C3%B6el%C3%A4m%C3%A4"));
        SECTIONS.add(new Section("Sunnuntai", 1, "http://www.hs.fi/rss/?osastot=sunnuntai"));
        SECTIONS.add(new Section("Kuukausiliite", 1, "http://www.hs.fi/rss/?osastot=kuukausiliite"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.nodes.Element element = doc.select(".article-body,.post,.body").get(0);
        element.select("script").remove();
        news.content = element.html();
    }

}