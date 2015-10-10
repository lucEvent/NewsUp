package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class RollingStoneNewsReader extends NewsReader {

    public RollingStoneNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("All news", 0, "http://www.rollingstone.com/news.rss"));
        SECTIONS.add(new Section("Music", 0, "http://www.rollingstone.com/music.rss"));
        SECTIONS.add(new Section("Album Reviews", 1, "http://www.rollingstone.com/music/albumreviews.rss"));
        SECTIONS.add(new Section("Song Reviews", 1, "http://www.rollingstone.com/music/songreviews.rss"));
        SECTIONS.add(new Section("Movies", 0, "http://www.rollingstone.com/movies.rss"));
        SECTIONS.add(new Section("Movie Reviews", 1, "http://www.rollingstone.com/movies/reviews.rss"));
        SECTIONS.add(new Section("Culture", 0, "http://www.rollingstone.com/culture.rss"));
        SECTIONS.add(new Section("Politics", 0, "http://www.rollingstone.com/politics.rss"));
        SECTIONS.add(new Section("Sports", 0, "http://www.rollingstone.com/sports.rss"));
        SECTIONS.add(new Section("All videos", 0, "http://www.rollingstone.com/videos.rss"));
        SECTIONS.add(new Section("Random notes", 0, "http://www.rollingstone.com/randomnotes.rss"));
        SECTIONS.add(new Section("Authors", 0, null));
        SECTIONS.add(new Section("Peter Travers", 1, "http://www.rollingstone.com/contributor/peter-travers.rss"));
        SECTIONS.add(new Section("Rob Sheffield", 1, "http://www.rollingstone.com/contributor/rob-sheffield.rss"));
        SECTIONS.add(new Section("David Fricke", 1, "http://www.rollingstone.com/contributor/david-fricke.rss"));
        SECTIONS.add(new Section("Tim Dickinson", 1, "http://www.rollingstone.com/contributor/tim-dickinson.rss"));

    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element subt = doc.select(".article-sub-title").get(0);
            org.jsoup.nodes.Element img = doc.select(".article-img-holder").get(0);
            org.jsoup.nodes.Element art = doc.select(".article-content").get(0);


            org.jsoup.select.Elements ads = art.select("script,.related-article");
            for (org.jsoup.nodes.Element ad : ads) ad.remove();

            org.jsoup.select.Elements ps = art.select("p");

            StringBuilder sb = new StringBuilder(subt.outerHtml()).append(img.outerHtml());
            for (org.jsoup.nodes.Element p : ps) sb.append(p.outerHtml());

            news.content = sb.toString();
        } catch (Exception exception) {
            debug("[ERROR] title:" + news.title);
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##RollingStoneNR##", text);
    }

}
