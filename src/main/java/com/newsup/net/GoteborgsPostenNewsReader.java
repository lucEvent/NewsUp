package com.newsup.net;


import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class GoteborgsPostenNewsReader extends NewsReader {

    public GoteborgsPostenNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Göteborg", 0, "http://www.gp.se/1.16942"));
        SECTIONS.add(new Section("Sverige", 0, "http://www.gp.se/1.16943"));
        SECTIONS.add(new Section("Världen", 0, "http://www.gp.se/1.16944"));
        SECTIONS.add(new Section("Ledare", 0, "http://www.gp.se/1.872491"));
        SECTIONS.add(new Section("Debatt", 0, "http://www.gp.se/1.315001"));
        SECTIONS.add(new Section("Sport", 0, "http://www.gp.se/1.16542"));
        SECTIONS.add(new Section("Kultur & Nöje", 0, "http://www.gp.se/1.16941"));
        SECTIONS.add(new Section("Ekonomi", 0, "http://www.gp.se/1.16555"));
        SECTIONS.add(new Section("Konsument", 0, "http://www.gp.se/1.16558"));
        SECTIONS.add(new Section("Bostad", 0, "http://www.gp.se/1.16562"));
        SECTIONS.add(new Section("Resor", 0, "http://www.gp.se/1.16569"));
        SECTIONS.add(new Section("Motor", 0, "http://www.gp.se/1.16570"));
        SECTIONS.add(new Section("Mat & Dryck", 0, "http://www.gp.se/1.16571"));

    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getNewsPage(news);
        if (doc == null) return news;

        try {
            org.jsoup.nodes.Element img = doc.select(".imageWrapper").get(0);
            org.jsoup.nodes.Element body = doc.select(".body").get(0);

            news.content = img.html() + body.html();
        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##GöteborgsPostenNR##", text);
    }

}
