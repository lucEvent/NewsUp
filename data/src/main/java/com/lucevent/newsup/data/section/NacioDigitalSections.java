package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class NacioDigitalSections extends Sections {

    public NacioDigitalSections()
    {
        super();

        add(new Section("\u00DAltimes", "http://www.naciodigital.cat/rss/", 0));
        add(new Section("Pol\u00EDtica", "http://www.naciodigital.cat/rss/2/politica", 0));
        add(new Section("El despertador", "http://www.naciodigital.cat/rss/1458/despertador", 0));
        add(new Section("Pa\u00EDs Valenci\u00E0", "http://www.naciodigital.cat/rss/1476/pais/valencia", 0));
        add(new Section("Internacional", "http://www.naciodigital.cat/rss/239/internacional", 0));
        add(new Section("Economia", "http://www.naciodigital.cat/rss/170/economia", 0));
        add(new Section("Societat", "http://www.naciodigital.cat/rss/168/societat", 0));
        add(new Section("Successos", "http://www.naciodigital.cat/rss/190/successos", 0));
        add(new Section("Cultura", "http://www.naciodigital.cat/rss/169/cultura", 0));
        add(new Section("Esports", "http://www.naciodigital.cat/rss/260/esports", 0));
        add(new Section("Tecnologia", "http://www.naciodigital.cat/rss/167/tecnologia", 0));
        add(new Section("Mitjans", "http://www.naciodigital.cat/rss/171/mitjans", 0));
        add(new Section("A la xarxa", "http://www.naciodigital.cat/rss/686/xarxa", 0));
        add(new Section("Les portades del dia", "http://www.naciodigital.cat/rss/976/portades/dia", 0));

    }

}
