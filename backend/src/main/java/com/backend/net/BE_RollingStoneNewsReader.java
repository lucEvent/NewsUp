package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_RollingStoneNewsReader extends BE_NewsReader {

    public BE_RollingStoneNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("All news", "http://www.rollingstone.com/news.rss"));

        SECTIONS.add(new BE_Section("Music", "http://www.rollingstone.com/music.rss"));
        SECTIONS.add(new BE_Section("Album Reviews", "http://www.rollingstone.com/music/albumreviews.rss"));

        SECTIONS.add(new BE_Section("Movies", "http://www.rollingstone.com/movies.rss"));
        SECTIONS.add(new BE_Section("Movie Reviews", "http://www.rollingstone.com/movies/reviews.rss"));

        SECTIONS.add(new BE_Section("Culture", "http://www.rollingstone.com/culture.rss"));
        SECTIONS.add(new BE_Section("Politics", "http://www.rollingstone.com/politics.rss"));
        SECTIONS.add(new BE_Section("Sports", "http://www.rollingstone.com/sports.rss"));
        SECTIONS.add(new BE_Section("All videos", "http://www.rollingstone.com/videos.rss"));

        SECTIONS.add(new BE_Section("Authors", null));
        SECTIONS.add(new BE_Section("Rob Sheffield", "http://www.rollingstone.com/contributor/rob-sheffield.rss"));
        SECTIONS.add(new BE_Section("David Fricke", "http://www.rollingstone.com/contributor/david-fricke.rss"));
        SECTIONS.add(new BE_Section("Tim Dickinson", "http://www.rollingstone.com/contributor/tim-dickinson.rss"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
