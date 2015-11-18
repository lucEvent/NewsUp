package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class TimeSections extends ArrayList<Section> {

    public TimeSections() {
        super();

        add(new Section("News", 0));
        add(new Section("Top Stories", 0));
        add(new Section("Most Emailed", 0));

        add(new Section("Politics", 0));
        add(new Section("World", 0));
        add(new Section("Global Spin", 0));
        add(new Section("Moneyland", 0));
        add(new Section("Technology", 0));
        add(new Section("Health", 0));
        add(new Section("Science", 0));
        add(new Section("Entertainment", 0));

        add(new Section("10 Questions", 0));
        add(new Section("Joe Klein 's Columns", 0));

    }

}
