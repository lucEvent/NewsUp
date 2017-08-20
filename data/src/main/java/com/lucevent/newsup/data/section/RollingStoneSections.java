package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class RollingStoneSections extends Sections {

    public RollingStoneSections()
    {
        super();

        add(new Section("Main", "http://www.rollingstone.com/rss", 0));

        add(new Section("News", "http://www.rollingstone.com/music+movies+culture+politics+sports/rss", 0));
        add(new Section("Music", "http://www.rollingstone.com/music/rss", 1));
        add(new Section("Movies", "http://www.rollingstone.com/movies/rss", 1));
        add(new Section("Culture", "http://www.rollingstone.com/culture/rss", 1));
        add(new Section("Politics", "http://www.rollingstone.com/politics/rss", 1));
        add(new Section("Sports", "http://www.rollingstone.com/sports/rss", 1));
        add(new Section("TV", "http://www.rollingstone.com/tv/rss", 1));
        add(new Section("Long Reads", "http://www.rollingstone.com/long-reads/rss", 0));
        add(new Section("Country", "http://www.rollingstone.com/country/rss", 0));

        add(new Section("Reviews", null, -1));
        add(new Section("Movie Reviews", "http://www.rollingstone.com/reviews/rss", 1));
        add(new Section("Album Reviews", "http://www.rollingstone.com/albumreviews/rss", 1));
        add(new Section("Live Reviews", "http://www.rollingstone.com/live-reviews/rss", 1));

        add(new Section("Videos", "http://www.rollingstone.com/videos/rss", 0));
        add(new Section("Lists", "http://www.rollingstone.com/lists/rss", 0));
        add(new Section("Galleries", "http://www.rollingstone.com/pictures/rss", 0));
        add(new Section("Interviews", "http://www.rollingstone.com/features/rss", 0));
        add(new Section("Glixel", "http://www.rollingstone.com/glixel/rss", 0));
        add(new Section("Ram Report", "http://www.rollingstone.com/country/ram-report/rss", 0));
        add(new Section("Podcasts", "http://www.rollingstone.com/topic/podcast/rss", 0));

    }

}
