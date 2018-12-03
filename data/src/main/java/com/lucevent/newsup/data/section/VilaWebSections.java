package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VilaWebSections extends Sections {

	public VilaWebSections()
	{
		super();

		add(new Section("Principal", "https://www.vilaweb.cat/rss/vilaweb.rss", 0));

		add(new Section("Pa\u00EDs", "https://www.vilaweb.cat/categoria/pais/feed/", 0));
		add(new Section("Andorra", "https://www.vilaweb.cat/categoria/pais/andorra/feed/", 1));
		add(new Section("Catalunya-nord", "https://www.vilaweb.cat/categoria/pais/catalunya-nord/feed/", 1));
		add(new Section("Franja-de-ponent", "https://www.vilaweb.cat/categoria/pais/franja-de-ponent/feed/", 1));
		add(new Section("Illes", "https://www.vilaweb.cat/categoria/pais/illes/feed/", 1));
		add(new Section("Pa\u00EDs-valenci\u00E0", "https://www.vilaweb.cat/categoria/pais/pais-valencia/feed/", 1));
		add(new Section("Principat", "https://www.vilaweb.cat/categoria/pais/principat/feed/", 1));

		add(new Section("M\u00F3n", "https://www.vilaweb.cat/categoria/mon/feed/", 0));
		add(new Section("\u00C1frica", "https://www.vilaweb.cat/categoria/mon/africa/feed/", 1));
		add(new Section("Am\u00E8rica", "https://www.vilaweb.cat/categoria/mon/america/feed/", 1));
		add(new Section("\u00C1sia-Pac\u00EDfic", "https://www.vilaweb.cat/categoria/mon/asia-pacific/feed/", 1));
		add(new Section("Europa", "https://www.vilaweb.cat/categoria/mon/europa/feed/", 1));
		add(new Section("Espanya", "https://www.vilaweb.cat/categoria/mon/espanya/feed/", 1));
		add(new Section("Fran\u00E7a", "https://www.vilaweb.cat/categoria/mon/franca/feed/", 1));
		add(new Section("Llevant", "https://www.vilaweb.cat/categoria/mon/llevant/feed/", 1));
		add(new Section("Occit\u00E0nia", "https://www.vilaweb.cat/categoria/mon/occitania/feed/", 1));
		add(new Section("Pa\u00EDs-basc", "https://www.vilaweb.cat/categoria/mon/pais-basc/feed/", 1));

		add(new Section("Societat", "https://www.vilaweb.cat/categoria/societat/feed/", 0));
		add(new Section("Ecologia", "https://www.vilaweb.cat/categoria/societat/ecologia/feed/", 1));
		add(new Section("Educaci\u00F3", "https://www.vilaweb.cat/categoria/societat/educacio/feed/", 1));
		add(new Section("Esports", "https://www.vilaweb.cat/categoria/societat/esports/feed/", 1));
		add(new Section("Policia i just\u00EDcia", "https://www.vilaweb.cat/categoria/societat/policia-i-justicia/feed/", 1));

		add(new Section("Opini\u00F3", "https://www.vilaweb.cat/categoria/opinio/feed/", 0));
		add(new Section("Mail obert", "https://www.vilaweb.cat/categoria/opinio/mail-obert/feed/", 1));
		add(new Section("Opini\u00F3 contundent", "https://www.vilaweb.cat/categoria/opinio/opinions-contundents/feed/", 1));

		add(new Section("Cultura", "https://www.vilaweb.cat/categoria/cultura/feed/", 0));
		add(new Section("Art i museus", "https://www.vilaweb.cat/categoria/cultura/art-i-museus/feed/", 1));
		add(new Section("Arts esc\u00E8niques", "https://www.vilaweb.cat/categoria/cultura/arts-esceniques/feed/", 1));
		add(new Section("Cinema", "https://www.vilaweb.cat/categoria/cultura/cinema/feed/", 1));
		add(new Section("Cultura popular", "https://www.vilaweb.cat/categoria/cultura/cultura-popular/feed/", 1));
		add(new Section("Gastronomia", "https://www.vilaweb.cat/categoria/cultura/gastronomia/feed/", 1));
		add(new Section("Llengua", "https://www.vilaweb.cat/categoria/cultura/llengua/feed/", 1));
		add(new Section("Lletres", "https://www.vilaweb.cat/categoria/cultura/lletres/feed/", 1));
		add(new Section("Mitjans de comunicaci\u00F3", "https://www.vilaweb.cat/categoria/cultura/mitjans-de-comunicacio/feed/", 1));
		add(new Section("M\u00FAsica", "https://www.vilaweb.cat/categoria/cultura/musica/feed/", 1));

		add(new Section("Economia", "https://www.vilaweb.cat/categoria/economia/feed/", 0));
		add(new Section("Empreses", "https://www.vilaweb.cat/categoria/economia/empreses/feed/", 1));
		add(new Section("Sindicalisme", "https://www.vilaweb.cat/categoria/economia/sindicalisme/feed/", 1));
		add(new Section("Tributs", "https://www.vilaweb.cat/categoria/economia/tributs/feed/", 1));

		add(new Section("Ci\u00E8ncia i tecnologia", "https://www.vilaweb.cat/categoria/ciencia-i-tecnologia/feed/", 0));
		add(new Section("Internet", "https://www.vilaweb.cat/categoria/ciencia-i-tecnologia/internet/feed/", 1));

		add(new Section("Publicacions", "https://www.vilaweb.cat/categoria/publicacions/feed/", 0));
		add(new Section("Cetrencada", "https://www.vilaweb.cat/categoria/publicacions/cetrencada/feed/", 1));
		add(new Section("Fes ta festa", "https://www.vilaweb.cat/categoria/publicacions/fes-ta-festa/feed/", 1));
		add(new Section("M\u00E8tode", "https://www.vilaweb.cat/categoria/ciencia-i-tecnologia/metode/feed/", 1));
		add(new Section("N\u00FAvol", "https://www.vilaweb.cat/categoria/publicacions/nuvol/feed/", 1));
		add(new Section("Tornaveu", "https://www.vilaweb.cat/categoria/publicacions/tornaveu/feed/", 1));
		add(new Section("Verkami", "https://www.vilaweb.cat/categoria/publicacions/verkami/feed/", 1));

		add(new Section("Locals", null, -1));
		add(new Section("Austr\u00E0lia", "https://australia.vilaweb.cat/feed/", 1));
		add(new Section("Castell\u00F3 de la Plana", "https://castello.vilaweb.cat/feed/", 1));
		add(new Section("Catalunya Nord", "https://catalunyanord.vilaweb.cat/feed/", 1));
		add(new Section("Mollerussa", "https://mollerussa.vilaweb.cat/feed/", 1));
		add(new Section("Ontinyent", "https://ontinyent.vilaweb.cat/feed/", 1));

		add(new Section("ACN", "https://www.vilaweb.cat/categoria/acn/feed/", 0));

	}

}
