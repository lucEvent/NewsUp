package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheVergeSections extends Sections {

    public TheVergeSections()
    {
        super();

        add(new Section("Main", "http://www.theverge.com/rss/full.xml", 0));
        add(new Section("Features", "http://www.theverge.com/rss/group/features/index.xml", 0));

        add(new Section("Tech", "http://www.theverge.com/tech/rss/full.xml", 0));
        add(new Section("Apple", "http://www.theverge.com/apple/rss/full.xml", 1));
        add(new Section("Apps", "http://www.theverge.com/apps/rss/full.xml", 1));
        add(new Section("Business", "http://www.theverge.com/business/rss/full.xml", 1));
        add(new Section("Google", "http://www.theverge.com/google/rss/full.xml", 1));
        add(new Section("Mobile", "http://www.theverge.com/mobile/rss/full.xml", 1));
        add(new Section("Photography", "http://www.theverge.com/photography/rss/full.xml", 1));
        add(new Section("Design", "http://www.theverge.com/design/rss/full.xml", 1));
        add(new Section("Microsoft", "http://www.theverge.com/microsoft/rss/full.xml", 1));
        add(new Section("Virtual Reality", "http://www.theverge.com/rss/group/vr-virtual-reality/index.xml", 1));

        add(new Section("Science", "http://www.theverge.com/science/rss/full.xml", 0));
        add(new Section("Space", "http://www.theverge.com/rss/group/space/index.xml", 1));
        add(new Section("Energy", "http://www.theverge.com/rss/group/energy/index.xml", 1));
        add(new Section("Health", "http://www.theverge.com/rss/group/health/index.xml", 1));
        add(new Section("Environment", "http://www.theverge.com/rss/group/environment/index.xml", 1));

        add(new Section("Culture", "http://www.theverge.com/culture/rss/full.xml", 0));
        add(new Section("Web", "http://www.theverge.com/web/rss/full.xml", 1));
        add(new Section("TV", "http://www.theverge.com/rss/group/tv/index.xml", 1));
        add(new Section("Film", "http://www.theverge.com/rss/group/film/index.xml", 1));
        add(new Section("Games", "http://www.theverge.com/games/rss/full.xml", 1));
        add(new Section("Comics", "http://www.theverge.com/rss/group/comics/index.xml", 1));
        add(new Section("Music", "http://www.theverge.com/rss/group/music/index.xml", 1));

        add(new Section("Cars", "http://www.theverge.com/transportation/rss/full.xml", 0));
        add(new Section("Ride-Sharing", "http://www.theverge.com/ride-sharing/rss/full.xml", 1));
        add(new Section("Cars", "http://www.theverge.com/rss/group/cars/index.xml", 1));
        add(new Section("Mass Transit", "http://www.theverge.com/rss/group/trains/index.xml", 1));
        add(new Section("Aviation", "http://www.theverge.com/rss/group/planes/index.xml", 1));
        add(new Section("Rideables", "http://www.theverge.com/rss/group/bikes/index.xml", 1));

        add(new Section("Reviews", "http://www.theverge.com/rss/group/review/index.xml", 0));
        add(new Section("Headphones", "http://www.theverge.com/headphone-review/rss/full.xml", 1));
        add(new Section("Book Review", "http://www.theverge.com/rss/group/book-review/index.xml", 1));

        add(new Section("Longform", "http://www.theverge.com/longform/rss/full.xml", 0));
        add(new Section("Interview", "http://www.theverge.com/rss/group/interview/index.xml", 1));
        add(new Section("Podcast", "http://www.theverge.com/rss/group/podcast/index.xml", 1));
        add(new Section("Report", "http://www.theverge.com/rss/group/report/index.xml", 1));

    }

}
