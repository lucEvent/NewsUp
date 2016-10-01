package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElAndroideLibreSections extends Sections {

    public ElAndroideLibreSections()
    {
        super();

        add(new Section("Principal", "http://www.elandroidelibre.com/feed", 0));
        add(new Section("Aplicaciones", "http://www.elandroidelibre.com/category/aplicaciones/feed", 0));
        add(new Section("Juegos", "http://www.elandroidelibre.com/category/juegos/feed", 0));

    }

}
