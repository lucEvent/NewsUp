package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GizmodoEsSections extends Sections {

    public GizmodoEsSections()
    {
        super();

        add(new Section("Principal", "http://es.gizmodo.com/rss/vip", 0));
        add(new Section("Ciencia", "http://es.gizmodo.com/tag/ciencia/rss", 0));
        add(new Section("Pel\u00EDculas", "http://es.gizmodo.com/tag/peliculas/rss", 0));
        add(new Section("Videojuegos", "http://es.gizmodo.com/tag/videojuegos/rss", 0));
        add(new Section("Smartphones", "http://es.gizmodo.com/tag/smartphones/rss", 0));
        add(new Section("Viajes", "http://es.gizmodo.com/tag/viajes/rss", 0));
        add(new Section("Espacio", "http://es.gizmodo.com/tag/espacio/rss", 0));

    }

}
