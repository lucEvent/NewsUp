package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class RollingStoneSections extends Sections {

    public RollingStoneSections()
    {
        super();

        add(new Section("Main", "https://www.rollingstone.com/rss", 0));

        add(new Section("News", "https://www.rollingstone.com/music+movies+culture+politics+sports/rss", 0));
        add(new Section("Music", "https://www.rollingstone.com/music/rss", 1));
        add(new Section("Movies", "https://www.rollingstone.com/movies/rss", 1));
        add(new Section("Culture", "https://www.rollingstone.com/culture/rss", 1));
        add(new Section("Politics", "https://www.rollingstone.com/politics/rss", 1));
        add(new Section("Sports", "https://www.rollingstone.com/sports/rss", 1));
        add(new Section("TV", "https://www.rollingstone.com/tv/rss", 1));
        add(new Section("Long Reads", "https://www.rollingstone.com/long-reads/rss", 0));
        add(new Section("Country", "https://www.rollingstone.com/country/rss", 0));

        add(new Section("Reviews", null, -1));
        add(new Section("Movie Reviews", "https://www.rollingstone.com/reviews/rss", 1));
        add(new Section("Album Reviews", "https://www.rollingstone.com/albumreviews/rss", 1));
        add(new Section("Live Reviews", "https://www.rollingstone.com/live-reviews/rss", 1));

        add(new Section("Videos", "https://www.rollingstone.com/videos/rss", 0));
        add(new Section("Lists", "https://www.rollingstone.com/lists/rss", 0));
        add(new Section("Galleries", "https://www.rollingstone.com/pictures/rss", 0));
        add(new Section("Interviews", "https://www.rollingstone.com/features/rss", 0));
        add(new Section("Glixel", "https://www.rollingstone.com/glixel/rss", 0));
        add(new Section("Ram Report", "https://www.rollingstone.com/country/ram-report/rss", 0));
        add(new Section("Podcasts", "https://www.rollingstone.com/topic/podcast/rss", 0));

    }

}
