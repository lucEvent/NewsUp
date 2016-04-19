package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class RollingStoneSections extends Sections {

    public RollingStoneSections() {
        super();

        add(new Section("All news", "http://www.rollingstone.com/news.rss", 0));

        add(new Section("Music", "http://www.rollingstone.com/music.rss", 0));
        add(new Section("Album Reviews", "http://www.rollingstone.com/music/albumreviews.rss", 1));

        add(new Section("Movies", "http://www.rollingstone.com/movies.rss", 0));
        add(new Section("Movie Reviews", "http://www.rollingstone.com/movies/reviews.rss", 1));

        add(new Section("Culture", "http://www.rollingstone.com/culture.rss", 0));
        add(new Section("Politics", "http://www.rollingstone.com/politics.rss", 0));
        add(new Section("Sports", "http://www.rollingstone.com/sports.rss", 0));
        add(new Section("All videos", "http://www.rollingstone.com/videos.rss", 0));

        add(new Section("Authors", null, -1));
        add(new Section("Rob Sheffield", "http://www.rollingstone.com/contributor/rob-sheffield.rss", 1));
        add(new Section("David Fricke", "http://www.rollingstone.com/contributor/david-fricke.rss", 1));
        add(new Section("Tim Dickinson", "http://www.rollingstone.com/contributor/tim-dickinson.rss", 1));

    }

}
