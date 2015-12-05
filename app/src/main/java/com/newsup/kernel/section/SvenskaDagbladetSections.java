package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class SvenskaDagbladetSections extends ArrayList<Section> {

    public SvenskaDagbladetSections() {
        super();

        add(new Section("Huvudnyheter", 0));
        add(new Section("Articles", 0));

    }

}
