package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPeriodicoCaSections extends Sections {

    public ElPeriodicoCaSections()
    {
        super();

        add(new Section("Portada", "http://www.elperiodico.cat/ca/rss/rss_portada.xml", 0));
        add(new Section("Opinió", "http://www.elperiodico.cat/ca/rss/opinio/rss.xml", 0));
        add(new Section("Internacional", "http://www.elperiodico.cat/ca/rss/internacional/rss.xml", 0));
        add(new Section("Política", "http://www.elperiodico.cat/ca/rss/politica/rss.xml", 0));
        add(new Section("Societat", "http://www.elperiodico.cat/ca/rss/societat/rss.xml", 0));
        add(new Section("Economia", "http://www.elperiodico.cat/ca/rss/economia/rss.xml", 0));
        add(new Section("Tecnologia", "http://www.elperiodico.cat/ca/rss/tecnologia/rss.xml", 0));
        add(new Section("Esports", "http://www.elperiodico.cat/ca/rss/esports/rss.xml", 0));
        add(new Section("Oci i cultura", "http://www.elperiodico.cat/ca/rss/oci-i-cultura/rss.xml", 0));
        add(new Section("Gent i TV", "http://www.elperiodico.cat/ca/rss/gent-i-tv/rss.xml", 0));

        add(new Section("Ciutats", null, -1));
        add(new Section("Barcelona", "http://www.elperiodico.cat/ca/rss/barcelona/rss.xml", 1));
        add(new Section("L'Hospitalet", "http://www.elperiodico.cat/ca/rss/hospitalet/rss.xml", 1));
        add(new Section("Cornellà", "http://www.elperiodico.cat/ca/rss/cornella/rss.xml", 1));
        add(new Section("Sabadell", "http://www.elperiodico.cat/ca/rss/sabadell/rss.xml", 1));
        add(new Section("Terrassa", "http://www.elperiodico.cat/ca/rss/terrassa/rss.xml", 1));
        add(new Section("Badalona", "http://www.elperiodico.cat/ca/rss/badalona/rss.xml", 1));
        add(new Section("Santa Coloma", "http://www.elperiodico.cat/ca/rss/santa-coloma/rss.xml", 1));

        add(new Section("Canal Bellesa", "http://www.elperiodico.cat/ca/rss/bellesa/rss.xml", 0));

        add(new Section("Blogs", null, -1));
        add(new Section("Los restaurantes de Pau Arenós", "http://rdp.elperiodico.com/feed/?_ga=1.183745446.1073564764.1445817152", 1));
        add(new Section("El Tourmalet", "http://tourmalet.elperiodico.com/feed/?_ga=1.183745446.1073564764.1445817152", 1));
        add(new Section("Rumbo a la Casa Blanca #USA2016", "http://blogs.elperiodico.com/rumbo-casa-blanca/feed/?_ga=1.183745446.1073564764.1445817152", 1));
        add(new Section("Bloglobal", "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.183745446.1073564764.1445817152", 1));
        add(new Section("Destinos ", "http://www.visitdestinos.com/feed/", 1));
        add(new Section("+ Digital", "http://blogs.elperiodico.com/masdigital/feed?_ga=1.183745446.1073564764.1445817152", 1));

    }

}
