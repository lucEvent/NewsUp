package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ExpressenSections extends Sections {

	public ExpressenSections()
	{
		super();

		add(new Section("Nyheter", "https://feeds.expressen.se/nyheter", 0));
		add(new Section("V\u00E4stSverige GT", "https://feeds.expressen.se/gt", 0));
		add(new Section("SydSverige KVP", "https://feeds.expressen.se/kvallsposten", 0));

		add(new Section("Sport", "https://feeds.expressen.se/sport", 0));
		add(new Section("Fotboll", "https://feeds.expressen.se/fotboll", 1));
		add(new Section("Hockey", "https://feeds.expressen.se/hockey", 1));

		add(new Section("N\u00F6jesnyheter", "https://feeds.expressen.se/noje", 0));
		add(new Section("Debatt", "https://feeds.expressen.se/debatt", 0));
		add(new Section("Ledare", "https://feeds.expressen.se/ledare", 0));
		add(new Section("Kultur", "https://feeds.expressen.se/kultur", 0));
		add(new Section("H\u00E4lsa & Sk\u00F6nhet", "https://feeds.expressen.se/halsoliv", 0));
		add(new Section("Leva & Bo", "https://feeds.expressen.se/leva-och-bo/", 0));
		add(new Section("Motor", "https://feeds.expressen.se/motor", 0));
		add(new Section("Res", "https://feeds.expressen.se/allt-om-resor", 0));

	}

}
