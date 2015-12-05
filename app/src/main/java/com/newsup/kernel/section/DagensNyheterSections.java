package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class DagensNyheterSections extends ArrayList<Section> {

    public DagensNyheterSections() {
        super();

        add(new Section("Senaste nytt", 0));
        add(new Section("Nyheter", 0));
        add(new Section("Goda Nyheter", 0));

        add(new Section("Stockholm", 0));
        add(new Section("Sverige", 0));
        add(new Section("Världen", 0));

        add(new Section("Ekonomi", 0));
        add(new Section("Politik", 0));
        add(new Section("Vetenskap", 0));
        add(new Section("Motor", 0));

        add(new Section("Sport", 0));
        add(new Section("Stories", 1));
        add(new Section("Ishockey", 1));
        add(new Section("Fotboll", 1));
        add(new Section("Målservice", 1));
        add(new Section("Slutresultat", 1));
        add(new Section("Engelska ligan", 1));

        add(new Section("Kultur", 0));
        add(new Section("Bok", 1));
        add(new Section("Kulturdebatt", 1));
        add(new Section("Film - TV", 1));
        add(new Section("Musik", 1));
        add(new Section("Scen", 1));
        add(new Section("Spel", 1));

        add(new Section("Val", 0));

        add(new Section("Frågesport", 0));
        add(new Section("Åsikt", 0));

    }

}
