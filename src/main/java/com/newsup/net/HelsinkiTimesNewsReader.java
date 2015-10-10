package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class HelsinkiTimesNewsReader extends NewsReader {

    public HelsinkiTimesNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main news", 0, "http://www.helsinkitimes.fi/?format=feed&type=rss"));

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

            news.content = doc.select(".item-page > p").outerHtml();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
        }

        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##HelsinkiTimesNR##", text);
    }

}