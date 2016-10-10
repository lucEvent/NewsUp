package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ClipsetSections extends Sections {

    public ClipsetSections()
    {
        super();

        add(new Section("Principal", "http://clipset.20minutos.es/feed/", 0));
        add(new Section("Tecnolog\u00EDa", "http://clipset.20minutos.es/category/tecnologia/feed/", 0));
        add(new Section("M\u00F3viles", "http://clipset.20minutos.es/category/tecnologia/moviles-general-2/feed/", 1));
        add(new Section("Gadgets", "http://clipset.20minutos.es/category/tecnologia/gadgets/feed/", 1));
        add(new Section("Imagen", "http://clipset.20minutos.es/category/tecnologia/audiovideo/feed/", 1));
        add(new Section("Actualidad", "http://clipset.20minutos.es/category/actualidad/feed/", 0));
        add(new Section("Inclasificable", "http://clipset.20minutos.es/category/inclasificable/feed/", 0));
        add(new Section("Geek", "http://clipset.20minutos.es/category/inclasificable/como-ser-un-geek-en-30-dias/feed/", 1));
        add(new Section("Ocio", "http://clipset.20minutos.es/category/ocio/feed/", 0));
        add(new Section("Videojuegos", "http://clipset.20minutos.es/category/ocio/videojuegos/feed/", 1));
        add(new Section("Videos", "http://clipset.20minutos.es/category/video/feed/", 0));

    }

}
