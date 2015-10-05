package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class SvDNewsReader extends NewsReader {

    public SvDNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("HUVUDNYHETER", 0, "http://www.svd.se/?service=rss"));

    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = Jsoup.parse(description).text();
        return new News(title, link, description, date, categories);
    }


    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getNewsPage(news);
        if (doc == null) return news;

        try {
            org.jsoup.nodes.Element element = doc.getElementsByClass("Body").get(0);

            news.content = Jsoup.clean(element.html(), Whitelist.basic());

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##SvDNewsReader##", text);
    }

}
