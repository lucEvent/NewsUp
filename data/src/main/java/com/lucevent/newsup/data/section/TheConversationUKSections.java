package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheConversationUKSections extends Sections {

	public TheConversationUKSections()
	{
		super();

		add(new Section("Homepage", "http://theconversation.com/uk/articles.rss", 0));
		add(new Section("Arts + Culture", "http://theconversation.com/uk/arts/articles.rss", 0));
		add(new Section("Business + Economy", "http://theconversation.com/uk/business/articles.rss", 0));
		add(new Section("Education", "http://theconversation.com/uk/education/articles.rss", 0));
		add(new Section("Environment + Energy", "http://theconversation.com/uk/environment/articles.rss", 0));
		add(new Section("Health + Medicine", "http://theconversation.com/uk/health/articles.rss", 0));
		add(new Section("Politics + Society", "http://theconversation.com/uk/politics/articles.rss", 0));
		add(new Section("Science + Technology", "http://theconversation.com/uk/technology/articles.rss", 0));

	}

}
