package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheConversationFranceSections extends Sections {

	public TheConversationFranceSections()
	{
		super();

		add(new Section("Page d'accueil", "http://theconversation.com/fr/articles.rss", 0));
		add(new Section("Arts + Culture", "http://theconversation.com/fr/arts/articles.rss", 0));
		add(new Section("\u00C9conomie + Entreprise", "http://theconversation.com/fr/economie/articles.rss", 0));
		add(new Section("\u00C9ducation", "http://theconversation.com/fr/education/articles.rss", 0));
		add(new Section("Environnement + \u00C9nergie", "http://theconversation.com/fr/environnement/articles.rss", 0));
		add(new Section("Politique + Soci\u00E9t\u00E9", "http://theconversation.com/fr/politique/articles.rss", 0));
		add(new Section("Sant\u00E9 + M\u00E9decine", "http://theconversation.com/fr/sante/articles.rss", 0));
		add(new Section("Science + Technologie", "http://theconversation.com/fr/technologie/articles.rss", 0));
		add(new Section("En anglais", "http://theconversation.com/fr/anglais/articles.rss", 0));

	}

}
