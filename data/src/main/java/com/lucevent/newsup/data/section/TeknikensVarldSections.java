package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TeknikensVarldSections extends Sections {

	public TeknikensVarldSections()
	{
		super();

		add(new Section("Alla nyheter", "http://teknikensvarld.se/feed/", 0));
		add(new Section("Bil & trafik", "http://teknikensvarld.se/kategori/nyheter/bil-och-trafik/feed/", 1));
		add(new Section("Elbil & laddhybrid", "http://teknikensvarld.se/kategori/nyheter/bil-och-trafik/elbil-laddhybrid/feed/", 1));
		add(new Section("Milj\u00F6 & teknik", "http://teknikensvarld.se/kategori/nyheter/miljo-och-teknik/feed/", 1));
		add(new Section("Bilbranschen", "http://teknikensvarld.se/kategori/nyheter/bilbranschen/feed/", 1));
		add(new Section("Konsument", "http://teknikensvarld.se/kategori/nyheter/konsument/feed/", 1));
		add(new Section("Bilspionen", "http://teknikensvarld.se/kategori/nyheter/bilspionen/feed/", 1));
		add(new Section("Styling & tuning", "http://teknikensvarld.se/kategori/nyheter/styling-tuning/feed/", 1));
		add(new Section("Motorsport", "http://teknikensvarld.se/kategori/nyheter/motorsport/feed/", 1));
		add(new Section("Aktuellt", "http://teknikensvarld.se/kategori/nyheter/aktuellt/feed/", 1));
		add(new Section("\u00D6vrigt", "http://teknikensvarld.se/kategori/nyheter/ovrigt/feed/", 1));

		add(new Section("Test och provk\u00F6rningar", "http://teknikensvarld.se/kategori/test-provkorningar/feed/", 0));

		add(new Section("Webb TV", "http://teknikensvarld.se/kategori/webb-tv/feed/", 0));

	}

}
