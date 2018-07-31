package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MashableSections extends Sections {

	public MashableSections()
	{
		super();

		add(new Section("Main", "http://feeds.mashable.com/mashable/", 0));
		add(new Section("Social Media", "http://feeds.mashable.com/mashable/socialmedia/", 0));
		add(new Section("Tech", "http://feeds.mashable.com/mashable/tech/", 0));
		add(new Section("Business", "http://feeds.mashable.com/mashable/business/", 0));
		add(new Section("Entertainment", "http://feeds.mashable.com/mashable/entertainment/", 0));
		add(new Section("Lifestyle", "http://feeds.mashable.com/mashable/lifestyle/", 0));
		add(new Section("Watercooler", "http://feeds.mashable.com/mashable/watercooler/", 0));
		add(new Section("StartUps", "http://feeds.mashable.com/mashable/startups/", 0));

	}
}
