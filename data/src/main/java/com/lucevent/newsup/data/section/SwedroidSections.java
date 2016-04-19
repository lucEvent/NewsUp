package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SwedroidSections extends Sections {

    public SwedroidSections()
    {
        super();

        add(new Section("Huvud", "http://www.swedroid.se/feed/", 0));

        add(new Section("Artiklar", null, -1));
        add(new Section("Android", "http://www.swedroid.se/category/android-2/feed/", 1));
        add(new Section("Google", "http://www.swedroid.se/category/google-2/feed/", 1));
        add(new Section("Hårdvara", "http://www.swedroid.se/category/hardvara/feed/", 1));
        add(new Section("Mediaenheter", "http://www.swedroid.se/category/mediaenheter/feed/", 1));
        add(new Section("Phablets", "http://www.swedroid.se/category/phablets-2/feed/", 1));
        add(new Section("Smartklockor", "http://www.swedroid.se/category/smartklockor-2/feed/", 1));
        add(new Section("Spel", "http://www.swedroid.se/category/spel-2/feed/", 1));
        add(new Section("Telefoner", "http://www.swedroid.se/category/telefoner/feed/", 1));
        add(new Section("Uppdateringar", "http://www.swedroid.se/category/uppdateringar-2/feed/", 1));
        add(new Section("Mjukvara", "http://www.swedroid.se/category/mjukvara/feed/", 1));
        add(new Section("Rykten", "http://www.swedroid.se/category/rykten/feed/", 1));

    }

}