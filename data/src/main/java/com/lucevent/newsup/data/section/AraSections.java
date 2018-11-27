package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AraSections extends Sections {

	public AraSections()
	{
		super();

		add(new Section("Portada", "https://www.ara.cat/rss/", 0));
		add(new Section("El m\u00e9s nou", "https://www.ara.cat/rss/latest/", 0));
		add(new Section("Pol\u00edtica", "https://www.ara.cat/rss/politica/", 0));
		add(new Section("Internacional", "https://www.ara.cat/rss/internacional/", 0));
		add(new Section("Societat", "https://www.ara.cat/rss/societat/", 0));
		add(new Section("Economia", "https://www.ara.cat/rss/economia/", 0));
		add(new Section("Estils i Gent", "https://www.ara.cat/rss/estils_i_gent/", 0));
		add(new Section("Cultura", "https://www.ara.cat/rss/cultura/", 0));

		add(new Section("Esports", "https://www.ara.cat/rss/esports/", 0));
		add(new Section("Bar\u00e7a", "https://www.ara.cat/rss/esports/barca/", 1));
		add(new Section("Futbol", "https://www.ara.cat/rss/esports/futbol/", 1));
		add(new Section("Espanyol", "https://www.ara.cat/rss/esports/futbol/espanyol/", 1));
		add(new Section("1a divisi\u00f3", "https://www.ara.cat/rss/esports/futbol/1a_divisio/", 1));
		add(new Section("2a divisi\u00f3", "https://www.ara.cat/rss/esports/futbol/2a_divisio/", 1));
		add(new Section("B\u00e0squet", "https://www.ara.cat/rss/esports/basquet/", 1));
		add(new Section("Motor", "https://www.ara.cat/rss/esports/motor/", 1));
		add(new Section("Tenis", "https://www.ara.cat/rss/esports/mes_esports/tenis/", 1));
		add(new Section("Ciclisme", "https://www.ara.cat/rss/esports/mes_esports/ciclisme/", 1));
		add(new Section("Hoquei patins", "https://www.ara.cat/rss/esports/mes_esports/hoquei_patins/", 1));
		add(new Section("Handbol", "https://www.ara.cat/rss/esports/mes_esports/handbol/", 1));
		add(new Section("Rugbi", "https://www.ara.cat/rss/esports/mes_esports/rugbi/", 1));
		add(new Section("Futbol Sala", "https://www.ara.cat/rss/esports/mes_esports/futbol_sala/", 1));
		add(new Section("Nataci\u00f3", "https://www.ara.cat/rss/esports/mes_esports/natacio/", 1));
		add(new Section("M\u00e9s esports", "https://www.ara.cat/rss/esports/mes_esports/", 1));

		add(new Section("M\u00e8dia", "https://www.ara.cat/rss/media/", 0));
		add(new Section("Tech", "https://www.ara.cat/rss/tecnologia/", 0));
		add(new Section("Fotografies", "https://www.ara.cat/rss/fotografies/", 0));

	}

}
