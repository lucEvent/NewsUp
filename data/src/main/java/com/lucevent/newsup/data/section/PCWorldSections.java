package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PCWorldSections extends Sections {

    public PCWorldSections()
    {
        super();

        add(new Section("Homepage", "http://www.pcworld.com/index.rss", 0));
        add(new Section("News", "http://www.pcworld.com/news/index.rss", 0));
        add(new Section("Reviews", "http://www.pcworld.com/reviews/index.rss", 0));
        add(new Section("How-Tos", "http://www.pcworld.com/howto/index.rss", 0));
        add(new Section("Video", "http://www.pcworld.com/video/index.rss", 0));

        add(new Section("Business", "http://www.pcworld.com/category/business/index.rss", 0));

        add(new Section("Laptops", "http://www.pcworld.com/category/laptop-computers/index.rss", 0));
        add(new Section("Accessories", "http://www.pcworld.com/category/laptop-accessories/index.rss", 1));

        add(new Section("Tablets", "http://www.pcworld.com/category/tablets/index.rss", 0));
        add(new Section("E-readers", "http://www.pcworld.com/category/e-readers/index.rss", 1));

        add(new Section("Phones", "http://www.pcworld.com/category/phones/index.rss", 0));
        add(new Section("Accessories", "http://www.pcworld.com/category/phone-accessories/index.rss", 1));

        add(new Section("Hardware", "http://www.pcworld.com/category/hardware/index.rss", 0));
        add(new Section("Computers", "http://www.pcworld.com/category/computers/index.rss", 1));
        add(new Section("Printers", "http://www.pcworld.com/category/printers/index.rss", 1));
        add(new Section("Cameras", "http://www.pcworld.com/category/cameras/index.rss", 1));
        add(new Section("Components", "http://www.pcworld.com/category/components/index.rss", 1));
        add(new Section("Computers accessories", "http://www.pcworld.com/category/computers-accessories/index.rss", 1));
        add(new Section("Displays", "http://www.pcworld.com/category/displays/index.rss", 1));
        add(new Section("Graphics cards", "http://www.pcworld.com/category/components-graphics/index.rss", 1));
        add(new Section("Networking", "http://www.pcworld.com/category/networking/index.rss", 1));

        add(new Section("Software", "http://www.pcworld.com/category/software/index.rss", 0));
        add(new Section("Games", "http://www.pcworld.com/category/software/software-games/index.rss", 1));
        add(new Section("Software security", "http://www.pcworld.com/category/software/software-security/index.rss", 1));
        add(new Section("Windows", "http://www.pcworld.com/category/windows/index.rss", 1));

        add(new Section("Security", "http://www.pcworld.com/category/security/index.rss", 0));
        add(new Section("Privacy", "http://www.pcworld.com/category/privacy/index.rss", 1));

        add(new Section("Gadgets", "http://www.pcworld.com/category/gadgets/index.rss", 0));
        add(new Section("Smartwatches", "http://www.pcworld.com/category/smartwatches/index.rss", 1));

        add(new Section("Blogs", null, -1));
        add(new Section("Answer Line", "http://www.pcworld.com/column/answer-line/index.rss", 1));
        add(new Section("Go Beyond Payments: What's Now and Next", "http://www.pcworld.com/column/go-beyond-payments-whats-now-and-next/index.rss", 1));
        add(new Section("Hassle-Free PC", "http://www.pcworld.com/column/hassle-free-pc/index.rss", 1));
        add(new Section("Max Productivity", "http://www.pcworld.com/column/max-productivity/index.rss", 1));
        add(new Section("Missing Pieces", "http://www.pcworld.com/column/missing-pieces/index.rss", 1));
        add(new Section("Power Up Your Passion", "http://www.pcworld.com/column/power-up-your-passion/index.rss", 1));
        add(new Section("Retro Tech", "http://www.pcworld.com/column/this-old-tech/index.rss", 1));
        add(new Section("Smart Mobile Tricks", "http://www.pcworld.com/column/smart-mobile-tricks/index.rss", 1));
        add(new Section("The Full Nerd", "http://www.pcworld.com/column/the-full-nerd/index.rss", 1));
        add(new Section("World Beyond Windows", "http://www.pcworld.com/column/world-beyond-windows/index.rss", 1));

    }

}
