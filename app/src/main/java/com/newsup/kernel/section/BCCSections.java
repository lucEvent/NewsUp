package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class BCCSections extends ArrayList<Section> {

    public BCCSections() {
        super();

        add(new Section("Top stories", 0));
        add(new Section("World", 1));
        add(new Section("UK", 1));
        add(new Section("Business", 1));
        add(new Section("Politics", 1));
        add(new Section("Health", 1));
        add(new Section("Education & Family", 1));
        add(new Section("Science & Environment", 1));
        add(new Section("Technology", 1));
        add(new Section("Entertainment & Arts", 1));

        add(new Section("UK News", 0));
        add(new Section("England", 1));
        add(new Section("Northern Ireland", 1));
        add(new Section("Scotland", 1));
        add(new Section("Wales", 1));

        add(new Section("World News ", 0));
        add(new Section("Africa", 1));
        add(new Section("Asia", 1));
        add(new Section("Europe", 1));
        add(new Section("Latin America", 1));
        add(new Section("Middle east", 1));
        add(new Section("US & Canada", 1));

        add(new Section("Video & Audio", 0));
        add(new Section("Top stories", 1));
        add(new Section("World", 1));
        add(new Section("UK", 1));
        add(new Section("Business", 1));
        add(new Section("Politics", 1));
        add(new Section("Health", 1));
        add(new Section("Science & Environment", 1));
        add(new Section("Technology", 1));
        add(new Section("Entertainment & Arts", 1));

        add(new Section("Other News", 0));
        add(new Section("Latest published stories", 1));
        add(new Section("Magazine", 1));
        add(new Section("Also in the news", 1));
        add(new Section("In pictures", 1));
        add(new Section("Special Reports", 1));
        add(new Section("Have your say", 1));

        add(new Section("Sports", 0));
        add(new Section("Football", 1));
        add(new Section("Cricket", 1));
        add(new Section("Rugby Union", 1));
        add(new Section("Rugby League", 1));
        add(new Section("Tennis", 1));
        add(new Section("Golf", 1));
        add(new Section("Snooker", 1));

        add(new Section("Sports Video & Audio", 0));

    }

}
