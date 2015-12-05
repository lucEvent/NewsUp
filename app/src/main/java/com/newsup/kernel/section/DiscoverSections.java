package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class DiscoverSections extends ArrayList<Section> {

    public DiscoverSections() {
        super();

        add(new Section("Top stories", 0));
        add(new Section("All stories", 0));

        add(new Section("Blogs", -1));
        add(new Section("Latest blogs", 1));
        add(new Section("The Crux", 1));
        add(new Section("Citizen Science Salon", 1));
        add(new Section("Drone 360", 1));
        add(new Section("The extremo files", 1));
        add(new Section("ImaGeo", 1));
        add(new Section("Lovesick Cyborg", 1));
        add(new Section("Neuroskeptic", 1));
        add(new Section("Out There", 1));
        add(new Section("Science Sushi", 1));

        add(new Section("Topics", -1));
        add(new Section("Health & Medicine", 1));
        add(new Section("Mind Brain", 1));
        add(new Section("Technology", 1));
        add(new Section("Space & Physics", 1));
        add(new Section("Environment", 1));

        add(new Section("Departments", -1));
        add(new Section("20 Things You Didn't Know About...", 1));
        add(new Section("Mind Over Matter", 1));
        add(new Section("Notes From Earth", 1));
        add(new Section("Out There", 1));
        add(new Section("Vital Signs", 1));

    }

}