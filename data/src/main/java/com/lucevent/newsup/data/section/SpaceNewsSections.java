package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SpaceNewsSections extends Sections {

	public SpaceNewsSections()
	{
		super();

		add(new Section("Space news", "https://spacenews.com/feed/", 0));
		add(new Section("Opinion", "https://spacenews.com/segment/opinion/feed/", 0));
		add(new Section("Launch", "https://spacenews.com/section/launch/feed/", 0));
		add(new Section("Business", "https://spacenews.com/section/business/feed/", 0));
		add(new Section("Missions", "https://spacenews.com/section/missions/feed/", 0));
		add(new Section("Policy & Politics", "https://spacenews.com/section/policy-politics/feed/", 0));
		add(new Section("People", "https://spacenews.com/section/people/feed/", 0));
		add(new Section("First-up", "https://spacenews.com/special-feature/first-up/feed/", 0));

	}

}