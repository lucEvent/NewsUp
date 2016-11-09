package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class HelsinkiSanomatSections extends Sections {

    public HelsinkiSanomatSections()
    {
        super();

        add(new Section("Uutiset", "http://www.hs.fi/uutiset/rss/", 0));

        add(new Section("Uutiset osastoittain", null, -1));
        add(new Section("Kotimaa", "http://www.hs.fi/rss/?osastot=kotimaa", 1));
        add(new Section("Politiikka", "http://www.hs.fi/rss/?osastot=politiikka", 1));
        add(new Section("Kaupunki", "http://www.hs.fi/rss/?osastot=kaupunki", 1));
        add(new Section("Ulkomaat", "http://www.hs.fi/rss/?osastot=ulkomaat", 1));
        add(new Section("Talous", "http://www.hs.fi/rss/?osastot=talous", 1));
        add(new Section("Urheilu", "http://www.hs.fi/rss/?osastot=urheilu", 1));
        add(new Section("Kulttuuri", "http://www.hs.fi/rss/?osastot=kulttuuri", 1));

        add(new Section("Teemat", null, -1));
        add(new Section("Ruoka", "http://www.hs.fi/rss/?osastot=ruoka", 1));
        add(new Section("El\u00E4m\u00E4 & Terveys", "http://www.hs.fi/uutiset/osastoittain/rss?osastot=elama,koti,terveys,tyyli,matka,ihmiset", 1));
        add(new Section("Tiede", "http://www.hs.fi/rss/?osastot=tiede", 1));
        add(new Section("Autot", "http://www.hs.fi/rss/?osastot=autot", 1));
        add(new Section("Tekniikka", "http://www.hs.fi/rss/?osastot=tekniikka", 1));
        add(new Section("Sunnuntai", "http://www.hs.fi/rss/?osastot=sunnuntai", 1));
        add(new Section("Kuukausiliite", "http://www.hs.fi/rss/?osastot=kuukausiliite", 1));

    }

}