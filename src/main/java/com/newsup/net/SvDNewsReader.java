package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;

public class SvDNewsReader extends NewsReader {

    public SvDNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Huvudnyheter", 0, "http://www.svd.se/?service=rss"));
        SECTIONS.add(new Section("Articles", 0, "http://www.svd.se/feed/articles.rss"));

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
            org.jsoup.nodes.Element e = doc.select(".Body").get(0);

            org.jsoup.select.Elements ads = e.select(".Body-ad");
            for (org.jsoup.nodes.Element ad : ads) {
                ad.remove();
            }
            e.select(".Body-pull").remove();

            news.content = e.html();

        } catch (Exception e) {
            debug("[ERROR] tit:" + news.title);
            e.printStackTrace();
        }

        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##SvDNewsReader##", text);
    }

}
