package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.RegionalSection;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ViceSections extends Sections {

	public ViceSections()
	{
		super();

		add(new RegionalSection("\u0639\u0631\u0628\u0649", "https://www.vice.com/ar/rss", 0, "ar", ""));
		add(new RegionalSection("Asia  (English)", "https://www.vice.com/en_asia/rss", 0, "en", "asia"));
		add(new RegionalSection("Australia", "https://www.vice.com/en_au/rss", 0, "en", "au"));
		add(new RegionalSection("Belgi\u00EB (Dutch)", "https://www.vice.com/be/rss", 0, "nl", "be"));
		add(new RegionalSection("Belgique (Fran\u00E7ais)", "https://www.vice.com/fr_be/rss", 0, "fr", "be"));
		add(new RegionalSection("Brasil", "https://www.vice.com/pt_br/rss", 0, "pt", "br"));
		add(new RegionalSection("Canada", "https://www.vice.com/en_ca/rss", 0, "en", "ca"));
		add(new RegionalSection("\u010Cesk\u00E1 Republika / Slova\u0161ka", "https://www.vice.com/cs/rss", 0, "cs", "cz"));
		add(new RegionalSection("Danmark", "https://www.vice.com/da/rss", 0, "da", "dk"));
		add(new RegionalSection("Deutschland", "https://www.vice.com/de/rss", 0, "de", "de"));
		add(new RegionalSection("Espa\u00F1a", "https://www.vice.com/es/rss", 0, "es", "es"));
		add(new RegionalSection("France", "https://www.vice.com/fr/rss", 0, "fr", "fr"));
		add(new RegionalSection("\u0395\u03BB\u03BB\u03AC\u03B4\u03B1", "https://www.vice.com/gr/rss", 0, "el", "gr"));
		add(new RegionalSection("India (English)", "https://www.vice.com/en_in/rss", 0, "en", "in"));
		add(new RegionalSection("Indonesia", "https://www.vice.com/id_id/rss", 0, "in", "id"));
		add(new RegionalSection("Italy", "https://www.vice.com/it/rss", 0, "it", "it"));
		add(new RegionalSection("\u65E5\u672C", "https://www.vice.com/jp/rss", 0, "ja", "jp"));
		add(new RegionalSection("Latinoam\u00E9rica", "https://www.vice.com/es_latam/rss", 0, "es", "latam"));
		add(new RegionalSection("Nederland", "https://www.vice.com/nl/rss", 0, "nl", "nl"));
		add(new RegionalSection("New Zealand", "https://www.vice.com/en_nz/rss", 0, "en", "nz"));
		add(new RegionalSection("\u00D6sterreich", "https://www.vice.com/de_at/rss", 0, "de", "at"));
		add(new RegionalSection("Polska", "https://www.vice.com/pl/rss", 0, "pl", "pl"));
		add(new RegionalSection("Portugal", "https://www.vice.com/pt/rss", 0, "pt", "pt"));
		add(new RegionalSection("Qu\u00E9bec", "https://www.vice.com/fr_ca/rss", 0, "fr", "ca"));
		add(new RegionalSection("Rom\u00E2nia", "https://www.vice.com/ro/rss", 0, "ro", "ro"));
		add(new RegionalSection("\u0420\u043E\u0441\u0441\u0438\u044F", "https://www.vice.com/ru/rss", 0, "ru", "ru"));
		add(new RegionalSection("Schweiz", "https://www.vice.com/de_ch/rss", 0, "de", "ch"));
		add(new RegionalSection("Srbija", "https://www.vice.com/rs/rss", 0, "sr", "rs"));
		add(new RegionalSection("Svenska", "https://www.vice.com/sv/rss", 0, "sv", "se"));
		add(new RegionalSection("UK", "https://www.vice.com/en_uk/rss", 0, "en", "gb"));
		add(new RegionalSection("USA", "https://www.vice.com/en_us/rss", 0, "en", "us"));

	}

	@Override
	public boolean areRegional()
	{
		return true;
	}

	@Override
	protected Section getRegionalDefault()
	{
		return getRegional("en", "us");
	}

}
