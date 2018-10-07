package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TeknikensVarldSections extends Sections {

	public TeknikensVarldSections()
	{
		super();

		add(new Section("Alla nyheter", "https://teknikensvarld.se/feed/", 0));
		add(new Section("Bil & trafik", "https://teknikensvarld.se/kategori/nyheter/bil-och-trafik/feed/", 1));
		add(new Section("Elbil & laddhybrid", "https://teknikensvarld.se/kategori/nyheter/bil-och-trafik/elbil-laddhybrid/feed/", 1));
		add(new Section("Milj\u00F6 & teknik", "https://teknikensvarld.se/kategori/nyheter/miljo-och-teknik/feed/", 1));
		add(new Section("Bilbranschen", "https://teknikensvarld.se/kategori/nyheter/bilbranschen/feed/", 1));
		add(new Section("Konsument", "https://teknikensvarld.se/kategori/nyheter/konsument/feed/", 1));
		add(new Section("Bilspionen", "https://teknikensvarld.se/kategori/nyheter/bilspionen/feed/", 1));
		add(new Section("Styling & tuning", "https://teknikensvarld.se/kategori/nyheter/styling-tuning/feed/", 1));
		add(new Section("Motorsport", "https://teknikensvarld.se/kategori/nyheter/motorsport/feed/", 1));
		add(new Section("Aktuellt", "https://teknikensvarld.se/kategori/nyheter/aktuellt/feed/", 1));
		add(new Section("\u00D6vrigt", "https://teknikensvarld.se/kategori/nyheter/ovrigt/feed/", 1));

		add(new Section("Test och provk\u00F6rningar", "https://teknikensvarld.se/kategori/test-provkorningar/feed/", 0));

		add(new Section("Webb TV", "https://teknikensvarld.se/kategori/webb-tv/feed/", 0));

	}

}
