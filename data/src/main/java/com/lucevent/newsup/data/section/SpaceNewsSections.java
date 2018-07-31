package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SpaceNewsSections extends Sections {

	public SpaceNewsSections()
	{
		super();

		add(new Section("Space news", "http://spacenews.com/feed/", 0));
		add(new Section("Opinion", "http://spacenews.com/segment/opinion/feed/", 0));
		add(new Section("Launch", "http://spacenews.com/section/launch/feed/", 0));
		add(new Section("Business", "http://spacenews.com/section/business/feed/", 0));
		add(new Section("Missions", "http://spacenews.com/section/missions/feed/", 0));
		add(new Section("Policy & Politics", "http://spacenews.com/section/policy-politics/feed/", 0));
		add(new Section("People", "http://spacenews.com/section/people/feed/", 0));
		add(new Section("First-up", "http://spacenews.com/special-feature/first-up/feed/", 0));

	}

}