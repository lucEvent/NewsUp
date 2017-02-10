package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class CodigoNuevoSections extends Sections {

    public CodigoNuevoSections()
    {
        super();

        add(new Section("Principal", "http://www.codigonuevo.com/feed/", 0));
        add(new Section("Actualidad", "http://www.codigonuevo.com/noticias/feed/", 0));
        add(new Section("Vida", "http://www.codigonuevo.com/vida/feed/", 0));
        add(new Section("Dinero", "http://www.codigonuevo.com/dinero/feed/", 0));
        add(new Section("Entretenimiento", "http://www.codigonuevo.com/entretenimiento/feed/", 0));
        add(new Section("Deportes", "http://www.codigonuevo.com/deportes/feed/", 0));
        add(new Section("M\u00FAsica", "http://www.codigonuevo.com/musica/feed/", 0));
        add(new Section("Humor", "http://www.codigonuevo.com/humor/feed/", 0));
        add(new Section("Agenda", "http://www.codigonuevo.com/agenda/feed/", 0));
        add(new Section("Salud", "http://www.codigonuevo.com/salud/feed/", 0));
        ;

    }

}
