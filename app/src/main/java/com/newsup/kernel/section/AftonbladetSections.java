package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class AftonbladetSections extends ArrayList<Section> {

    public AftonbladetSections() {
        super();

        add(new Section("Startsidan", 0));
        add(new Section("Senaste Nytt", 0));
        add(new Section("Nyheter", 0));
        add(new Section("Sportbladet", 0));
        add(new Section("Fotboll", 1));
        add(new Section("Hockey", 1));
        add(new Section("Nöjesbladet", 0));
        add(new Section("Klick!", 0));
        add(new Section("Ledare", 0));
        add(new Section("Kultur", 0));
        add(new Section("Wellness", 0));
        add(new Section("Bil", 0));
        add(new Section("Kolumnister", 0));

    }

}
