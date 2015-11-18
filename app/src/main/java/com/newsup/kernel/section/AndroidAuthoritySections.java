package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class AndroidAuthoritySections extends ArrayList<Section> {

    public AndroidAuthoritySections() {
        super();

        add(new Section("Lasts", 0));
        add(new Section("News", 0));

        add(new Section("Reviews", 0));
        add(new Section("Apps", 1));
        add(new Section("Phones", 1));

        add(new Section("Apps", 0));
        add(new Section("Lists", 1));
        add(new Section("Games", 1));

        add(new Section("Features", 0));
        add(new Section("Opinions", 0));
        add(new Section("Podcast", 0));
        add(new Section("Tips", 0));
        add(new Section("Giveaways", 0));

    }

}
