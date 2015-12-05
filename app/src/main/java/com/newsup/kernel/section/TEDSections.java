package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class TEDSections extends ArrayList<Section> {

    public TEDSections() {
        super();

        add(new Section("Main talks", 0));
        add(new Section("Talks Videos", 0));

    }

}

