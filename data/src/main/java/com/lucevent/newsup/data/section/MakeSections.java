package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MakeSections extends Sections {

    public MakeSections()
    {
        super();

        add(new Section("Homepage", "http://makezine.com/feed/", 0));

        add(new Section("Latest", null, -1));
        add(new Section("Maker pro", "http://makezine.com/tag/maker-pro/feed/", 1));
        add(new Section("Raspberry Pi 2", "http://makezine.com/tag/raspberry-pi-2/feed/", 1));
        add(new Section("Skill builder", "http://makezine.com/tag/skill-builder/feed/", 1));
        add(new Section("Makerspaces", "http://makezine.com/tag/makerspaces/feed/", 1));
        add(new Section("Maker faire", "http://makezine.com/tag/maker-faire/feed/", 1));
        add(new Section("Education", "http://makezine.com/tag/education/feed/", 1));

        add(new Section("Projects", "http://makezine.com/projects/feed/", 0));

        add(new Section("Technology", "http://makezine.com/category/technology/feed/", 0));
        add(new Section("Arduino", "http://makezine.com/category/technology/arduino/feed/", 1));
        add(new Section("Computers & Mobile", "http://makezine.com/category/technology/computers-mobile/feed/", 1));
        add(new Section("Raspberry Pi", "http://makezine.com/category/technology/raspberry-pi/feed/", 1));
        add(new Section("Internet of things", "http://makezine.com/category/technology/iot/feed/", 1));
        add(new Section("Robotics", "http://makezine.com/category/technology/robotics/feed/", 1));
        add(new Section("Microcontrollers", "http://makezine.com/category/technology/microcontrollers/feed/", 1));
        add(new Section("Wearables", "http://makezine.com/category/technology/wearables/feed/", 1));

        add(new Section("Digital fabrication", "http://makezine.com/category/digital-fabrication/feed/", 0));
        add(new Section("3D Printing & Imaging", "http://makezine.com/category/digital-fabrication/3d-printing-workshop/feed/", 1));
        add(new Section("CAD", "http://makezine.com/category/digital-fabrication/cad/feed/  ", 1));
        add(new Section("CNC & Machining", "http://makezine.com/category/digital-fabrication/machining/feed/", 1));

        add(new Section("Craft & Design", "http://makezine.com/category/craft/feed/", 0));
        add(new Section("Art & Sculpture", "http://makezine.com/category/craft/fine-art/feed/", 1));
        add(new Section("Makeup & Costumes", "http://makezine.com/category/craft/makeup-costumes/feed/", 1));
        add(new Section("Music", "http://makezine.com/category/craft/music/feed/", 1));
        add(new Section("Paper crafts", "http://makezine.com/category/craft/paper-crafts/feed/", 1));
        add(new Section("Photography & Video", "http://makezine.com/category/craft/photography-video/feed/", 1));
        add(new Section("Yarncraft", "http://makezine.com/category/craft/yarncraft/feed/", 1));

        add(new Section("Vehicles", "http://makezine.com/category/drones-vehicles/feed/", 0));
        add(new Section("Bikes", "http://makezine.com/category/drones-vehicles/bikes/feed/", 1));
        add(new Section("Cars", "http://makezine.com/category/drones-vehicles/cars/feed/", 1));
        add(new Section("Drones", "http://makezine.com/category/drones-vehicles/drones/feed/", 1));

        add(new Section("Science", "http://makezine.com/category/science/feed/", 0));
        add(new Section("Energy & Sustainability", "http://makezine.com/category/science/energy/feed/", 1));
        add(new Section("Health & Biohacking", "http://makezine.com/category/science/health-science/feed/", 1));

        add(new Section("Home", "http://makezine.com/category/home/feed/", 0));
        add(new Section("Connected home", "http://makezine.com/category/home/connected-home/feed/", 1));
        add(new Section("Food & Beverage", "http://makezine.com/category/home/food-beverage/feed/", 1));
        add(new Section("Fun & Games", "http://makezine.com/category/home/fun-games/feed/", 1));
        add(new Section("Furniture & Lighting", "http://makezine.com/category/home/furniture/feed/", 1));

        add(new Section("Workshop", "http://makezine.com/category/workshop/feed/", 0));
        add(new Section("Metalworking", "http://makezine.com/category/workshop/metalworking/feed/", 1));
        add(new Section("Woodworking", "http://makezine.com/category/workshop/woodworking/feed/", 1));

    }

}
