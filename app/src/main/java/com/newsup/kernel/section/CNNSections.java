package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class CNNSections extends ArrayList<Section> {

    public CNNSections() {
        super();

        add(new Section("Top Stories", 0));
        add(new Section("Most Recent", 0));
        add(new Section("World", 0));
        add(new Section("Africa", 1));
        add(new Section("Americas", 1));
        add(new Section("Asia", 1));
        add(new Section("Europe", 1));
        add(new Section("Middle East", 1));
        add(new Section("U.S.", 1));

        add(new Section("Technology", 0));
        add(new Section("Entertainment", 0));
        add(new Section("Politics", 0));
        add(new Section("Health", 0));
        add(new Section("Travel", 0));
        add(new Section("Living", 0));

        add(new Section("World Sport", 0));
        add(new Section("Football", 1));
        add(new Section("Golf", 1));
        add(new Section("Motorsport", 1));
        add(new Section("Tennis", 1));

        add(new Section("Student News", 0));

    }

}