package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class LifeHackerNewsReader extends NewsReader {

    public LifeHackerNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main", 0, "http://feeds.gawker.com/lifehacker/vip"));

    }

    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        News res = new News(title, link, "", date, categories);
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(description);
        doc.select("img").last().remove();
        res.content = doc.html();
        return res;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##LifeHackerNR##", text);
    }

}
