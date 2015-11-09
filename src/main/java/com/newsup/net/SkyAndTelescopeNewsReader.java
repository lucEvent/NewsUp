package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class SkyAndTelescopeNewsReader extends NewsReader {

    public SkyAndTelescopeNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Astronomy News", 0, "http://www.skyandtelescope.com/astronomy-news/feed/"));
        SECTIONS.add(new Section("Night Sky News", 1, "http://www.skyandtelescope.com/astronomy-news/observing-news/feed/"));
        SECTIONS.add(new Section("Solar system", 1, "http://www.skyandtelescope.com/astronomy-news/solar-system/feed/"));
        SECTIONS.add(new Section("Spacecraft and Space missions", 1, "http://www.skyandtelescope.com/astronomy-news/spacecraft-and-space-missions/feed/"));
        SECTIONS.add(new Section("Exoplanets", 1, "http://www.skyandtelescope.com/astronomy-news/exoplanets/feed/"));
        SECTIONS.add(new Section("Galaxies", 1, "http://www.skyandtelescope.com/astronomy-news/galaxies/feed/"));
        SECTIONS.add(new Section("Astrobiology", 1, "http://www.skyandtelescope.com/astronomy-news/astrobiology/feed/"));
        SECTIONS.add(new Section("Black holes", 1, "http://www.skyandtelescope.com/astronomy-news/black-holes/feed/"));
        SECTIONS.add(new Section("People, Places and Events", 1, "http://www.skyandtelescope.com/astronomy-news/people-places-and-events/feed/"));

        SECTIONS.add(new Section("This Week's Sky at a Glance", 0, "http://www.skyandtelescope.com/observing/sky-at-a-glance/feed/"));
        SECTIONS.add(new Section("SkyTour Podcast", 0, "http://www.skyandtelescope.com/observing/astronomy-podcasts/feed/"));
        SECTIONS.add(new Section("Explore the Night with Bob King", 0, "http://www.skyandtelescope.com/astronomy-blogs/explore-night-bob-king/feed/"));
        SECTIONS.add(new Section("Observing Tips & Techniques", 0, "http://www.skyandtelescope.com/observing/feed/"));
        SECTIONS.add(new Section("Astronomy Resources & Education", 0, "http://www.skyandtelescope.com/astronomy-resources/feed/"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.select.Elements description = org.jsoup.Jsoup.parseBodyFragment(news.description).select("p");
        if (!description.isEmpty()) {
            news.description = description.get(0).text();
        }
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(content);
        for (org.jsoup.nodes.Element e : doc.getElementsByAttribute("style")) {
            e.attr("style", "");
        }
        news.content = doc.select("body").html();
        return news;
    }

}
