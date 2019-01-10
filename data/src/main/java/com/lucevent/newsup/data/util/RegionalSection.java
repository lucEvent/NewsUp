package com.lucevent.newsup.data.util;

public class RegionalSection extends Section {

	public final String language, region;

	public RegionalSection(String name, String url, int level, String language, String region)
	{
		super(name, url, level);
		this.language = language;
		this.region = region;
	}

}
