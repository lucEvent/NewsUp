package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class HSNewsReader extends NewsReader {

    public HSNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();

        SECTIONS.add(new Section("UUTISET", 0, "http://www.hs.fi/uutiset/rss/"));

        SECTIONS.add(new Section("UUTISET OSASTOITTAIN", 0, null));
        SECTIONS.add(new Section("KOTIMAA", -1, "http://www.hs.fi/rss/?osastot=kotimaa"));
        SECTIONS.add(new Section("POLITIIKKA", -1, "http://www.hs.fi/rss/?osastot=politiikka"));
        SECTIONS.add(new Section("KAUPUNKI", -1, "http://www.hs.fi/rss/?osastot=kaupunki"));
        SECTIONS.add(new Section("ULKOMAAT", -1, "http://www.hs.fi/rss/?osastot=ulkomaat"));
        SECTIONS.add(new Section("TALOUS", -1, "http://www.hs.fi/rss/?osastot=talous"));
        SECTIONS.add(new Section("URHEILU", -1, "http://www.hs.fi/rss/?osastot=urheilu"));
        SECTIONS.add(new Section("KULTTUURI", -1, "http://www.hs.fi/rss/?osastot=kulttuuri"));

        SECTIONS.add(new Section("TEEMAT", 0, null));
        SECTIONS.add(new Section("RUOKA", -1, "http://www.hs.fi/rss/?osastot=ruoka"));
        SECTIONS.add(new Section("ELÄMÄ & TERVEYS", -1, "http://www.hs.fi/uutiset/osastoittain/rss?osastot=elama,koti,terveys,tyyli,matka,ihmiset"));
        SECTIONS.add(new Section("KULUTTAJA", -1, "http://www.hs.fi/rss/?osastot=kuluttaja"));
        SECTIONS.add(new Section("TIEDE", -1, "http://www.hs.fi/rss/?osastot=tiede"));
        SECTIONS.add(new Section("AUTOT", -1, "http://www.hs.fi/rss/?osastot=autot"));
        SECTIONS.add(new Section("TEKNIIKKA", -1, "http://www.hs.fi/rss/?osastot=tekniikka"));
        SECTIONS.add(new Section("TYÖELÄMÄ", -1, "http://www.hs.fi/rss/?osastot=ty%C3%B6el%C3%A4m%C3%A4"));
        SECTIONS.add(new Section("SUNNUNTAI", -1, "http://www.hs.fi/rss/?osastot=sunnuntai"));
        SECTIONS.add(new Section("KUUKAUSILIITE", -1, "http://www.hs.fi/rss/?osastot=kuukausiliite"));

    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element element = doc.select(".article-body").get(0);
            news.content = element.html();

        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
            e.printStackTrace();
        }

        return news;
    }

} 