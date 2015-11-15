package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class RollingStoneNewsReader extends NewsReader {

    public RollingStoneNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("All news", 0, "http://www.rollingstone.com/news.rss"));

        SECTIONS.add(new Section("Music", 0, "http://www.rollingstone.com/music.rss"));
        SECTIONS.add(new Section("Album Reviews", 1, "http://www.rollingstone.com/music/albumreviews.rss"));

        SECTIONS.add(new Section("Movies", 0, "http://www.rollingstone.com/movies.rss"));
        SECTIONS.add(new Section("Movie Reviews", 1, "http://www.rollingstone.com/movies/reviews.rss"));

        SECTIONS.add(new Section("Culture", 0, "http://www.rollingstone.com/culture.rss"));
        SECTIONS.add(new Section("Politics", 0, "http://www.rollingstone.com/politics.rss"));
        SECTIONS.add(new Section("Sports", 0, "http://www.rollingstone.com/sports.rss"));
        SECTIONS.add(new Section("All videos", 0, "http://www.rollingstone.com/videos.rss"));

        SECTIONS.add(new Section("Authors", 0, null));
        SECTIONS.add(new Section("Rob Sheffield", 1, "http://www.rollingstone.com/contributor/rob-sheffield.rss"));
        SECTIONS.add(new Section("David Fricke", 1, "http://www.rollingstone.com/contributor/david-fricke.rss"));
        SECTIONS.add(new Section("Tim Dickinson", 1, "http://www.rollingstone.com/contributor/tim-dickinson.rss"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements img = doc.select(".article-img-holder img,.article-img-holder iframe");
        org.jsoup.select.Elements content = doc.select(".article-content,.long-list-item");

        if (content.isEmpty()) {
            img = doc.select("article .asset-container img,article .asset-container iframe");
            content = doc.select("article .body-text");

            if (content.isEmpty()) {
                return;
            } else {
                content.select(".related-article").remove();
            }
        } else {
            content.select(".related-article,.social-icons-container,.related-topics,.sidebar").remove();
        }

        news.content = img.outerHtml() + content.outerHtml();
    }

}
