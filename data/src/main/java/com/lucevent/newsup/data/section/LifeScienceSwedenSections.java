package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LifeScienceSwedenSections extends Sections {

    public LifeScienceSwedenSections()
    {
        super();

        add(new Section("Nyheter", "http://www.lifesciencesweden.se/feed/", 0));
        add(new Section("Medtech", "http://www.lifesciencesweden.se/medtech/feed/", 0));
        add(new Section("Pharma", "http://www.lifesciencesweden.se/pharma/feed/", 0));
        add(new Section("Forskning", "http://www.lifesciencesweden.se/forskning/feed/", 0));
        add(new Section("Labb", "http://www.lifesciencesweden.se/labbnyheter/feed/", 0));
        add(new Section("Jobb & Karri\u00E4r", "http://www.lifesciencesweden.se/jobb-karriar/feed/", 0));

    }

}
