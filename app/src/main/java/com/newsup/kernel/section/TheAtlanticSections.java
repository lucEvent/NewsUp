package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class TheAtlanticSections extends ArrayList<Section> {

    public TheAtlanticSections() {
        super();

        add(new Section("The Atlantic", 0));
        add(new Section("Politics", 1));
        add(new Section("Business", 1));
        add(new Section("Culture", 1));
        add(new Section("Global", 1));
        add(new Section("Technology", 1));
        add(new Section("U.S.", 1));
        add(new Section("Health", 1));
        add(new Section("Video", 1));
        add(new Section("Education", 1));
        add(new Section("Science", 1));
        add(new Section("Photo", 1));
        add(new Section("Notes", 1));

        add(new Section("The Wire", 0));
        add(new Section("CityLab", 0));

    }

}
