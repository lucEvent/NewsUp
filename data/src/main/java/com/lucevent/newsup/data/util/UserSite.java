package com.lucevent.newsup.data.util;

import com.lucevent.newsup.data.reader.UserSiteReader;

public class UserSite extends Site {

	public static final int ERROR_IN_GOOGLE_SEARCH = 0;
	public static final int ERROR_GETTING_PAGE = 1;
	public static final int ERROR_RSS_NOT_FOUND = 2;
	public static final int RSS_NOT_PRESENT = 3;
	public static final int OK = 3;

	public final String icon;

	public final String rssUrl;

	public UserSite(int code, String name, int color, String url, int info, String rssUrl, String icon)
	{
		super(code, name, color, url, info, Sections.class, UserSiteReader.class);
		this.rssUrl = rssUrl;
		this.icon = icon;
	}

	@Override
	public Sections getSections()
	{
		if (sections == null) {
			sections = new Sections();
			sections.add(new Section("Main", rssUrl, 0));
		}
		return sections;
	}

}
