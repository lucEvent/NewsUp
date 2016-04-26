package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SkyAndTelescopeSections extends Sections {

    public SkyAndTelescopeSections()
    {
        super();

        add(new Section("Astronomy News", "http://www.skyandtelescope.com/astronomy-news/feed/", 0));
        add(new Section("Night Sky News", "http://www.skyandtelescope.com/astronomy-news/observing-news/feed/", 1));
        add(new Section("Solar system", "http://www.skyandtelescope.com/astronomy-news/solar-system/feed/", 1));
        add(new Section("Spacecraft and Space missions", "http://www.skyandtelescope.com/astronomy-news/spacecraft-and-space-missions/feed/", 1));
        add(new Section("Exoplanets", "http://www.skyandtelescope.com/astronomy-news/exoplanets/feed/", 1));
        add(new Section("Galaxies", "http://www.skyandtelescope.com/astronomy-news/galaxies/feed/", 1));
        add(new Section("Black holes", "http://www.skyandtelescope.com/astronomy-news/black-holes/feed/", 1));
        add(new Section("People, Places and Events", "http://www.skyandtelescope.com/astronomy-news/people-places-and-events/feed/", 1));

        add(new Section("This Week's Sky at a Glance", "http://www.skyandtelescope.com/observing/sky-at-a-glance/feed/", 0));
        add(new Section("SkyTour Podcast", "http://www.skyandtelescope.com/observing/astronomy-podcasts/feed/", 0));
        add(new Section("Explore the Night with Bob King", "http://www.skyandtelescope.com/astronomy-blogs/explore-night-bob-king/feed/", 0));
        add(new Section("Observing Tips & Techniques", "http://www.skyandtelescope.com/observing/feed/", 0));
        add(new Section("Astronomy Resources & Education", "http://www.skyandtelescope.com/astronomy-resources/feed/", 0));

    }

}
