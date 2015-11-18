package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class MakeSections extends ArrayList<Section> {

    public MakeSections() {
        super();


        add(new Section("Main page", 0));
        add(new Section("Maker pro", 0));

        add(new Section("Projects", 0));

        add(new Section("Technology", 0));
        add(new Section("Arduino", 1));
        add(new Section("Computers & Mobile", 1));
        add(new Section("Raspberry Pi", 1));
        add(new Section("Internet of things", 1));

        add(new Section("Digital fabrication", 0));
        add(new Section("3D printing", 1));

        add(new Section("Craft", 0));
        add(new Section("Paper crafts", 1));
        add(new Section("Music", 1));
        add(new Section("Art & Sculpture", 1));
        add(new Section("Makeup & Costumes", 1));

        add(new Section("Drones & Vehicles", 0));
        add(new Section("Cars", 1));
        add(new Section("Bikes", 1));
        add(new Section("Drones", 1));
        add(new Section("Planes", 1));
        add(new Section("Rockets", 1));

        add(new Section("Science", 0));
        add(new Section("Space", 1));

        add(new Section("Home", 0));
        add(new Section("Furniture", 1));
        add(new Section("Fun games", 1));
        add(new Section("Food & Beverage", 1));

        add(new Section("Workshop", 0));
        add(new Section("Woodworking", 1));

        add(new Section("Maker Faire", 0));

    }

}
