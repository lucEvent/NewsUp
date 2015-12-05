package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class PeopleSections extends ArrayList<Section> {

    public PeopleSections() {
        super();

        add(new Section("Latest News", 0));
        add(new Section("StyleWatch", 0));
        add(new Section("TV Watch", 0));
        add(new Section("Pets", 0));

    }

}
