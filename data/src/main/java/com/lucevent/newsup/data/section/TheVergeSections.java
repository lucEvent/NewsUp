package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheVergeSections extends Sections {

	public TheVergeSections()
	{
		super();

		add(new Section("Main", "https://www.theverge.com/rss/full.xml", 0));
		add(new Section("Features", "https://www.theverge.com/rss/group/features/index.xml", 0));

		add(new Section("Tech", "https://www.theverge.com/tech/rss/full.xml", 0));
		add(new Section("Apple", "https://www.theverge.com/apple/rss/full.xml", 1));
		add(new Section("Apps", "https://www.theverge.com/apps/rss/full.xml", 1));
		add(new Section("Business", "https://www.theverge.com/business/rss/full.xml", 1));
		add(new Section("Google", "https://www.theverge.com/google/rss/full.xml", 1));
		add(new Section("Mobile", "https://www.theverge.com/mobile/rss/full.xml", 1));
		add(new Section("Photography", "https://www.theverge.com/photography/rss/full.xml", 1));
		add(new Section("Design", "https://www.theverge.com/design/rss/full.xml", 1));
		add(new Section("Microsoft", "https://www.theverge.com/microsoft/rss/full.xml", 1));
		add(new Section("Virtual Reality", "https://www.theverge.com/rss/group/vr-virtual-reality/index.xml", 1));

		add(new Section("Science", "https://www.theverge.com/science/rss/full.xml", 0));
		add(new Section("Space", "https://www.theverge.com/rss/group/space/index.xml", 1));
		add(new Section("Energy", "https://www.theverge.com/rss/group/energy/index.xml", 1));
		add(new Section("Health", "https://www.theverge.com/rss/group/health/index.xml", 1));
		add(new Section("Environment", "https://www.theverge.com/rss/group/environment/index.xml", 1));

		add(new Section("Culture", "https://www.theverge.com/culture/rss/full.xml", 0));
		add(new Section("Web", "https://www.theverge.com/web/rss/full.xml", 1));
		add(new Section("TV", "https://www.theverge.com/rss/group/tv/index.xml", 1));
		add(new Section("Film", "https://www.theverge.com/rss/group/film/index.xml", 1));
		add(new Section("Games", "https://www.theverge.com/games/rss/full.xml", 1));
		add(new Section("Comics", "https://www.theverge.com/rss/group/comics/index.xml", 1));
		add(new Section("Music", "https://www.theverge.com/rss/group/music/index.xml", 1));

		add(new Section("Transportation", "https://www.theverge.com/transportation/rss/full.xml", 0));
		add(new Section("Ride-Sharing", "https://www.theverge.com/ride-sharing/rss/full.xml", 1));
		add(new Section("Cars", "https://www.theverge.com/rss/group/cars/index.xml", 1));
		add(new Section("Mass Transit", "https://www.theverge.com/rss/group/trains/index.xml", 1));
		add(new Section("Aviation", "https://www.theverge.com/rss/group/planes/index.xml", 1));
		add(new Section("Rideables", "https://www.theverge.com/rss/group/bikes/index.xml", 1));

		add(new Section("Reviews", "https://www.theverge.com/rss/group/reviews/index.xml", 0));
		add(new Section("Headphones", "https://www.theverge.com/headphone-review/rss/full.xml", 1));
		add(new Section("Book Review", "https://www.theverge.com/rss/group/book-review/index.xml", 1));

		add(new Section("Longform", "https://www.theverge.com/longform/rss/full.xml", 0));
		add(new Section("Interview", "https://www.theverge.com/rss/group/interview/index.xml", 1));
		add(new Section("Podcast", "https://www.theverge.com/rss/group/podcast/index.xml", 1));
		add(new Section("Report", "https://www.theverge.com/rss/group/report/index.xml", 1));

	}

}
