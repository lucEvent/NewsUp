package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class TheTelegraphSections extends ArrayList<Section> {

    public TheTelegraphSections() {
        super();

        add(new Section("News", 0));
        add(new Section("UK News", 0));
        add(new Section("World News", 0));
        add(new Section("Politics", 0));
        add(new Section("How about that?", 0));
        add(new Section("Technology News", 0));
        add(new Section("Travel News", 0));
        add(new Section("The Royal Family", 1));
        add(new Section("Celebrity news", 1));
        add(new Section("Science News", 1));
        add(new Section("Earth News", 1));

        add(new Section("Finance", 0));
        add(new Section("Personal Finance", 0));
        add(new Section("Ambrose Evans-Pritchard", 1));
        add(new Section("Economics", 1));
        add(new Section("Markets", 1));
        add(new Section("Property", 0));
        add(new Section("International Property", 1));
        add(new Section("Property Market", 1));
        add(new Section("Property News", 1));

        add(new Section("Comment", 0));
        add(new Section("Columnists", 1));
        add(new Section("Simon Heffer", 1));

        add(new Section("Motoring", 0));
        add(new Section("Motoring News", 1));

        add(new Section("Sport", 0));
        add(new Section("Football", 0));
        add(new Section("Cricket", 1));
        add(new Section("Premier League", 1));
        add(new Section("Formula One", 1));
        add(new Section("Rugby Union", 1));

        add(new Section("Travel", 0));
        add(new Section("Hotels", 1));
        add(new Section("Cruises", 1));
        add(new Section("Destinations", 1));

        add(new Section("Culture", 0));
        add(new Section("Art", 1));
        add(new Section("Books", 1));
        add(new Section("Film", 1));
        add(new Section("Music", 1));
        add(new Section("TV and Radio", 1));

        add(new Section("Technology", 0));

        add(new Section("Picture Galleries", 0));
        add(new Section("Pictures of the day", 1));

        add(new Section("Food and Drink", 0));

    }

}
