package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class FriaTiderSections extends Sections {

	public FriaTiderSections()
	{
		super();

		add(new Section("Huvudnyheter", "http://www.friatider.se/rss.xml", 0));

/*  Blocked links due to ddos-attack
		 add(new Section("Inrikes", "http://www.friatider.se/taxonomy/term/20/feed", 0));
		 add(new Section("Utrikes", "http://www.friatider.se/taxonomy/term/21/feed", 0));
		 add(new Section("Politik", "http://www.friatider.se/taxonomy/term/38/feed", 0));
		 add(new Section("Ekonomi", "http://www.friatider.se/taxonomy/term/2/feed", 0));
		 add(new Section("Vetenskap", "http://www.friatider.se/taxonomy/term/19/feed", 0));
		 add(new Section("Livsstil", "http://www.friatider.se/taxonomy/term/1/feed", 0));
		 add(new Section("Teknik", "http://www.friatider.se/taxonomy/term/52/feed", 0));
		 add(new Section("Kultur", "http://www.friatider.se/taxonomy/term/90/feed", 0));
		 add(new Section("Media", "http://www.friatider.se/taxonomy/term/36/feed", 0));
		 add(new Section("Debatt", "http://www.friatider.se/taxonomy/term/39/feed", 0));
		 add(new Section("Opinion", "http://www.friatider.se/taxonomy/term/22/feed", 0));
*/
	}

}
