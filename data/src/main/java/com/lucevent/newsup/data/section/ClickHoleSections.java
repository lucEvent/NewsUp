package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ClickHoleSections extends Sections {

	public ClickHoleSections()
	{
		super();

		add(new Section("Homepage", "https://www.clickhole.com/rss/vip", 0));
		add(new Section("Quizzes", "https://quizzes.clickhole.com/rss/vip", 0));
		add(new Section("Resistance Hole", "https://resistancehole.clickhole.com/rss/vip", 0));
		add(new Section("Patriot Hole", "https://patriothole.clickhole.com/rss/vip", 0));

	}

}
