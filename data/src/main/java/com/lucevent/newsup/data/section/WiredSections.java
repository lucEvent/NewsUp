package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class WiredSections extends Sections {

	public WiredSections()
	{
		super();

		add(new Section("Top Stories", "https://www.wired.com/feed/rss", 0));
		add(new Section("Business", "https://www.wired.com/feed/category/business/latest/rss", 0));
		add(new Section("Culture", "https://www.wired.com/feed/category/culture/latest/rss", 0));
		add(new Section("Design", "https://www.wired.com/feed/category/design/latest/rss", 0));
		add(new Section("Gear", "https://www.wired.com/feed/category/gear/latest/rss", 0));
		add(new Section("Science", "https://www.wired.com/feed/category/science/latest/rss", 0));
		add(new Section("Security", "https://www.wired.com/feed/category/security/latest/rss", 0));
		add(new Section("Transportation", "https://www.wired.com/feed/category/transportation/latest/rss", 0));
		add(new Section("Photo", "https://www.wired.com/feed/category/photo/latest/rss", 0));
		add(new Section("Backchannel", "https://www.wired.com/feed/backchannel/rss", 0));

	}

}
