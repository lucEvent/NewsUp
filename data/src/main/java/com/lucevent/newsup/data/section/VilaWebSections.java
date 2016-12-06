package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VilaWebSections extends Sections {

    public VilaWebSections()
    {
        super();

        add(new Section("Principal", "http://www.vilaweb.cat/rss/vilaweb.rss", 0));

        add(new Section("Pa\u00EDs", "http://www.vilaweb.cat/categoria/pais/feed/", 0));
        add(new Section("Andorra", "http://www.vilaweb.cat/categoria/pais/andorra/feed/", 1));
        add(new Section("Catalunya-nord", "http://www.vilaweb.cat/categoria/pais//feed/", 1));
        add(new Section("Franja-de-ponent", "http://www.vilaweb.cat/categoria/pais/franja-de-ponent/feed/", 1));
        add(new Section("Illes", "http://www.vilaweb.cat/categoria/pais/illes/feed/", 1));
        add(new Section("Pa\u00EDs-valenci\u00E0", "http://www.vilaweb.cat/categoria/pais/pais-valencia/feed/", 1));
        add(new Section("Principat", "http://www.vilaweb.cat/categoria/pais/principat/feed/", 1));

        add(new Section("M\u00F3n", "http://www.vilaweb.cat/categoria/mon/feed/", 0));
        add(new Section("\u00C1frica", "http://www.vilaweb.cat/categoria/mon/africa/feed/", 1));
        add(new Section("Am\u00E8rica", "http://www.vilaweb.cat/categoria/mon/america/feed/", 1));
        add(new Section("\u00C1sia-Pac\u00EDfic", "http://www.vilaweb.cat/categoria/mon/asia-pacific/feed/", 1));
        add(new Section("Europa", "http://www.vilaweb.cat/categoria/mon/europa/feed/", 1));
        add(new Section("Espanya", "http://www.vilaweb.cat/categoria/mon/espanya/feed/", 1));
        add(new Section("Fran\u00E7a", "http://www.vilaweb.cat/categoria/mon/franca/feed/", 1));
        add(new Section("Llevant", "http://www.vilaweb.cat/categoria/mon/llevant/feed/", 1));
        add(new Section("Occit\u00E0nia", "http://www.vilaweb.cat/categoria/mon/occitania/feed/", 1));
        add(new Section("Pa\u00EDs-basc", "http://www.vilaweb.cat/categoria/mon/pais-basc/feed/", 1));

        add(new Section("Societat", "http://www.vilaweb.cat/categoria/societat/feed/", 0));
        add(new Section("Ecologia", "http://www.vilaweb.cat/categoria/societat/ecologia/feed/", 1));
        add(new Section("Educaci\u00F3", "http://www.vilaweb.cat/categoria/societat/educacio/feed/", 1));
        add(new Section("Esports", "http://www.vilaweb.cat/categoria/societat/esports/feed/", 1));
        add(new Section("Policia i just\u00EDcia", "http://www.vilaweb.cat/categoria/societat/policia-i-justicia/feed/", 1));

        add(new Section("Opini\u00F3", "http://www.vilaweb.cat/categoria/opinio/feed/", 0));
        add(new Section("Editorial", "http://www.vilaweb.cat/categoria/opinio/editorial/feed/", 1));
        add(new Section("Mail obert", "http://www.vilaweb.cat/categoria/opinio/mail-obert/feed/", 1));
        add(new Section("Opini\u00F3 contundent", "http://www.vilaweb.cat/categoria/opinio/opinions-contundents/feed/", 1));

        add(new Section("Cultura", "http://www.vilaweb.cat/categoria/cultura/feed/", 0));
        add(new Section("Art i museus", "http://www.vilaweb.cat/categoria/cultura/art-i-museus/feed/", 1));
        add(new Section("Arts esc\u00E8niques", "http://www.vilaweb.cat/categoria/cultura/arts-esceniques/feed/", 1));
        add(new Section("Cinema", "http://www.vilaweb.cat/categoria/cultura/cinema/feed/", 1));
        add(new Section("Cultura popular", "http://www.vilaweb.cat/categoria/cultura/cultura-popular/feed/", 1));
        add(new Section("Gastronomia", "http://www.vilaweb.cat/categoria/cultura/gastronomia/feed/", 1));
        add(new Section("Llengua", "http://www.vilaweb.cat/categoria/cultura/llengua/feed/", 1));
        add(new Section("Lletres", "http://www.vilaweb.cat/categoria/cultura/lletres/feed/", 1));
        add(new Section("Mitjans de comunicaci\u00F3", "http://www.vilaweb.cat/categoria/cultura/mitjans-de-comunicacio/feed/", 1));
        add(new Section("M\u00FAsica", "http://www.vilaweb.cat/categoria/cultura/musica/feed/", 1));

        add(new Section("Economia", "http://www.vilaweb.cat/categoria/economia/feed/", 0));
        add(new Section("Empreses", "http://www.vilaweb.cat/categoria/economia/empreses/feed/", 1));
        add(new Section("Sindicalisme", "http://www.vilaweb.cat/categoria/economia/sindicalisme/feed/", 1));
        add(new Section("Tributs", "http://www.vilaweb.cat/categoria/economia/tributs/feed/", 1));

        add(new Section("Ci\u00E8ncia i tecnologia", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/feed/", 0));
        add(new Section("Internet", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/internet/feed/", 1));
        add(new Section("L'internauta", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/linternauta/feed/", 1));

        add(new Section("Publicacions", "http://www.vilaweb.cat/categoria/publicacions/feed/", 0));
        add(new Section("Cetrencada", "http://www.vilaweb.cat/categoria/publicacions/cetrencada/feed/", 1));
        add(new Section("Fes ta festa", "http://www.vilaweb.cat/categoria/publicacions/fes-ta-festa/feed/", 1));
        add(new Section("Inspira", "http://www.vilaweb.cat/categoria/publicacions/inspira/feed/", 1));
        add(new Section("M\u00E8tode", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/metode/feed/", 1));
        add(new Section("N\u00FAvol", "http://www.vilaweb.cat/categoria/publicacions/nuvol/feed/", 1));
        add(new Section("Tornaveu", "http://www.vilaweb.cat/categoria/publicacions/tornaveu/feed/", 1));
        add(new Section("Verkami", "http://www.vilaweb.cat/categoria/publicacions/verkami/feed/", 1));

        add(new Section("Locals", "http://www.vilaweb.cat/categoria/pais/locals/feed/", 0));
        add(new Section("Austr\u00E0lia", "http://australia.vilaweb.cat/feed/", 1));
        add(new Section("Castell\u00F3 de la Plana", "http://castello.vilaweb.cat/feed/", 1));
        add(new Section("Catalunya Nord", "http://catalunyanord.vilaweb.cat/feed/", 1));
        add(new Section("Mollerussa", "http://mollerussa.vilaweb.cat/feed/", 1));
        add(new Section("Ontinyent", "http://ontinyent.vilaweb.cat/feed/", 1));
        add(new Section("Osona", "http://osona.vilaweb.cat/feed/", 1));

        add(new Section("ACN", "http://www.vilaweb.cat/categoria/acn/feed/", 0));
        add(new Section("Europa-press", "http://www.vilaweb.cat/categoria/europa-press/feed/", 0));

    }
}
