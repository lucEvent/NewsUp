package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AndroidAuthoritySections extends Sections {

    public AndroidAuthoritySections() {
        super();

        add(new Section("Lasts", "http://feed.androidauthority.com/", 0));
        add(new Section("News", "http://www.androidauthority.com/news/feed/", 0));

        add(new Section("Reviews", "http://www.androidauthority.com/reviews/feed/", 0));
        add(new Section("Apps", "http://www.androidauthority.com/apps/app-reviews/feed/", 1));
        add(new Section("Phones", "http://www.androidauthority.com/reviews/phones/feed/", 1));

        add(new Section("Apps", "http://www.androidauthority.com/apps/feed/", 0));
        add(new Section("Lists", "http://www.androidauthority.com/apps/app-lists/feed/", 1));
        add(new Section("Games", "http://www.androidauthority.com/apps/games/feed/", 1));

        add(new Section("Features", "http://www.androidauthority.com/features/feed/", 0));
        add(new Section("Opinions", "http://www.androidauthority.com/features/opinions/feed/", 0));
        add(new Section("Podcast", "http://www.androidauthority.com/podcast/feed/", 0));
        add(new Section("Tips", "http://www.androidauthority.com/tips/feed/", 0));
        add(new Section("Giveaways", "http://www.androidauthority.com/giveaways/feed/", 0));

    }

}
