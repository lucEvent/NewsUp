package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class HelsinkiTimesSections extends ArrayList<Section> {

    public HelsinkiTimesSections() {
        super();

        add(new Section("News", 0));
        add(new Section("Domestic", 1));
        add(new Section("Politics", 1));
        add(new Section("Business", 0));
        add(new Section("Columns", 0));

    }

}