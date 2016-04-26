package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ExpressenSections extends Sections {

    public ExpressenSections()
    {
        super();

        add(new Section("Nyheter", "http://expressen.se/rss/nyheter", 0));
        add(new Section("VästSverige GT", "http://gt.se/rss/nyheter", 0));
        add(new Section("SydSverige KVP", "http://kvp.se/rss/nyheter", 0));

        add(new Section("Sport", "http://expressen.se/rss/sport", 0));
        add(new Section("Fotboll", "http://expressen.se/rss/fotboll", 1));
        add(new Section("Hockey", "http://expressen.se/rss/hockey", 1));

        add(new Section("Nöjesnyheter", "http://expressen.se/rss/noje", 0));
        add(new Section("Debatt", "http://expressen.se/rss/debatt", 0));
        add(new Section("Ledare", "http://expressen.se/rss/ledare", 0));
        add(new Section("Kultur", "http://expressen.se/rss/kultur", 0));
        add(new Section("Hälsa & Skönhet", "http://expressen.se/rss/halsa", 0));
        add(new Section("Leva & Bo", "http://expressen.se/rss/leva-och-bo", 0));
        add(new Section("Motor", "http://expressen.se/rss/motor", 0));
        add(new Section("Res", "http://expressen.se/rss/res", 0));
        add(new Section("Dokument", "http://expressen.se/rss/dokument", 0));

    }

}
