package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPeriodicoCaSections extends Sections {

	public ElPeriodicoCaSections()
	{
		super();

		add(new Section("Portada", "https://www.elperiodico.cat/ca/rss/rss_portada.xml", 0));
		add(new Section("Opini\u00F3", "https://www.elperiodico.cat/ca/rss/opinio/rss.xml", 0));
		add(new Section("Internacional", "https://www.elperiodico.cat/ca/rss/internacional/rss.xml", 0));
		add(new Section("Pol\u00EDtica", "https://www.elperiodico.cat/ca/rss/politica/rss.xml", 0));
		add(new Section("Societat", "https://www.elperiodico.cat/ca/rss/societat/rss.xml", 0));
		add(new Section("Economia", "https://www.elperiodico.cat/ca/rss/economia/rss.xml", 0));
		add(new Section("Esports", "https://www.elperiodico.cat/ca/rss/esports/rss.xml", 0));
		add(new Section("Oci i cultura", "https://www.elperiodico.cat/ca/rss/oci-i-cultura/rss.xml", 0));
		add(new Section("Ci\u00E8ncia", "https://www.elperiodico.cat/ca/rss/ciencia/rss.xml", 0));
		add(new Section("Sanitat", "https://www.elperiodico.cat/ca/rss/sanitat/rss.xml", 0));
		add(new Section("Gent i TV", "https://www.elperiodico.cat/ca/rss/gent/rss.xml", 0));
		add(new Section("eXtra", "https://www.elperiodico.cat/ca/rss/extra/rss.xml", 0));

		add(new Section("Ciutats", null, -1));
		add(new Section("Barcelona", "https://www.elperiodico.cat/ca/rss/barcelona/rss.xml", 1));
		add(new Section("Santa Coloma", "https://www.elperiodico.cat/ca/rss/santa-coloma/rss.xml", 1));

		add(new Section("Blogs", null, -1));
		add(new Section("Bloglobal", "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.183745446.1073564764.1445817152", 1));
		add(new Section("Destinos", "https://www.visitdestinos.com/feed/", 1));

	}

}
