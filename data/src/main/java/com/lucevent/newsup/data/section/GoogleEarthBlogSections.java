package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GoogleEarthBlogSections extends Sections {

    public GoogleEarthBlogSections()
    {
        super();

        add(new Section("Main", "http://www.gearthblog.com/feed/", 0));
        add(new Section("Tips", "http://www.gearthblog.com/category/google_earth_tips/feed", 0));
        add(new Section("3D models", "http://www.gearthblog.com/category/3d_models/feed", 0));
        add(new Section("Sightseeing", "http://www.gearthblog.com/category/sightseeing/feed", 0));

    }

}
