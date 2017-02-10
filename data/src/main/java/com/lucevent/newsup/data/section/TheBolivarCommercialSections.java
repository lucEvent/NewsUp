package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheBolivarCommercialSections extends Sections {

    public TheBolivarCommercialSections()
    {
        super();

        add(new Section("Homepage", "http://www.bolivarcommercial.com/?format=feed&type=atom", 0));
        add(new Section("News", "http://www.bolivarcommercial.com/newsx/?format=feed&type=atom", 0));
        add(new Section("Sports", "http://www.bolivarcommercial.com/sports/?format=feed&type=atom", 0));
        add(new Section("Opinion", "http://www.bolivarcommercial.com/newsx/opinion-alt/?format=feed&type=atom", 0));
        add(new Section("Obituaries", "http://www.bolivarcommercial.com/obituaries/?format=feed&type=atom", 0));

    }

}
