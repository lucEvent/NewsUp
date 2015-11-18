package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class MetroSections extends ArrayList<Section> {

    public MetroSections() {
        super();

        add(new Section("Nyheter", 0));
        add(new Section("Senaste nytt", 0));
        add(new Section("Toppnyheter", 0));

        add(new Section("Sverige", 0));
        add(new Section("Sverige-topp", 1));

        add(new Section("Världen", 0));

        add(new Section("Sport", 0));
        add(new Section("Sport-topp", 1));

        add(new Section("Nöje", 0));
        add(new Section("Teknik", 0));
        add(new Section("Viralgranskaren", 0));
        add(new Section("Kolumner", 0));

    }

}
