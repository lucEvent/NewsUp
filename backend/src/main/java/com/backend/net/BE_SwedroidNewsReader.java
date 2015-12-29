package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_SwedroidNewsReader extends BE_NewsReader {

    public BE_SwedroidNewsReader() {
        super(true);

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Huvud", "http://www.swedroid.se/feed/"));

        SECTIONS.add(new BE_Section("Artiklar", null));
        SECTIONS.add(new BE_Section("Android", "http://www.swedroid.se/category/android-2/feed/"));
        SECTIONS.add(new BE_Section("Google", "http://www.swedroid.se/category/google-2/feed/"));
        SECTIONS.add(new BE_Section("Hårdvara", "http://www.swedroid.se/category/hardvara/feed/"));
        SECTIONS.add(new BE_Section("Mediaenheter", "http://www.swedroid.se/category/mediaenheter/feed/"));
        SECTIONS.add(new BE_Section("Phablets", "http://www.swedroid.se/category/phablets-2/feed/"));
        SECTIONS.add(new BE_Section("Smartklockor", "http://www.swedroid.se/category/smartklockor-2/feed/"));
        SECTIONS.add(new BE_Section("Spel", "http://www.swedroid.se/category/spel-2/feed/"));
        SECTIONS.add(new BE_Section("Telefoner", "http://www.swedroid.se/category/telefoner/feed/"));
        SECTIONS.add(new BE_Section("Uppdateringar", "http://www.swedroid.se/category/uppdateringar-2/feed/"));
        SECTIONS.add(new BE_Section("Mjukvara", "http://www.swedroid.se/category/mjukvara/feed/"));
        SECTIONS.add(new BE_Section("Rykten", "http://www.swedroid.se/category/rykten/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        int s = news.description.indexOf("[…]");
        if (s != -1) {
            news.description = news.description.substring(s);
        }
        if (content.length() > 0) {
            news.content = content.replace("style=", "none=");
        }
        return news;
    }

}