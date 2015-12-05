package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class RollingStoneSections extends ArrayList<Section> {

    public RollingStoneSections() {
        super();

        add(new Section("All news", 0));

        add(new Section("Music", 0));
        add(new Section("Album Reviews", 1));

        add(new Section("Movies", 0));
        add(new Section("Movie Reviews", 1));

        add(new Section("Culture", 0));
        add(new Section("Politics", 0));
        add(new Section("Sports", 0));
        add(new Section("All videos", 0));

        add(new Section("Authors", -1));
        add(new Section("Rob Sheffield", 1));
        add(new Section("David Fricke", 1));
        add(new Section("Tim Dickinson", 1));

    }

}
