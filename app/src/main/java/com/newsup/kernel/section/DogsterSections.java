package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class DogsterSections extends ArrayList<Section> {

    public DogsterSections() {
        super();

        add(new Section("News", 0));
        add(new Section("The Scoop", 0));
        add(new Section("Lifestyle", 0));

    }

}