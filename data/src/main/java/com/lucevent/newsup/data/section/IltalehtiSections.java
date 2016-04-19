package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class IltalehtiSections extends Sections {

    public IltalehtiSections()
    {
        super();

        add(new Section("Uutiset", "http://www.iltalehti.fi/rss/uutiset.xml", 0));
        add(new Section("Kotimaan uutiset", "http://www.iltalehti.fi/rss/kotimaa.xml", 0));
        add(new Section("Ulkomaan uutiset", "http://www.iltalehti.fi/rss/ulkomaat.xml", 0));

        add(new Section("Urheilu-uutiset", "http://www.iltalehti.fi/rss/urheilu.xml", 0));
        add(new Section("Formula 1", "http://www.iltalehti.fi/rss/formulat.xml", 1));
        add(new Section("Jalkapallo", "http://www.iltalehti.fi/rss/jalkapallo.xml", 1));
        add(new Section("J‰‰kiekko", "http://www.iltalehti.fi/rss/jaakiekko.xml", 1));
        add(new Section("Ralli", "http://www.iltalehti.fi/rss/ralli.xml", 1));

        add(new Section("Viihde", "http://www.iltalehti.fi/rss/viihde.xml", 0));
        add(new Section("Musiikki", "http://www.iltalehti.fi/rss/musiikki.xml", 1));
        add(new Section("Kuninkaalliset", "http://www.iltalehti.fi/rss/kuninkaalliset.xml", 1));
        add(new Section("Leffat", "http://www.iltalehti.fi/rss/leffat.xml", 1));

        add(new Section("Autot", "http://www.iltalehti.fi/rss/autot.xml", 0));
        add(new Section("Digi", "http://www.iltalehti.fi/rss/digi.xml", 0));
        add(new Section("Terveys", "http://www.iltalehti.fi/rss/terveys.xml", 0));
        add(new Section("Tyyli.com", "http://www.iltalehti.fi/rss/tyylicom.xml", 0));
        add(new Section("Asuminen", "http://www.iltalehti.fi/rss/asuminen.xml", 0));
        add(new Section("Matkailu", "http://www.iltalehti.fi/rss/matkailu.xml", 0));

    }

}
