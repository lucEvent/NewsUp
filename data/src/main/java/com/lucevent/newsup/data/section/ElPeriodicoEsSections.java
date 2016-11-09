package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPeriodicoEsSections extends Sections {

    public ElPeriodicoEsSections()
    {
        super();

        add(new Section("Portada", "http://www.elperiodico.com/es/rss/rss_portada.xml", 0));
        add(new Section("Opini\u00F3n", "http://www.elperiodico.com/es/rss/opinion/rss.xml", 0));
        add(new Section("Internacional", "http://www.elperiodico.com/es/rss/internacional/rss.xml", 0));
        add(new Section("Pol\u00EDtica", "http://www.elperiodico.com/es/rss/politica/rss.xml", 0));
        add(new Section("Sociedad", "http://www.elperiodico.com/es/rss/sociedad/rss.xml", 0));
        add(new Section("Econom\u00EDa", "http://www.elperiodico.com/es/rss/economia/rss.xml", 0));
        add(new Section("Tecnolog\u00EDa", "http://www.elperiodico.com/es/rss/tecnologia/rss.xml", 0));
        add(new Section("Deportes", "http://www.elperiodico.com/es/rss/deportes/rss.xml", 0));
        add(new Section("Ocio y cultura", "http://www.elperiodico.com/es/rss/ocio-y-cultura/rss.xml", 0));
        add(new Section("Ciencia", "http://www.elperiodico.com/es/rss/ciencia/rss.xml", 0));
        add(new Section("Medio ambiente", "http://www.elperiodico.com/es/rss/medio-ambiente/rss.xml", 0));
        add(new Section("Sanidad", "http://www.elperiodico.com/es/rss/sanidad/rss.xml", 0));
        add(new Section("Gente y TV", "http://www.elperiodico.com/es/rss/gente/rss.xml", 0));
        add(new Section("eXtra", "http://www.elperiodico.com/es/rss/extra/rss.xml", 0));

        add(new Section("Ciudades", null, -1));
        add(new Section("Barcelona", "http://www.elperiodico.com/es/rss/barcelona/rss.xml", 1));
        add(new Section("L'Hospitalet", "http://www.elperiodico.com/es/rss/hospitalet/rss.xml", 1));
        add(new Section("Sabadell", "http://www.elperiodico.com/es/rss/sabadell/rss.xml", 1));
        add(new Section("Badalona", "http://www.elperiodico.com/es/rss/badalona/rss.xml", 1));
        add(new Section("Santa Coloma", "http://www.elperiodico.com/es/rss/santa-coloma/rss.xml", 1));

        add(new Section("Canal Belleza", "http://www.elperiodico.com/es/rss/belleza/rss.xml", 0));
        add(new Section("Motor", "http://www.elperiodico.com/es/rss/motor/rss.xml", 0));

        add(new Section("Blogs", null, -1));
        add(new Section("Los restaurantes de Pau Aren\u00F3s", "http://rdp.elperiodico.com/feed/?_ga=1.214333143.1954951623.1458825034", 1));
        add(new Section("El Tourmalet", "http://tourmalet.elperiodico.com/feed/?_ga=1.214333143.1954951623.1458825034", 1));
        add(new Section("Bloglobal", "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.214333143.1954951623.1458825034", 1));
        add(new Section("Destinos", "http://www.visitdestinos.com/feed/", 1));
        add(new Section("+ Digital", "http://blogs.elperiodico.com/masdigital/feed?_ga=1.214333143.1954951623.1458825034", 1));

    }

}
