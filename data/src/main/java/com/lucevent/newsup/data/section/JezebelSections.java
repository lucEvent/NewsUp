package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class JezebelSections extends Sections {

	public JezebelSections()
	{
		super();

		add(new Section("Homepage", "https://jezebel.com/rss/vip", 0));
		add(new Section("TWSS", "https://thatswhatshesaid.jezebel.com/rss/vip", 0));
		add(new Section("The Muse", "https://themuse.jezebel.com/rss/vip", 0));
		add(new Section("Pictorial", "https://pictorial.jezebel.com/rss/vip", 0));
		add(new Section("The Slot", "https://theslot.jezebel.com/rss/vip", 0));

	}

}
