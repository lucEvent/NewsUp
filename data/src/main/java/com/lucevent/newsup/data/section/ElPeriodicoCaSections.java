package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPeriodicoCaSections extends Sections {

    public ElPeriodicoCaSections()
    {
        super();

        add(new Section("Portada", "http://www.elperiodico.cat/ca/rss/rss_portada.xml", 0));
        add(new Section("Opini\u00F3", "http://www.elperiodico.cat/ca/rss/opinio/rss.xml", 0));
        add(new Section("Internacional", "http://www.elperiodico.cat/ca/rss/internacional/rss.xml", 0));
        add(new Section("Pol\u00EDtica", "http://www.elperiodico.cat/ca/rss/politica/rss.xml", 0));
        add(new Section("Societat", "http://www.elperiodico.cat/ca/rss/societat/rss.xml", 0));
        add(new Section("Economia", "http://www.elperiodico.cat/ca/rss/economia/rss.xml", 0));
        add(new Section("Tecnologia", "http://www.elperiodico.cat/ca/rss/tecnologia/rss.xml", 0));
        add(new Section("Esports", "http://www.elperiodico.cat/ca/rss/esports/rss.xml", 0));
        add(new Section("Oci i cultura", "http://www.elperiodico.cat/ca/rss/oci-i-cultura/rss.xml", 0));
        add(new Section("Ci\u00E8ncia", "http://www.elperiodico.cat/ca/rss/ciencia/rss.xml", 0));
        add(new Section("Medi ambient", "http://www.elperiodico.cat/ca/rss/medi-ambient/rss.xml", 0));
        add(new Section("Sanitat", "http://www.elperiodico.cat/ca/rss/sanitat/rss.xml", 0));
        add(new Section("Gent i TV", "http://www.elperiodico.cat/ca/rss/gent/rss.xml", 0));
        add(new Section("eXtra", "http://www.elperiodico.cat/ca/rss/extra/rss.xml", 0));

        add(new Section("Ciutats", null, -1));
        add(new Section("Barcelona", "http://www.elperiodico.cat/ca/rss/barcelona/rss.xml", 1));
        add(new Section("Badalona", "http://www.elperiodico.cat/ca/rss/badalona/rss.xml", 1));

        add(new Section("Blogs", null, -1));
        add(new Section("Bloglobal", "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.183745446.1073564764.1445817152", 1));
        add(new Section("Destinos ", "http://www.visitdestinos.com/feed/", 1));

    }

}
