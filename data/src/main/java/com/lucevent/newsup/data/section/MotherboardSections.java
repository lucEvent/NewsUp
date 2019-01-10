package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.RegionalSection;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MotherboardSections extends Sections {

	public MotherboardSections()
	{
		super();

		add(new RegionalSection("Deutschland (Germany)", "https://motherboard.vice.com/de/rss", 0, "de", ""));
		add(new RegionalSection("France", "https://motherboard.vice.com/fr/rss", 0, "fr", ""));
		add(new RegionalSection("Italia", "https://motherboard.vice.com/it/rss", 0, "it", ""));
		add(new RegionalSection("Nederland (Netherlands)", "https://motherboard.vice.com/nl/rss", 0, "nl", ""));
		add(new RegionalSection("United States", "https://motherboard.vice.com/en_us/rss", 0, "en", ""));

	}

	@Override
	public boolean areRegional()
	{
		return true;
	}

	@Override
	protected Section getRegionalDefault()
	{
		return getRegional("en", "");
	}

}
