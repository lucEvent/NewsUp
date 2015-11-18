package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class SwedroidNewsReader extends NewsReader {

    public SwedroidNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Start", 0, "http://www.swedroid.se/feed/"));

        SECTIONS.add(new SectionDeprecated("Artiklar", 0, null));
        SECTIONS.add(new SectionDeprecated("Android", 1, "http://www.swedroid.se/category/android-2/feed/"));
        SECTIONS.add(new SectionDeprecated("Google", 1, "http://www.swedroid.se/category/google-2/feed/"));
        SECTIONS.add(new SectionDeprecated("Hårdvara", 1, "http://www.swedroid.se/category/hardvara/feed/"));
        SECTIONS.add(new SectionDeprecated("Mediaenheter", 1, "http://www.swedroid.se/category/mediaenheter/feed/"));
        SECTIONS.add(new SectionDeprecated("Phablets", 1, "http://www.swedroid.se/category/phablets-2/feed/"));
        SECTIONS.add(new SectionDeprecated("Smartklockor", 1, "http://www.swedroid.se/category/smartklockor-2/feed/"));
        SECTIONS.add(new SectionDeprecated("Spel", 1, "http://www.swedroid.se/category/spel-2/feed/"));
        SECTIONS.add(new SectionDeprecated("Telefoner", 1, "http://www.swedroid.se/category/telefoner/feed/"));
        SECTIONS.add(new SectionDeprecated("Uppdateringar", 1, "http://www.swedroid.se/category/uppdateringar-2/feed/"));
        SECTIONS.add(new SectionDeprecated("Mjukvara", 1, "http://www.swedroid.se/category/mjukvara/feed/"));
        SECTIONS.add(new SectionDeprecated("Närmare titt på", 1, "http://www.swedroid.se/category/narmare-titt-pa/feed/"));
        SECTIONS.add(new SectionDeprecated("Rykten", 1, "http://www.swedroid.se/category/rykten/feed/"));

        SECTIONS.add(new SectionDeprecated("Recensioner", 0, "http://www.swedroid.se/recensioner/feed/"));

    }

}