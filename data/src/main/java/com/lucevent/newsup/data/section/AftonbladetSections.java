package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AftonbladetSections extends Sections {

    public AftonbladetSections()
    {
        super();

        add(new Section("Startsidan", "http://www.aftonbladet.se/rss.xml", 0));
        add(new Section("Senaste Nytt", "http://www.aftonbladet.se/senastenytt/rss.xml", 0));
        add(new Section("Nyheter", "http://www.aftonbladet.se/nyheter/rss.xml", 0));
        add(new Section("Sportbladet", "http://www.aftonbladet.se/sportbladet/rss.xml", 0));
        add(new Section("Fotboll", "http://www.aftonbladet.se/sportbladet/fotboll/rss.xml", 1));
        add(new Section("Hockey", "http://www.aftonbladet.se/sportbladet/hockey/rss.xml", 1));
        add(new Section("N\u00F6jesbladet", "http://www.aftonbladet.se/nojesbladet/rss.xml", 0));
        add(new Section("Klick!", "http://www.aftonbladet.se/nojesbladet/klick/rss.xml", 0));
        add(new Section("Ledare", "http://www.aftonbladet.se/ledare/rss.xml", 0));
        add(new Section("Kultur", "http://www.aftonbladet.se/kultur/rss.xml", 0));
        add(new Section("Bil", "http://www.aftonbladet.se/bil/rss.xml", 0));
        add(new Section("Kolumnister", "http://www.aftonbladet.se/nyheter/kolumnister/rss.xml", 0));

    }

}
