package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PCWorldSections extends Sections {

	public PCWorldSections()
	{
		super();

		add(new Section("Homepage", "https://www.pcworld.com/index.rss", 0));
		add(new Section("News", "https://www.pcworld.com/news/index.rss", 0));
		add(new Section("Reviews", "https://www.pcworld.com/reviews/index.rss", 0));
		add(new Section("How-Tos", "https://www.pcworld.com/howto/index.rss", 0));

		add(new Section("Business", "https://www.pcworld.com/category/business/index.rss", 0));

		add(new Section("Laptops", "https://www.pcworld.com/category/laptop-computers/index.rss", 0));
		add(new Section("Laptop accessories", "https://www.pcworld.com/category/laptop-accessories/index.rss", 1));

		add(new Section("Tablets", "https://www.pcworld.com/category/tablets/index.rss", 0));
		add(new Section("E-readers", "https://www.pcworld.com/category/e-readers/index.rss", 1));

		add(new Section("Phone accessories", "https://www.pcworld.com/category/phone-accessories/index.rss", 0));

		add(new Section("Hardware", "https://www.pcworld.com/category/hardware/index.rss", 0));
		add(new Section("Computers", "https://www.pcworld.com/category/computers/index.rss", 1));
		add(new Section("Printers", "https://www.pcworld.com/category/printers/index.rss", 1));
		add(new Section("Cameras", "https://www.pcworld.com/category/cameras/index.rss", 1));
		add(new Section("Components", "https://www.pcworld.com/category/components/index.rss", 1));
		add(new Section("Computers accessories", "https://www.pcworld.com/category/computers-accessories/index.rss", 1));
		add(new Section("Displays", "https://www.pcworld.com/category/displays/index.rss", 1));
		add(new Section("Graphics cards", "https://www.pcworld.com/category/components-graphics/index.rss", 1));
		add(new Section("Networking", "https://www.pcworld.com/category/networking/index.rss", 1));

		add(new Section("Software", "https://www.pcworld.com/category/software/index.rss", 0));
		add(new Section("Windows", "https://www.pcworld.com/category/windows/index.rss", 1));

		add(new Section("Security", "https://www.pcworld.com/category/security/index.rss", 0));
		add(new Section("Privacy", "https://www.pcworld.com/category/privacy/index.rss", 1));

		add(new Section("Gadgets", "https://www.pcworld.com/category/gadgets/index.rss", 0));

		add(new Section("Blogs", null, -1));
		add(new Section("Hassle-Free PC", "https://www.pcworld.com/column/hassle-free-pc/index.rss", 1));
		add(new Section("Max Productivity", "https://www.pcworld.com/column/max-productivity/index.rss", 1));
		add(new Section("Missing Pieces", "https://www.pcworld.com/column/missing-pieces/index.rss", 1));
		add(new Section("The Full Nerd", "https://www.pcworld.com/column/the-full-nerd/index.rss", 1));

	}

}
