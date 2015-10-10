package com.newsup.net;


import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class ExpressenNewsReader extends NewsReader {

    public ExpressenNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Nyheter", 0, "http://expressen.se/rss/nyheter"));
        SECTIONS.add(new Section("VästSverige GT", 0, "http://gt.se/rss/nyheter"));
        SECTIONS.add(new Section("SydSverige KVP", 0, "http://kvp.se/rss/nyheter"));

        SECTIONS.add(new Section("Sport", 0, "http://expressen.se/rss/sport"));
        SECTIONS.add(new Section("Fotboll", 1, "http://expressen.se/rss/fotboll"));
        SECTIONS.add(new Section("Hockey", 1, "http://expressen.se/rss/hockey"));

        SECTIONS.add(new Section("Nöjesnyheter", 0, "http://expressen.se/rss/noje"));
        SECTIONS.add(new Section("Debatt", 0, "http://expressen.se/rss/debatt"));
        SECTIONS.add(new Section("Ledare", 0, "http://expressen.se/rss/ledare"));
        SECTIONS.add(new Section("Kultur", 0, "http://expressen.se/rss/kultur"));
        SECTIONS.add(new Section("Ekonomi", 0, "http://expressen.se/rss/ekonomi"));
        SECTIONS.add(new Section("Hälsa & Skönhet", 0, "http://expressen.se/rss/halsa"));
        SECTIONS.add(new Section("Leva & Bo", 0, "http://expressen.se/rss/leva-och-bo"));
        SECTIONS.add(new Section("Motor", 0, "http://expressen.se/rss/motor"));
        SECTIONS.add(new Section("Res", 0, "http://expressen.se/rss/res"));
        SECTIONS.add(new Section("Dokument", 0, "http://expressen.se/rss/dokument"));
        SECTIONS.add(new Section("Extra", 0, "http://expressen.se/rss/extra"));

    }

    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = org.jsoup.Jsoup.parseBodyFragment(description).text();
        return new News(title, link, description, date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element e = doc.select(".b-article__content").get(0);
            e.select(".b-soc-panel").remove();
            e.select(".b-ad__wrapper").remove();
            news.content = e.html();

        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##ExpressenNewsReader##", text);
    }

}
