package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheGeekHammerSections extends Sections {

    public TheGeekHammerSections()
    {
        super();

        add(new Section("Principal", "http://thegeekhammer.com/feed", 0));
        add(new Section("Tecnolog\u00EDa", "http://thegeekhammer.com/category/tecnologia/feed", 0));
        add(new Section("Gadgets", "http://thegeekhammer.com/category/gadgets/feed", 0));
        add(new Section("Smartphones", "http://thegeekhammer.com/category/smartphones/feed", 0));
        add(new Section("Videojuegos", "http://thegeekhammer.com/category/videojuegos/feed", 0));
        add(new Section("Apps", "http://thegeekhammer.com/category/apps/feed", 0));
        add(new Section("An\u00E1lisis", "http://thegeekhammer.com/category/analisis/feed", 0));
        add(new Section("Wearables", "http://thegeekhammer.com/category/wearables/feed", 0));
        add(new Section("Internet", "http://thegeekhammer.com/category/internet/feed", 0));

    }

}
