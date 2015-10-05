package com.newsup.net;


import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;

public class ElAndroideLibreNewsReader extends NewsReader {

    public ElAndroideLibreNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Principal", 0, "http://feeds.feedburner.com/elandroidelibre"));

    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = Jsoup.parse(description).getElementsByTag("p").get(0).text().replace("[...]", "");
        return new News(title, link, description, date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getNewsPage(news);
        if (doc == null) return news;

        try {

            news.content = doc.select("#singlePostContent").get(0).html();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##ElAndroideLibreNR##", text);
    }

}
