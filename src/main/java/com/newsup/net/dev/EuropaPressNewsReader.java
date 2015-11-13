package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class EuropaPressNewsReader extends NewsReader {

    public EuropaPressNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://www.europapress.es/rss/rss.aspx"));
        SECTIONS.add(new Section("Nacional", 0, "http://www.europapress.es/rss/rss.aspx?ch=66"));
        SECTIONS.add(new Section("Internacional", 0, "http://www.europapress.es/rss/rss.aspx?ch=69"));
        SECTIONS.add(new Section("Economía", 0, "http://www.europapress.es/rss/rss.aspx?ch=136"));
        SECTIONS.add(new Section("EP Social", 0, "http://www.europapress.es/rss/rss.aspx?ch=313"));
        SECTIONS.add(new Section("Deportes", 0, "http://www.europapress.es/rss/rss.aspx?ch=67"));
        SECTIONS.add(new Section("Chance", 0, "http://www.europapress.es/rss/rss.aspx?ch=549"));
        SECTIONS.add(new Section("Tecnología", 0, "http://www.europapress.es/rss/rss.aspx?ch=445"));
        SECTIONS.add(new Section("Cultura", 0, "http://www.europapress.es/rss/rss.aspx?ch=126"));
        SECTIONS.add(new Section("Sociedad", 0, "http://www.europapress.es/rss/rss.aspx?ch=73"));
        SECTIONS.add(new Section("Motor", 0, "http://www.europapress.es/rss/rss.aspx?ch=435"));
        SECTIONS.add(new Section("Comunicados", 0, "http://www.europapress.es/rss/rss.aspx?ch=137"));

        SECTIONS.add(new Section("Autonomías", 0, "http://www.europapress.es/rss/rss.aspx?ch=279"));
        SECTIONS.add(new Section("Andalucía", 1, "http://www.europapress.es/rss/rss.aspx?ch=279"));
        SECTIONS.add(new Section("Aragón", 1, "http://www.europapress.es/rss/rss.aspx?ch=280"));
        SECTIONS.add(new Section("Asturias", 1, "http://www.europapress.es/rss/rss.aspx?ch=294"));
        SECTIONS.add(new Section("C. Valenciana", 1, "http://www.europapress.es/rss/rss.aspx?ch=192"));
        SECTIONS.add(new Section("Cantabria", 1, "http://www.europapress.es/rss/rss.aspx?ch=281"));
        SECTIONS.add(new Section("Castilla-La Mancha", 1, "http://www.europapress.es/rss/rss.aspx?ch=282"));
        SECTIONS.add(new Section("Castilla y León", 1, "http://www.europapress.es/rss/rss.aspx?ch=283"));
        SECTIONS.add(new Section("Cataluña", 1, "http://www.europapress.es/rss/rss.aspx?ch=284 "));
        SECTIONS.add(new Section("Ceuta y Melilla", 1, "http://www.europapress.es/rss/rss.aspx?ch=310"));
        SECTIONS.add(new Section("Extremadura", 1, "http://www.europapress.es/rss/rss.aspx?ch=285"));
        SECTIONS.add(new Section("Galicia", 1, "http://www.europapress.es/rss/rss.aspx?ch=286"));
        SECTIONS.add(new Section("Islas Baleares", 1, "http://www.europapress.es/rss/rss.aspx?ch=288"));
        SECTIONS.add(new Section("Islas Canarias", 1, "http://www.europapress.es/rss/rss.aspx?ch=287"));
        SECTIONS.add(new Section("La Rioja", 1, "http://www.europapress.es/rss/rss.aspx?ch=291"));
        SECTIONS.add(new Section("Madrid", 1, "http://www.europapress.es/rss/rss.aspx?ch=289"));
        SECTIONS.add(new Section("Murcia", 1, "http://www.europapress.es/rss/rss.aspx?ch=295"));
        SECTIONS.add(new Section("Navarra", 1, "http://www.europapress.es/rss/rss.aspx?ch=293"));
        SECTIONS.add(new Section("País Vasco", 1, "http://www.europapress.es/rss/rss.aspx?ch=290"));

        SECTIONS.add(new Section("Lenguas", 0, "http://www.europapress.es/rss/rss.aspx?ch=56"));
        SECTIONS.add(new Section("Euskera", 1, "http://www.europapress.es/rss/rss.aspx?ch=58"));
        SECTIONS.add(new Section("Galego", 1, "http://www.europapress.es/rss/rss.aspx?ch=57"));
        SECTIONS.add(new Section("Valencià ", 1, "http://www.europapress.es/rss/rss.aspx?ch=60"));
        SECTIONS.add(new Section("Asturianu", 1, "http://www.europapress.es/rss/rss.aspx?ch=395"));

        SECTIONS.add(new Section("Innovación", 0, null));
        SECTIONS.add(new Section("C. Valenciana", 1, "http://www.europapress.es/rss/rss.aspx?ch=214"));
        SECTIONS.add(new Section("Galicia", 1, "http://www.europapress.es/rss/rss.aspx?ch=222"));
        SECTIONS.add(new Section("Andalucía", 1, "http://www.europapress.es/rss/rss.aspx?ch=232"));
        SECTIONS.add(new Section("Castilla-La Mancha", 1, "http://www.europapress.es/rss/rss.aspx?ch=234"));

    }

}