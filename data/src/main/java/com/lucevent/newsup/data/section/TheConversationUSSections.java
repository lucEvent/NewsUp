package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheConversationUSSections extends Sections {

	public TheConversationUSSections()
	{
		super();

		add(new Section("Homepage", "http://theconversation.com/us/articles.rss", 0));
		add(new Section("Arts + Culture", "http://theconversation.com/us/arts/articles.rss", 0));
		add(new Section("Business + Economy", "http://theconversation.com/us/business/articles.rss", 0));
		add(new Section("Education", "http://theconversation.com/us/education/articles.rss", 0));
		add(new Section("Environment + Energy", "http://theconversation.com/us/environment/articles.rss", 0));
		add(new Section("Ethics + Religion", "http://theconversation.com/us/ethics/articles.rss", 0));
		add(new Section("Health + Medicine", "http://theconversation.com/us/health/articles.rss", 0));
		add(new Section("Politics + Society", "http://theconversation.com/us/politics/articles.rss", 0));
		add(new Section("Science + Technology", "http://theconversation.com/us/technology/articles.rss", 0));

	}

}
