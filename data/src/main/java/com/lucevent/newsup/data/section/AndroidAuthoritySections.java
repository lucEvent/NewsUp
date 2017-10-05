package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AndroidAuthoritySections extends Sections {

    public AndroidAuthoritySections()
    {
        super();

        add(new Section("Homepage", "http://www.androidauthority.com/feed/", 0));
        add(new Section("News", "http://www.androidauthority.com/news/feed/", 0));
        add(new Section("Deals", "http://www.androidauthority.com/deals/feed/", 0));

        add(new Section("Apps", "http://www.androidauthority.com/apps/feed/", 0));
        add(new Section("Lists", "http://www.androidauthority.com/tag/app-lists/feed/", 1));
        add(new Section("Games", "http://www.androidauthority.com/tag/gaming/feed/", 1));

        add(new Section("Reviews", "http://www.androidauthority.com/reviews/feed/", 0));
        add(new Section("The best", "http://www.androidauthority.com/best/feed/", 0));
        add(new Section("Features", "http://www.androidauthority.com/features/feed/", 0));
        add(new Section("How to", "http://www.androidauthority.com/how-to/feed/", 0));
        add(new Section("Android development", "http://www.androidauthority.com/android-development/feed/", 0));

        add(new Section("Opinions", "http://www.androidauthority.com/tag/opinions/feed/", 0));
        add(new Section("Podcast", "http://www.androidauthority.com/podcast/feed/", 0));
        add(new Section("Tips", "http://www.androidauthority.com/tag/tips/feed/", 0));

    }

}
