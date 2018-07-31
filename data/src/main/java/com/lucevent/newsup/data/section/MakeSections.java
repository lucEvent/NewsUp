package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MakeSections extends Sections {

	public MakeSections()
	{
		super();

		add(new Section("Homepage", "https://makezine.com/feed/", 0));

		add(new Section("Latest", null, -1));
		add(new Section("Maker pro", "https://makezine.com/tag/maker-pro/feed/", 1));
		add(new Section("Raspberry Pi 2", "https://makezine.com/tag/raspberry-pi-2/feed/", 1));
		add(new Section("Skill builder", "https://makezine.com/tag/skill-builder/feed/", 1));
		add(new Section("Makerspaces", "https://makezine.com/tag/makerspaces/feed/", 1));
		add(new Section("Maker faire", "https://makezine.com/tag/maker-faire/feed/", 1));
		add(new Section("Education", "https://makezine.com/tag/education/feed/", 1));

		add(new Section("Projects", "https://makezine.com/projects/feed/", 0));

		add(new Section("Technology", "https://makezine.com/category/technology/feed/", 0));
		add(new Section("Arduino", "https://makezine.com/category/technology/arduino/feed/", 1));
		add(new Section("Computers & Mobile", "https://makezine.com/category/technology/computers-mobile/feed/", 1));
		add(new Section("Raspberry Pi", "https://makezine.com/category/technology/raspberry-pi/feed/", 1));
		add(new Section("Internet of things", "https://makezine.com/category/technology/iot/feed/", 1));
		add(new Section("Robotics", "https://makezine.com/category/technology/robotics/feed/", 1));
		add(new Section("Microcontrollers", "https://makezine.com/category/technology/microcontrollers/feed/", 1));
		add(new Section("Wearables", "https://makezine.com/category/technology/wearables/feed/", 1));

		add(new Section("Digital fabrication", "https://makezine.com/category/digital-fabrication/feed/", 0));
		add(new Section("3D Printing & Imaging", "https://makezine.com/category/digital-fabrication/3d-printing-workshop/feed/", 1));
		add(new Section("CAD", "https://makezine.com/category/digital-fabrication/cad/feed/", 1));
		add(new Section("CNC & Machining", "https://makezine.com/category/digital-fabrication/machining/feed/", 1));

		add(new Section("Craft & Design", "https://makezine.com/category/craft/feed/", 0));
		add(new Section("Art & Sculpture", "https://makezine.com/category/craft/fine-art/feed/", 1));
		add(new Section("Makeup & Costumes", "https://makezine.com/category/craft/makeup-costumes/feed/", 1));
		add(new Section("Music", "https://makezine.com/category/craft/music/feed/", 1));
		add(new Section("Paper crafts", "https://makezine.com/category/craft/paper-crafts/feed/", 1));
		add(new Section("Photography & Video", "https://makezine.com/category/craft/photography-video/feed/", 1));
		add(new Section("Yarncraft", "https://makezine.com/category/craft/yarncraft/feed/", 1));

		add(new Section("Vehicles", "https://makezine.com/category/drones-vehicles/feed/", 0));
		add(new Section("Bikes", "https://makezine.com/category/drones-vehicles/bikes/feed/", 1));
		add(new Section("Cars", "https://makezine.com/category/drones-vehicles/cars/feed/", 1));
		add(new Section("Drones", "https://makezine.com/category/drones-vehicles/drones/feed/", 1));

		add(new Section("Science", "https://makezine.com/category/science/feed/", 0));
		add(new Section("Energy & Sustainability", "https://makezine.com/category/science/energy/feed/", 1));
		add(new Section("Health & Biohacking", "https://makezine.com/category/science/health-science/feed/", 1));

		add(new Section("Home", "https://makezine.com/category/home/feed/", 0));
		add(new Section("Connected home", "https://makezine.com/category/home/connected-home/feed/", 1));
		add(new Section("Food & Beverage", "https://makezine.com/category/home/food-beverage/feed/", 1));
		add(new Section("Fun & Games", "https://makezine.com/category/home/fun-games/feed/", 1));
		add(new Section("Furniture & Lighting", "https://makezine.com/category/home/furniture/feed/", 1));

		add(new Section("Workshop", "https://makezine.com/category/workshop/feed/", 0));
		add(new Section("Metalworking", "https://makezine.com/category/workshop/metalworking/feed/", 1));
		add(new Section("Woodworking", "https://makezine.com/category/workshop/woodworking/feed/", 1));

	}

}
