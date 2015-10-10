package com.newsup.net;


import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class FriaTiderNewsReader extends NewsReader {

    public FriaTiderNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Nyheter", 0, "http://www.friatider.se/rss.xml"));
        SECTIONS.add(new Section("Featured", 0, "http://www.friatider.se/taxonomy/term/15/feed"));

        SECTIONS.add(new Section("Politik", 0, "http://www.friatider.se/taxonomy/term/38/feed"));
        SECTIONS.add(new Section("Ekonomi", 0, "http://www.friatider.se/taxonomy/term/2/feed"));
        SECTIONS.add(new Section("Kultur", 0, "http://www.friatider.se/taxonomy/term/3/feed"));
        SECTIONS.add(new Section("Vetenskap", 0, "http://www.friatider.se/taxonomy/term/19/feed"));
        SECTIONS.add(new Section("Inrikes", 0, "http://www.friatider.se/taxonomy/term/20/feed"));
        SECTIONS.add(new Section("Utrikes", 0, "http://www.friatider.se/taxonomy/term/21/feed"));
        SECTIONS.add(new Section("Insändare", 0, "http://www.friatider.se/taxonomy/term/30/feed"));
        SECTIONS.add(new Section("Ledare", 0, "http://www.friatider.se/taxonomy/term/31/feed"));
        SECTIONS.add(new Section("Livsstil", 0, "http://www.friatider.se/taxonomy/term/1/feed"));
        SECTIONS.add(new Section("Special: Sidebar top", 0, "http://www.friatider.se/taxonomy/term/16/feed"));
        SECTIONS.add(new Section("Debatt", 0, "http://www.friatider.se/taxonomy/term/39/feed"));
        SECTIONS.add(new Section("Media", 0, "http://www.friatider.se/taxonomy/term/36/feed"));
        SECTIONS.add(new Section("Du betalar", 0, "http://www.friatider.se/taxonomy/term/34/feed"));

        SECTIONS.add(new Section("Top first", 0, "http://www.friatider.se/taxonomy/term/26/feed"));
        SECTIONS.add(new Section("Top second", 0, "http://www.friatider.se/taxonomy/term/27/feed"));

        SECTIONS.add(new Section("Large", 0, "http://www.friatider.se/taxonomy/term/4/feed"));
        SECTIONS.add(new Section("Medium", 0, "http://www.friatider.se/taxonomy/term/5/feed"));
        SECTIONS.add(new Section("Small", 0, "http://www.friatider.se/taxonomy/term/6/feed"));
        SECTIONS.add(new Section("Normal", 0, "http://www.friatider.se/taxonomy/term/7/feed"));
        SECTIONS.add(new Section("Wide", 0, "http://www.friatider.se/taxonomy/term/8/feed"));

    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element e = doc.select(".node").get(0);

            org.jsoup.select.Elements ads = e.select("h1,.rightbox");
            for (org.jsoup.nodes.Element ad : ads)
                ad.remove();

            news.content = e.html();
        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##FriaTiderNewsReader##", text);
    }

}
