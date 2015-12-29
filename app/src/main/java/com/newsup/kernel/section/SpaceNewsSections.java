package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class SpaceNewsSections extends ArrayList<Section> {

    public SpaceNewsSections() {
        super();

        add(new Section("Space news", 0));
        add(new Section("News", 0));
        add(new Section("Opinion", 0));
        add(new Section("Video", 0));
        add(new Section("Launch", 0));
        add(new Section("Business", 0));
        add(new Section("Missions", 0));
        add(new Section("Policy & Politics", 0));
        add(new Section("People", 0));
        add(new Section("First-up", 0));
        add(new Section("Space geeks", 0));

    }

}