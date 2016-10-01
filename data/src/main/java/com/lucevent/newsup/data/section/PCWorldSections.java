package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PCWorldSections extends Sections {

    public PCWorldSections()
    {
        super();

        add(new Section("All Stories", "http://www.pcworld.com/index.rss", 0));
        add(new Section("Reviews", "http://www.pcworld.com/reviews/index.rss", 0));
        add(new Section("How-Tos", "http://www.pcworld.com/howto/index.rss", 0));
        add(new Section("Video", "http://www.pcworld.com/video/index.rss", 0));

        add(new Section("Columns", null, -1));
        add(new Section("Hassle-Free PC", "http://www.pcworld.com/column/hassle-free-pc/index.rss", 1));

        add(new Section("Topics", null, -1));
        add(new Section("Business", "http://www.pcworld.com/category/business/index.rss", 1));
        add(new Section("Security & privacy", "http://www.pcworld.com/category/privacy/index.rss", 1));
        add(new Section("Windows", "http://www.pcworld.com/category/windows/index.rss", 1));

        add(new Section("Products", null, -1));
        add(new Section("Laptops", "http://www.pcworld.com/category/laptop-computers/index.rss", 1));
        add(new Section("Software", "http://www.pcworld.com/category/software/index.rss", 1));
        add(new Section("Printers", "http://www.pcworld.com/category/printers/index.rss", 1));
        add(new Section("Phones", "http://www.pcworld.com/category/phones/index.rss", 1));
        add(new Section("Tablets", "http://www.pcworld.com/category/tablets/index.rss", 1));

    }

}
