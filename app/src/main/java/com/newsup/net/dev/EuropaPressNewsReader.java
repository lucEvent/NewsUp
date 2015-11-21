package com.newsup.net.dev;

import com.newsup.net.NewsReaderDeprecated;

public class EuropaPressNewsReader extends NewsReaderDeprecated {

    public EuropaPressNewsReader() {
        super();

        SECTIONS.add(new SectionDeprecated("Portada", 0, "http://www.europapress.es/rss/rss.aspx"));
        SECTIONS.add(new SectionDeprecated("Nacional", 0, "http://www.europapress.es/rss/rss.aspx?ch=66"));
        SECTIONS.add(new SectionDeprecated("Internacional", 0, "http://www.europapress.es/rss/rss.aspx?ch=69"));
        SECTIONS.add(new SectionDeprecated("Economía", 0, "http://www.europapress.es/rss/rss.aspx?ch=136"));
        SECTIONS.add(new SectionDeprecated("EP Social", 0, "http://www.europapress.es/rss/rss.aspx?ch=313"));
        SECTIONS.add(new SectionDeprecated("Deportes", 0, "http://www.europapress.es/rss/rss.aspx?ch=67"));
        SECTIONS.add(new SectionDeprecated("Chance", 0, "http://www.europapress.es/rss/rss.aspx?ch=549"));
        SECTIONS.add(new SectionDeprecated("Tecnología", 0, "http://www.europapress.es/rss/rss.aspx?ch=445"));
        SECTIONS.add(new SectionDeprecated("Cultura", 0, "http://www.europapress.es/rss/rss.aspx?ch=126"));
        SECTIONS.add(new SectionDeprecated("Sociedad", 0, "http://www.europapress.es/rss/rss.aspx?ch=73"));
        SECTIONS.add(new SectionDeprecated("Motor", 0, "http://www.europapress.es/rss/rss.aspx?ch=435"));
        SECTIONS.add(new SectionDeprecated("Comunicados", 0, "http://www.europapress.es/rss/rss.aspx?ch=137"));

        SECTIONS.add(new SectionDeprecated("Autonomías", 0, "http://www.europapress.es/rss/rss.aspx?ch=279"));
        SECTIONS.add(new SectionDeprecated("Andalucía", 1, "http://www.europapress.es/rss/rss.aspx?ch=279"));
        SECTIONS.add(new SectionDeprecated("Aragón", 1, "http://www.europapress.es/rss/rss.aspx?ch=280"));
        SECTIONS.add(new SectionDeprecated("Asturias", 1, "http://www.europapress.es/rss/rss.aspx?ch=294"));
        SECTIONS.add(new SectionDeprecated("C. Valenciana", 1, "http://www.europapress.es/rss/rss.aspx?ch=192"));
        SECTIONS.add(new SectionDeprecated("Cantabria", 1, "http://www.europapress.es/rss/rss.aspx?ch=281"));
        SECTIONS.add(new SectionDeprecated("Castilla-La Mancha", 1, "http://www.europapress.es/rss/rss.aspx?ch=282"));
        SECTIONS.add(new SectionDeprecated("Castilla y León", 1, "http://www.europapress.es/rss/rss.aspx?ch=283"));
        SECTIONS.add(new SectionDeprecated("Cataluña", 1, "http://www.europapress.es/rss/rss.aspx?ch=284 "));
        SECTIONS.add(new SectionDeprecated("Ceuta y Melilla", 1, "http://www.europapress.es/rss/rss.aspx?ch=310"));
        SECTIONS.add(new SectionDeprecated("Extremadura", 1, "http://www.europapress.es/rss/rss.aspx?ch=285"));
        SECTIONS.add(new SectionDeprecated("Galicia", 1, "http://www.europapress.es/rss/rss.aspx?ch=286"));
        SECTIONS.add(new SectionDeprecated("Islas Baleares", 1, "http://www.europapress.es/rss/rss.aspx?ch=288"));
        SECTIONS.add(new SectionDeprecated("Islas Canarias", 1, "http://www.europapress.es/rss/rss.aspx?ch=287"));
        SECTIONS.add(new SectionDeprecated("La Rioja", 1, "http://www.europapress.es/rss/rss.aspx?ch=291"));
        SECTIONS.add(new SectionDeprecated("Madrid", 1, "http://www.europapress.es/rss/rss.aspx?ch=289"));
        SECTIONS.add(new SectionDeprecated("Murcia", 1, "http://www.europapress.es/rss/rss.aspx?ch=295"));
        SECTIONS.add(new SectionDeprecated("Navarra", 1, "http://www.europapress.es/rss/rss.aspx?ch=293"));
        SECTIONS.add(new SectionDeprecated("País Vasco", 1, "http://www.europapress.es/rss/rss.aspx?ch=290"));

        SECTIONS.add(new SectionDeprecated("Lenguas", 0, "http://www.europapress.es/rss/rss.aspx?ch=56"));
        SECTIONS.add(new SectionDeprecated("Euskera", 1, "http://www.europapress.es/rss/rss.aspx?ch=58"));
        SECTIONS.add(new SectionDeprecated("Galego", 1, "http://www.europapress.es/rss/rss.aspx?ch=57"));
        SECTIONS.add(new SectionDeprecated("Valencià ", 1, "http://www.europapress.es/rss/rss.aspx?ch=60"));
        SECTIONS.add(new SectionDeprecated("Asturianu", 1, "http://www.europapress.es/rss/rss.aspx?ch=395"));

        SECTIONS.add(new SectionDeprecated("Innovación", 0, null));
        SECTIONS.add(new SectionDeprecated("C. Valenciana", 1, "http://www.europapress.es/rss/rss.aspx?ch=214"));
        SECTIONS.add(new SectionDeprecated("Galicia", 1, "http://www.europapress.es/rss/rss.aspx?ch=222"));
        SECTIONS.add(new SectionDeprecated("Andalucía", 1, "http://www.europapress.es/rss/rss.aspx?ch=232"));
        SECTIONS.add(new SectionDeprecated("Castilla-La Mancha", 1, "http://www.europapress.es/rss/rss.aspx?ch=234"));

    }

}