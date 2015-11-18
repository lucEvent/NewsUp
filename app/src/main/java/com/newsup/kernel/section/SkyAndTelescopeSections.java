package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class SkyAndTelescopeSections extends ArrayList<Section> {

    public SkyAndTelescopeSections() {
        super();

        add(new Section("Astronomy News", 0));
        add(new Section("Night Sky News", 1));
        add(new Section("Solar system", 1));
        add(new Section("Spacecraft and Space missions", 1));
        add(new Section("Exoplanets", 1));
        add(new Section("Galaxies", 1));
        add(new Section("Astrobiology", 1));
        add(new Section("Black holes", 1));
        add(new Section("People, Places and Events", 1));

        add(new Section("This Week's Sky at a Glance", 0));
        add(new Section("SkyTour Podcast", 0));
        add(new Section("Explore the Night with Bob King", 0));
        add(new Section("Observing Tips & Techniques", 0));
        add(new Section("Astronomy Resources & Education", 0));

    }

}
