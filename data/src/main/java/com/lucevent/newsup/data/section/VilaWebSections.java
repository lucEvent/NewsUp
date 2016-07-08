package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VilaWebSections extends Sections {

    public VilaWebSections()
    {
        super();

        add(new Section("Principal", "http://www.vilaweb.cat/rss/vilaweb.rss", 0));

        add(new Section("País", "http://www.vilaweb.cat/categoria/pais/feed/", 0));
        add(new Section("Andorra", "http://www.vilaweb.cat/categoria/pais/andorra/feed/", 1));
        add(new Section("Catalunya-nord", "http://www.vilaweb.cat/categoria/pais//feed/", 1));
        add(new Section("Franja-de-ponent", "http://www.vilaweb.cat/categoria/pais/franja-de-ponent/feed/", 1));
        add(new Section("Illes", "http://www.vilaweb.cat/categoria/pais/illes/feed/", 1));
        add(new Section("País-valencià", "http://www.vilaweb.cat/categoria/pais/pais-valencia/feed/", 1));
        add(new Section("Principat", "http://www.vilaweb.cat/categoria/pais/principat/feed/", 1));

        add(new Section("Món", "http://www.vilaweb.cat/categoria/mon/feed/", 0));
        add(new Section("Àfrica", "http://www.vilaweb.cat/categoria/mon/africa/feed/", 1));
        add(new Section("L'Alguer-Sardenya", "http://www.vilaweb.cat/categoria/mon/alguer/feed/", 1));
        add(new Section("Amèrica", "http://www.vilaweb.cat/categoria/mon/america/feed/", 1));
        add(new Section("Àsia-Pacífic", "http://www.vilaweb.cat/categoria/mon/asia-pacific/feed/", 1));
        add(new Section("Europa", "http://www.vilaweb.cat/categoria/mon/europa/feed/", 1));
        add(new Section("Espanya", "http://www.vilaweb.cat/categoria/mon/espanya/feed/", 1));
        add(new Section("França", "http://www.vilaweb.cat/categoria/mon/franca/feed/", 1));
        add(new Section("Llevant", "http://www.vilaweb.cat/categoria/mon/llevant/feed/", 1));
        add(new Section("Occitània", "http://www.vilaweb.cat/categoria/mon/occitania/feed/", 1));
        add(new Section("País-basc", "http://www.vilaweb.cat/categoria/mon/pais-basc/feed/", 1));

        add(new Section("Societat", "http://www.vilaweb.cat/categoria/societat/feed/", 0));
        add(new Section("Ecologia", "http://www.vilaweb.cat/categoria/societat/ecologia/feed/", 1));
        add(new Section("Educació", "http://www.vilaweb.cat/categoria/societat/educacio/feed/", 1));
        add(new Section("Esports", "http://www.vilaweb.cat/categoria/societat/esports/feed/", 1));
        add(new Section("Policia i justícia", "http://www.vilaweb.cat/categoria/societat/policia-i-justicia/feed/", 1));

        add(new Section("Opinió", "http://www.vilaweb.cat/categoria/opinio/feed/", 0));
        add(new Section("Anàlisi", "http://www.vilaweb.cat/categoria/opinio/analisi/feed/", 1));
        add(new Section("Cartes creuades", "http://www.vilaweb.cat/categoria/opinio/cartes-creuades/feed/", 1));
        add(new Section("Editorial", "http://www.vilaweb.cat/categoria/opinio/editorial/feed/", 1));
        add(new Section("Mail obert", "http://www.vilaweb.cat/categoria/opinio/mail-obert/feed/", 1));
        add(new Section("Opinió contundent", "http://www.vilaweb.cat/categoria/opinio/opinions-contundents/feed/", 1));

        add(new Section("Cultura", "http://www.vilaweb.cat/categoria/cultura/feed/", 0));
        add(new Section("Art i museus", "http://www.vilaweb.cat/categoria/cultura/art-i-museus/feed/", 1));
        add(new Section("Arts escèniques", "http://www.vilaweb.cat/categoria/cultura/arts-esceniques/feed/", 1));
        add(new Section("Associacions", "http://www.vilaweb.cat/categoria/cultura/associacions/feed/", 1));
        add(new Section("Cinema", "http://www.vilaweb.cat/categoria/cultura/cinema/feed/", 1));
        add(new Section("Cultura popular", "http://www.vilaweb.cat/categoria/cultura/cultura-popular/feed/", 1));
        add(new Section("Gastronomia", "http://www.vilaweb.cat/categoria/cultura/gastronomia/feed/", 1));
        add(new Section("Llengua", "http://www.vilaweb.cat/categoria/cultura/llengua/feed/", 1));
        add(new Section("Lletres", "http://www.vilaweb.cat/categoria/cultura/lletres/feed/", 1));
        add(new Section("Mitjans de comunicació", "http://www.vilaweb.cat/categoria/cultura/mitjans-de-comunicacio/feed/", 1));
        add(new Section("Música", "http://www.vilaweb.cat/categoria/cultura/musica/feed/", 1));

        add(new Section("Economia", "http://www.vilaweb.cat/categoria/economia/feed/", 0));
        add(new Section("Borsa", "http://www.vilaweb.cat/categoria/economia/borsa/feed/", 1));
        add(new Section("Empreses", "http://www.vilaweb.cat/categoria/economia/empreses/feed/", 1));
        add(new Section("Ocupació", "http://www.vilaweb.cat/categoria/economia/ocupacio/feed/", 1));
        add(new Section("Sindicalisme", "http://www.vilaweb.cat/categoria/economia/sindicalisme/feed/", 1));
        add(new Section("Tributs", "http://www.vilaweb.cat/categoria/economia/tributs/feed/", 1));

        add(new Section("Ciència i tecnologia", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/feed/", 0));
        add(new Section("Internet", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/internet/feed/", 1));
        add(new Section("L'internauta", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/linternauta/feed/", 1));

        add(new Section("Publicacions", "http://www.vilaweb.cat/categoria/publicacions/feed/", 0));
        add(new Section("Cetrencada", "http://www.vilaweb.cat/categoria/publicacions/cetrencada/feed/", 1));
        add(new Section("Fes ta festa", "http://www.vilaweb.cat/categoria/publicacions/fes-ta-festa/feed/", 1));
        add(new Section("Inspira", "http://www.vilaweb.cat/categoria/publicacions/inspira/feed/", 1));
        add(new Section("Mètode", "http://www.vilaweb.cat/categoria/ciencia-i-tecnologia/metode/feed/", 1));
        add(new Section("Núvol", "http://www.vilaweb.cat/categoria/publicacions/nuvol/feed/", 1));
        add(new Section("Tornaveu", "http://www.vilaweb.cat/categoria/publicacions/tornaveu/feed/", 1));
        add(new Section("Verkami", "http://www.vilaweb.cat/categoria/publicacions/verkami/feed/", 1));

        add(new Section("Locals", "http://www.vilaweb.cat/categoria/pais/locals/feed/", 0));
        add(new Section("Austràlia", "http://australia.vilaweb.cat/feed/", 1));
        add(new Section("Castelló de la Plana", "http://castello.vilaweb.cat/feed/", 1));
        add(new Section("Catalunya Nord", "http://catalunyanord.vilaweb.cat/feed/", 1));
        add(new Section("La Rella", "http://larella.vilaweb.cat/feed/", 1));
        add(new Section("Mollerussa", "http://mollerussa.vilaweb.cat/feed/", 1));
        add(new Section("Ontinyent", "http://ontinyent.vilaweb.cat/feed/", 1));
        add(new Section("Osona", "http://osona.vilaweb.cat/feed/", 1));

        add(new Section("ACN", "http://www.vilaweb.cat/categoria/acn/feed/", 0));
        add(new Section("Europa-press", "http://www.vilaweb.cat/categoria/europa-press/feed/", 0));

    }
}
