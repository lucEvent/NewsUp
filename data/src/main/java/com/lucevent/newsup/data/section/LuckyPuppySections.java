package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LuckyPuppySections extends Sections {

	public LuckyPuppySections()
	{
		super();

		add(new Section("Homepage", "https://www.luckypuppymag.com/feed/", 0));

		add(new Section("Live well", "https://www.luckypuppymag.com/category/livewell/feed/", 0));
		add(new Section("Want it bad", "https://www.luckypuppymag.com/want-it-bad/feed/", 1));
		add(new Section("Thrive", "https://www.luckypuppymag.com/thrive/feed/", 1));

		add(new Section("Do good", "https://www.luckypuppymag.com/category/dogood/feed/", 0));
		add(new Section("Rescue in the news", "https://www.luckypuppymag.com/rescue-in-the-news/feed/", 1));

		add(new Section("Be great", "https://www.luckypuppymag.com/category/begreat/feed/", 0));
		add(new Section("Who saved whom", "https://www.luckypuppymag.com/who-saved-whom/feed/", 1));
		add(new Section("Your pet's life coach", "https://www.luckypuppymag.com/your-pets-life-coach/feed/", 1));

	}

}
