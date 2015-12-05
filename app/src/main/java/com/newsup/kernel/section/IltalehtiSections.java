package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class IltalehtiSections extends ArrayList<Section> {

    public IltalehtiSections() {
        super();

        add(new Section("Uutiset", 0));
        add(new Section("Kotimaan uutiset", 0));
        add(new Section("Ulkomaan uutiset", 0));

        add(new Section("Urheilu-uutiset", 0));
        add(new Section("Formula 1", 1));
        add(new Section("Jalkapallo", 1));
        add(new Section("Jääkiekko", 1));
        add(new Section("Ralli", 1));

        add(new Section("Viihde", 0));
        add(new Section("Musiikki", 1));
        add(new Section("Kuninkaalliset", 1));
        add(new Section("Leffat", 1));

        add(new Section("Autot", 0));
        add(new Section("Digi", 0));
        add(new Section("Terveys", 0));
        add(new Section("Tyyli.com", 0));
        add(new Section("Asuminen", 0));
        add(new Section("Matkailu", 0));

    }

}
