package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class GizmodoSections extends ArrayList<Section> {

    public GizmodoSections() {
        super();

        add(new Section("Main site", 0));
        add(new Section("UK version", 0));
        add(new Section("Spain version", 0));
        add(new Section("Australia version", 0));

    }

}
