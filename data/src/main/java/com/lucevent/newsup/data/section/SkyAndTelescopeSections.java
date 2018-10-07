package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SkyAndTelescopeSections extends Sections {

	public SkyAndTelescopeSections()
	{
		super();

		add(new Section("Home", "https://www.skyandtelescope.com/feed/", 0));

		add(new Section("News", "https://www.skyandtelescope.com/astronomy-news/feed/", 0));
		add(new Section("Observing News", "https://www.skyandtelescope.com/astronomy-news/observing-news/feed/", 1));
		add(new Section("Solar System", "https://www.skyandtelescope.com/astronomy-news/solar-system/feed/", 1));
		add(new Section("Spacecraft & Space Missions", "https://www.skyandtelescope.com/astronomy-news/spacecraft-and-space-missions/feed/", 1));
		add(new Section("Exoplanets", "https://www.skyandtelescope.com/astronomy-news/exoplanets/feed/", 1));
		add(new Section("Galaxies", "https://www.skyandtelescope.com/astronomy-news/galaxies/feed/", 1));
		add(new Section("Cosmology", "https://www.skyandtelescope.com/astronomy-news/cosmology/feed/", 1));
		add(new Section("Astrobiology", "https://www.skyandtelescope.com/astronomy-news/astrobiology/feed/", 1));
		add(new Section("Astronomy & Society", "https://www.skyandtelescope.com/astronomy-news/astronomy-and-society/feed/", 1));
		add(new Section("Black holes", "https://www.skyandtelescope.com/astronomy-news/black-holes/feed/", 1));
		add(new Section("Milky-way", "https://www.skyandtelescope.com/astronomy-news/milky-way/feed/", 1));
		add(new Section("People, places and events", "https://www.skyandtelescope.com/astronomy-news/people-places-and-events/feed/", 1));
		add(new Section("Stellar Science", "https://www.skyandtelescope.com/astronomy-news/stellar-science/feed/", 1));

		add(new Section("Observing", "https://www.skyandtelescope.com/observing/feed/", 0));
		add(new Section("This Week's Sky at a Glance", "https://www.skyandtelescope.com/observing/sky-at-a-glance/feed/", 1));
		add(new Section("Stargazer's Corner", "https://www.skyandtelescope.com/observing/stargazers-corner/feed/", 1));
		add(new Section("Astronomy Podcasts", "https://www.skyandtelescope.com/observing/astronomy-podcasts/feed/", 1));

		add(new Section("Resources & Education", "https://www.skyandtelescope.com/astronomy-resources/feed/", 0));
		add(new Section("Astrophotography", "https://www.skyandtelescope.com/astronomy-resources/astrophotography-tips/feed/", 1));
		add(new Section("Orbital Path Podcasts", "https://www.skyandtelescope.com/astronomy-resources/orbital-path-astronomy-podcast/feed/", 1));

		add(new Section("Astronomy Blogs", "https://www.skyandtelescope.com/astronomy-blogs/feed/", 0));
		add(new Section("Explore the Night - Bob King", "https://www.skyandtelescope.com/astronomy-blogs/explore-night-bob-king/feed/", 1));
		add(new Section("Astronomy in Space - David Dickinson", "https://www.skyandtelescope.com/astronomy-blogs/astronomy-space-david-dickinson/feed/", 1));

		add(new Section("Events", "https://www.skyandtelescope.com/astronomy-events/feed/", 0));
		add(new Section("Editor's Choice Archive", "https://www.skyandtelescope.com/online-gallery/editors-choice-photos/feed/", 0));
		add(new Section("Magazine", "https://www.skyandtelescope.com/sky-and-telescope-magazine/feed/", 0));

	}

}
