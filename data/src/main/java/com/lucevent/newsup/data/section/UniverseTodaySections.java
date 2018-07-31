package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class UniverseTodaySections extends Sections {

	public UniverseTodaySections()
	{
		super();

		add(new Section("Homepage", "https://www.universetoday.com/feed/", 0));

		add(new Section("NASA", "https://www.universetoday.com/tag/nasa/feed/", 0));
		add(new Section("ISS", "https://www.universetoday.com/tag/iss/feed/", 0));
		add(new Section("Black holes", "https://www.universetoday.com/tag/black-holes/feed/", 0));
		add(new Section("Eclipses", "https://www.universetoday.com/tag/eclipse/feed/", 0));
		add(new Section("Astronauts", "https://www.universetoday.com/tag/astronauts/feed/", 0));
		add(new Section("Exoplanets", "https://www.universetoday.com/tag/exoplanets/feed/", 0));
		add(new Section("Milky Way", "https://www.universetoday.com/tag/milky-way/feed/", 0));

		add(new Section("Missions", "https://www.universetoday.com/tag/missions/feed/", 0));
		add(new Section("Curiosity Rover", "https://www.universetoday.com/tag/curiosity-rover/feed/", 1));
		add(new Section("Cassini", "https://www.universetoday.com/tag/cassini/feed/", 1));
		add(new Section("Juno", "https://www.universetoday.com/tag/juno-mission/feed/", 1));

		add(new Section("Solar system", "https://www.universetoday.com/tag/solar-system/feed/", 0));
		add(new Section("Sun", "https://www.universetoday.com/tag/sun/feed/", 1));
		add(new Section("Mercury", "https://www.universetoday.com/tag/mercury/feed/", 1));
		add(new Section("Venus", "https://www.universetoday.com/tag/venus/feed/", 1));
		add(new Section("Earth", "https://www.universetoday.com/tag/earth/feed/", 1));
		add(new Section("Moon", "https://www.universetoday.com/tag/moon/feed/", 1));
		add(new Section("Mars", "https://www.universetoday.com/tag/mars/feed/", 1));
		add(new Section("Jupiter", "https://www.universetoday.com/tag/jupiter/feed/", 1));
		add(new Section("Saturn", "https://www.universetoday.com/tag/saturn/feed/", 1));
		add(new Section("Uranus", "https://www.universetoday.com/tag/uranus/feed/", 1));
		add(new Section("Neptune", "https://www.universetoday.com/tag/neptune/feed/", 1));
		add(new Section("Pluto", "https://www.universetoday.com/tag/pluto/feed/", 1));

	}

}
