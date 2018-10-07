package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SwedroidSections extends Sections {

	public SwedroidSections()
	{
		super();

		add(new Section("Start", "https://swedroid.se/feed/", 0));

		add(new Section("Artiklar", null, -1));
		add(new Section("Android", "https://swedroid.se/category/android-2/feed/", 1));
		add(new Section("Chrome OS", "https://swedroid.se/category/chrome-os-2/feed/", 1));
		add(new Section("Google", "https://swedroid.se/category/google-2/feed/", 1));
		add(new Section("H\u00E5rdvara", "https://swedroid.se/category/hardvara/feed/", 1));
		add(new Section("Mediaenheter", "https://swedroid.se/category/mediaenheter/feed/", 1));
		add(new Section("Mjukvara", "https://swedroid.se/category/mjukvara/feed/", 1));
		add(new Section("Phablets", "https://swedroid.se/category/phablets-2/feed/", 1));
		add(new Section("Rykten", "https://swedroid.se/category/rykten/feed/", 1));
		add(new Section("Smartklockor", "https://swedroid.se/category/smartklockor-2/feed/", 1));
		add(new Section("Spel", "https://swedroid.se/category/spel-2/feed/", 1));
		add(new Section("Statistik", "https://swedroid.se/category/statistik/feed/", 1));
		add(new Section("Surfplattor", "https://swedroid.se/category/tablets/feed/", 1));
		add(new Section("S\u00E4kerhet", "https://swedroid.se/category/sakerhet-2/feed/", 1));
		add(new Section("Telefoner", "https://swedroid.se/category/telefoner/feed/", 1));
		add(new Section("Tillbeh\u00F6r", "https://swedroid.se/category/tillbehor/feed/", 1));
		add(new Section("Uppdateringar", "https://swedroid.se/category/uppdateringar-2/feed/", 1));
		add(new Section("Unders\u00F6kningar", "https://swedroid.se/category/undersokning/feed/", 1));
		add(new Section("Utveckling och ROM:ar", "https://swedroid.se/category/utveckling/feed/", 1));

	}

}