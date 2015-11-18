package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class HelsinkiSanomatSections extends ArrayList<Section> {

    public HelsinkiSanomatSections() {
        super();

        add(new Section("Uutiset", 0));

        add(new Section("Uutiset osastoittain", 0));
        add(new Section("Kotimaa", 1));
        add(new Section("Politiikka", 1));
        add(new Section("Kaupunki", 1));
        add(new Section("Ulkomaat", 1));
        add(new Section("Talous", 1));
        add(new Section("Urheilu", 1));
        add(new Section("Kulttuuri", 1));

        add(new Section("Teemat", 0));
        add(new Section("Ruoka", 1));
        add(new Section("Elämä & Terveys", 1));
        add(new Section("Tiede", 1));
        add(new Section("Autot", 1));
        add(new Section("Tekniikka", 1));
        add(new Section("Sunnuntai", 1));
        add(new Section("Kuukausiliite", 1));

    }

}