package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DeadspinSections extends Sections {

	public DeadspinSections()
	{
		super();

		add(new Section("Homepage", "https://deadspin.com/rss/vip", 0));
		add(new Section("The Concourse", "https://theconcourse.deadspin.com/rss/vip", 0));
		add(new Section("Adequate Man", "https://adequateman.deadspin.com/rss/vip", 0));

	}

}
