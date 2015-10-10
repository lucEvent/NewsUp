package com.newsup.net;


import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class IltalehtiNewsReader extends NewsReader {

    public IltalehtiNewsReader(Handler handler, Context context) {
        super(handler, context);

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
        SECTIONS.add(new Section("Ruoka", 0, "http://www.iltalehti.fi/rss/ruoka.xml"));
        SECTIONS.add(new Section("Terveys", 0, "http://www.iltalehti.fi/rss/terveys.xml"));
        SECTIONS.add(new Section("Tyyli.com", 0, "http://www.iltalehti.fi/rss/tyylicom.xml"));
        SECTIONS.add(new Section("Asuminen", 0, "http://www.iltalehti.fi/rss/asuminen.xml"));
        SECTIONS.add(new Section("Matkailu", 0, "http://www.iltalehti.fi/rss/matkailu.xml"));

    }

    @Override
    protected Document getDocument(String rsslink) throws IOException {
        return Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element root = doc.getElementsByTag("isense").get(0);
            Elements elements = root.children();
            int last = elements.indexOf(root.getElementsByClass("author").get(0));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= last; ++i) {
                sb.append(elements.get(i).outerHtml());
            }
            news.content = sb.toString();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##IltalehtiNewsReader##", text);
    }

}
