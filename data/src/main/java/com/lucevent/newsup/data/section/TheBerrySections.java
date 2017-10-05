package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheBerrySections extends Sections {

    public TheBerrySections()
    {
        super();

        add(new Section("Main", "http://theberry.com/feed/", 0));
        add(new Section("Humor", "http://theberry.com/category/humor/feed/", 0));
        add(new Section("Fashion", "http://theberry.com/category/fashion/feed/", 0));
        add(new Section("Lifestyle", "http://theberry.com/category/lifestyle/feed/", 0));
        add(new Section("Entertainment", "http://theberry.com/category/entertainment/feed/", 0));
        add(new Section("Beauty", "http://theberry.com/category/beauty/feed/", 0));
        add(new Section("Body", "http://theberry.com/category/body/feed/", 0));
        add(new Section("Sex & Dating", "http://theberry.com/category/sex-relationship/feed/", 0));
        add(new Section("Books", "http://theberry.com/category/entertainment/book-club/feed/", 0));

    }

}
