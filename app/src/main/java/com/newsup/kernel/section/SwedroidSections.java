package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class SwedroidSections extends ArrayList<Section> {

    public SwedroidSections() {
        super();

        add(new Section("Huvud", 0));

        add(new Section("Artiklar", 0));
        add(new Section("Android", 1));
        add(new Section("Google", 1));
        add(new Section("Hårdvara", 1));
        add(new Section("Mediaenheter", 1));
        add(new Section("Phablets", 1));
        add(new Section("Smartklockor", 1));
        add(new Section("Spel", 1));
        add(new Section("Telefoner", 1));
        add(new Section("Uppdateringar", 1));
        add(new Section("Mjukvara", 1));
        add(new Section("Rykten", 1));

    }

}