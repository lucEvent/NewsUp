package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_SkyAndTelescopeNewsReader extends BE_NewsReader {

    public BE_SkyAndTelescopeNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Astronomy News", "http://www.skyandtelescope.com/astronomy-news/feed/"));
        SECTIONS.add(new BE_Section("Night Sky News", "http://www.skyandtelescope.com/astronomy-news/observing-news/feed/"));
        SECTIONS.add(new BE_Section("Solar system", "http://www.skyandtelescope.com/astronomy-news/solar-system/feed/"));
        SECTIONS.add(new BE_Section("Spacecraft and Space missions", "http://www.skyandtelescope.com/astronomy-news/spacecraft-and-space-missions/feed/"));
        SECTIONS.add(new BE_Section("Exoplanets", "http://www.skyandtelescope.com/astronomy-news/exoplanets/feed/"));
        SECTIONS.add(new BE_Section("Galaxies", "http://www.skyandtelescope.com/astronomy-news/galaxies/feed/"));
        SECTIONS.add(new BE_Section("Astrobiology", "http://www.skyandtelescope.com/astronomy-news/astrobiology/feed/"));
        SECTIONS.add(new BE_Section("Black holes", "http://www.skyandtelescope.com/astronomy-news/black-holes/feed/"));
        SECTIONS.add(new BE_Section("People, Places and Events", "http://www.skyandtelescope.com/astronomy-news/people-places-and-events/feed/"));

        SECTIONS.add(new BE_Section("This Week's Sky at a Glance", "http://www.skyandtelescope.com/observing/sky-at-a-glance/feed/"));
        SECTIONS.add(new BE_Section("SkyTour Podcast", "http://www.skyandtelescope.com/observing/astronomy-podcasts/feed/"));
        SECTIONS.add(new BE_Section("Explore the Night with Bob King", "http://www.skyandtelescope.com/astronomy-blogs/explore-night-bob-king/feed/"));
        SECTIONS.add(new BE_Section("Observing Tips & Techniques", "http://www.skyandtelescope.com/observing/feed/"));
        SECTIONS.add(new BE_Section("Astronomy Resources & Education", "http://www.skyandtelescope.com/astronomy-resources/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
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
