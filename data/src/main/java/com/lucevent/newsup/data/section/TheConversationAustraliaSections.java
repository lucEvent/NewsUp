package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheConversationAustraliaSections extends Sections {

	public TheConversationAustraliaSections()
	{
		super();

		add(new Section("Homepage", "http://theconversation.com/au/articles.rss", 0));
		add(new Section("Arts + Culture", "http://theconversation.com/au/arts/articles.rss", 0));
		add(new Section("Business + Economy", "http://theconversation.com/au/business/articles.rss", 0));
		add(new Section("Education", "http://theconversation.com/au/education/articles.rss", 0));
		add(new Section("Environment + Energy", "http://theconversation.com/au/environment/articles.rss", 0));
		add(new Section("FactCheck", "http://theconversation.com/au/factcheck/articles.rss", 0));
		add(new Section("Health + Medicine", "http://theconversation.com/au/health/articles.rss", 0));
		add(new Section("Politics + Society", "http://theconversation.com/au/politics/articles.rss", 0));
		add(new Section("Science + Technology", "http://theconversation.com/au/technology/articles.rss", 0));

	}

}
