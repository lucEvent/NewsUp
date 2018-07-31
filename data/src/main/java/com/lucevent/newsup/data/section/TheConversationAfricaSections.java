package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheConversationAfricaSections extends Sections {

	public TheConversationAfricaSections()
	{
		super();

		add(new Section("Homepage", "http://theconversation.com/africa/articles.rss", 0));
		add(new Section("Arts + Culture", "http://theconversation.com/africa/arts/articles.rss", 0));
		add(new Section("Business + Economy", "http://theconversation.com/africa/business/articles.rss", 0));
		add(new Section("Education", "http://theconversation.com/africa/education/articles.rss", 0));
		add(new Section("Environment + Energy", "http://theconversation.com/africa/environment/articles.rss", 0));
		add(new Section("Health + Medicine", "http://theconversation.com/africa/health/articles.rss", 0));
		add(new Section("Politics + Society", "http://theconversation.com/africa/politics/articles.rss", 0));
		add(new Section("Science + Technology", "http://theconversation.com/africa/technology/articles.rss", 0));

	}

}
