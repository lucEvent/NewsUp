package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MotherboardSections extends Sections {

	public MotherboardSections()
	{
		super();

		add(new Section("United States", "https://motherboard.vice.com/en_us/rss", 0));
		add(new Section("Deutschland (Germany)", "https://motherboard.vice.com/de/rss", 0));
		add(new Section("France", "https://motherboard.vice.com/fr/rss", 0));
		add(new Section("Nederland (Netherlands)", "https://motherboard.vice.com/nl/rss", 0));
		add(new Section("Italia", "https://motherboard.vice.com/it/rss", 0));

	}

}
