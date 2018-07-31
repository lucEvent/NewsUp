package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AndroidAuthoritySections extends Sections {

	public AndroidAuthoritySections()
	{
		super();

		add(new Section("Homepage", "https://www.androidauthority.com/feed/", 0));
		add(new Section("Deals", "https://www.androidauthority.com/deals/feed/", 0));

		add(new Section("Apps", "https://www.androidauthority.com/apps/feed/", 0));
		add(new Section("Lists", "https://www.androidauthority.com/tag/app-lists/feed/", 1));
		add(new Section("Games", "https://www.androidauthority.com/tag/gaming/feed/", 1));

		add(new Section("Reviews", "https://www.androidauthority.com/reviews/feed/", 0));
		add(new Section("The best", "https://www.androidauthority.com/best/feed/", 0));
		add(new Section("Features", "https://www.androidauthority.com/features/feed/", 0));
		add(new Section("How to", "https://www.androidauthority.com/how-to/feed/", 0));
		add(new Section("Android development", "https://www.androidauthority.com/android-development/feed/", 0));

		add(new Section("Opinions", "https://www.androidauthority.com/tag/opinions/feed/", 0));
		add(new Section("Tips", "https://www.androidauthority.com/tag/tips/feed/", 0));

	}

}
