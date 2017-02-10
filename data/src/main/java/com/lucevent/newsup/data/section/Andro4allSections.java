package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class Andro4allSections extends Sections {

    public Andro4allSections()
    {
        super();

        add(new Section("Principal", "http://andro4all.com/feed", 0));
        add(new Section("Accesorios para m\u00F3vil", "http://andro4all.com/categoria/accesorios/feed", 0));
        add(new Section("Aplicaciones Android", "http://andro4all.com/categoria/aplicaciones/feed", 0));
        add(new Section("Difoosion", "http://andro4all.com/categoria/difoosion/feed", 0));
        add(new Section("Marcas", "http://andro4all.com/categoria/marca/feed", 0));
        add(new Section("Operadoras", "http://andro4all.com/categoria/operadoras/feed", 0));
        add(new Section("Personalizaci\u00F3n", "http://andro4all.com/categoria/personalizacion/feed", 0));
        add(new Section("Recopilaciones y comparativas", "http://andro4all.com/categoria/recopilaciones/feed", 0));
        add(new Section("Sin categor\u00EDa", "http://andro4all.com/categoria/sin-categoria/feed", 0));
        add(new Section("Sistema Operativo", "http://andro4all.com/categoria/android-os/feed", 0));
        add(new Section("Tecnolog\u00EDa", "http://andro4all.com/categoria/tecnologia/feed", 0));

    }

}
