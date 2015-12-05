package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class FriaTiderSections extends ArrayList<Section> {

    public FriaTiderSections() {
        super();

        add(new Section("Nyheter", 0));
        add(new Section("Politik", 0));
        add(new Section("Ekonomi", 0));
        add(new Section("Kultur", 0));
        add(new Section("Vetenskap", 0));
        add(new Section("Inrikes", 0));
        add(new Section("Utrikes", 0));
        add(new Section("Ledare", 0));
        add(new Section("Special: Sidebar top", 0));
        add(new Section("Media", 0));
        add(new Section("Du betalar", 0));

        add(new Section("Top first", 0));
        add(new Section("Top second", 0));

        add(new Section("Large", 0));
        add(new Section("Medium", 0));
        add(new Section("Normal", 0));
        add(new Section("Wide", 0));

    }

}
