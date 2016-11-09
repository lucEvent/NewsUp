package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DagensNyheterSections extends Sections {

    public DagensNyheterSections()
    {
        super();

        add(new Section("Senaste nytt", "http://www.dn.se/rss/senaste-nytt/", 0));
        add(new Section("Nyheter", "http://www.dn.se/nyheter/rss/", 0));
        add(new Section("Goda Nyheter", "http://www.dn.se/goda-nyheter/rss", 0));

        add(new Section("Stockholm", "http://www.dn.se/sthlm/rss/", 0));
        add(new Section("Sverige", "http://www.dn.se/nyheter/sverige/rss", 0));
        add(new Section("V\u00E4rlden", "http://www.dn.se/nyheter/varlden/rss/", 0));

        add(new Section("Ekonomi", "http://www.dn.se/ekonomi/rss/", 0));
        add(new Section("Politik", "http://www.dn.se/nyheter/politik/rss", 0));
        add(new Section("Vetenskap", "http://www.dn.se/nyheter/vetenskap/rss", 0));
        add(new Section("Motor", "http://www.dn.se/motor/rss", 0));

        add(new Section("Sport", "http://www.dn.se/sport/rss/", 0));
        add(new Section("Ishockey", "http://www.dn.se/sport/ishockey/rss", 1));
        add(new Section("Fotboll", "http://www.dn.se/sport/fotboll/rss", 1));
        add(new Section("Slutresultat", "http://www.dn.se/sport/slutresultat/rss", 1));
        add(new Section("Engelska ligan", "http://www.dn.se/sport/engelska-ligan/rss", 1));

        add(new Section("Kultur", "http://www.dn.se/kultur-noje/rss/", 0));
        add(new Section("Bok", "http://www.dn.se/dnbok/rss", 1));
        add(new Section("Kulturdebatt", "http://www.dn.se/kultur-noje/kulturdebatt/rss", 1));
        add(new Section("Film - TV", "http://www.dn.se/kultur-noje/film-tv/rss", 1));
        add(new Section("Musik", "http://www.dn.se/kultur-noje/musik/rss", 1));
        add(new Section("Scen", "http://www.dn.se/kultur-noje/scen/rss", 1));
        add(new Section("Spel", "http://www.dn.se/spel/spel-hem/rss", 1));

        add(new Section("Fr\u00E5gesport", "http://www.dn.se/nyheter/fragesport/rss", 0));
        add(new Section("\u00C5sikt", "http://asikt.dn.se/feed/", 0));

    }

}
