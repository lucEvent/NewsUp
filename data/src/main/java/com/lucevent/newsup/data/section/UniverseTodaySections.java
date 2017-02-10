package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class UniverseTodaySections extends Sections {

    public UniverseTodaySections()
    {
        super();

        add(new Section("Homepage", "http://www.universetoday.com/feed/", 0));

        add(new Section("NASA", "http://www.universetoday.com/tag/nasa/feed/", 0));
        add(new Section("ISS", "http://www.universetoday.com/tag/iss/feed/", 0));
        add(new Section("Black holes", "http://www.universetoday.com/tag/black-holes/feed/", 0));
        add(new Section("Eclipses", "http://www.universetoday.com/tag/eclipse/feed/", 0));
        add(new Section("Meteorites", "http://www.universetoday.com/tag/meteorites/feed/", 0));
        add(new Section("Astronauts", "http://www.universetoday.com/tag/astronauts/feed/", 0));
        add(new Section("Exoplanets", "http://www.universetoday.com/tag/exoplanets/feed/", 0));
        add(new Section("Milky Way", "http://www.universetoday.com/tag/milky-way/feed/", 0));

        add(new Section("Missions", "http://www.universetoday.com/tag/missions/feed/", 0));
        add(new Section("Curiosity Rover", "http://www.universetoday.com/tag/curiosity-rover/feed/", 1));
        add(new Section("Cassini", "http://www.universetoday.com/tag/cassini/feed/", 1));
        add(new Section("Juno", "http://www.universetoday.com/tag/juno-mission/feed/", 1));

        add(new Section("Solar system", "http://www.universetoday.com/tag/solar-system/feed/", 0));
        add(new Section("Sun", "http://www.universetoday.com/tag/sun/feed/", 1));
        add(new Section("Mercury", "http://www.universetoday.com/tag/mercury/feed/", 1));
        add(new Section("Venus", "http://www.universetoday.com/tag/venus/feed/", 1));
        add(new Section("Earth", "http://www.universetoday.com/tag/earth/feed/", 1));
        add(new Section("Moon", "http://www.universetoday.com/tag/moon/feed/", 1));
        add(new Section("Mars", "http://www.universetoday.com/tag/mars/feed/", 1));
        add(new Section("Jupiter", "http://www.universetoday.com/tag/jupiter/feed/", 1));
        add(new Section("Saturn", "http://www.universetoday.com/tag/saturn/feed/", 1));
        add(new Section("Uranus", "http://www.universetoday.com/tag/uranus/feed/", 1));
        add(new Section("Neptune", "http://www.universetoday.com/tag/neptune/feed/", 1));
        add(new Section("Pluto", "http://www.universetoday.com/tag/pluto/feed/", 1));

    }

}
