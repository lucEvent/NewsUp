package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LaVanguardiaSections extends Sections {

    public LaVanguardiaSections()
    {
        super();

        add(new Section("Última hora", "http://feeds.feedburner.com/lavanguardia/alminuto", 0));
        add(new Section("Top Noticias", "http://feeds.feedburner.com/lavanguardia/home", 0));

        add(new Section("Internacional", "http://feeds.feedburner.com/lavanguardia/internacional", 0));
        add(new Section("Política", "http://feeds.feedburner.com/lavanguardia/politica", 0));
        add(new Section("Economía", "http://feeds.feedburner.com/lavanguardia/economia", 0));
        add(new Section("Sucesos", "http://feeds.feedburner.com/lavanguardia/sucesos", 0));
        add(new Section("Deportes", "http://feeds.feedburner.com/lavanguardia/deportes", 0));
        add(new Section("Tecnología", "http://feeds.feedburner.com/lavanguardia/internet", 0));

        add(new Section("Vida", "http://feeds.feedburner.com/lavanguardia/vida", 0));
        add(new Section("Ciencia", "http://feeds.feedburner.com/lavanguardia/ciencia", 1));
        add(new Section("Comunicación", "http://feeds.feedburner.com/lavanguardia/comunicacion", 1));
        add(new Section("La Contra", "http://feeds.feedburner.com/lavanguardia/lacontra", 1));

        add(new Section("Cultura", null, -1));
        add(new Section("Música", "http://feeds.feedburner.com/lavanguardia/musica", 1));
        add(new Section("Cine", "http://feeds.feedburner.com/lavanguardia/cine", 1));
        add(new Section("Libros", "http://feeds.feedburner.com/lavanguardia/libros", 1));
        add(new Section("Escenarios", "http://feeds.feedburner.com/lavanguardia/escenarios", 1));

        add(new Section("Gente", "http://feeds.feedburner.com/lavanguardia/gente", 0));

        add(new Section("Ocio", "http://feeds.feedburner.com/lavanguardia/ocio", 0));
        add(new Section("Televisión", "http://feeds.feedburner.com/lavanguardia/television", 1));
        add(new Section("Viajes", "http://feeds.feedburner.com/lavanguardia/viajes", 1));
        add(new Section("Motor", "http://feeds.feedburner.com/lavanguardia/motor", 1));

        add(new Section("Participación", "http://feeds.feedburner.com/lavanguardia/participacion", 0));

        add(new Section("Corresponsales", null, -1));
        add(new Section("Beirut: Tomás Alcoverro", "http://feeds.feedburner.com/lavanguardia/diariodebeirut", 1));
        add(new Section("India: Jordi Joan Baños", "http://feeds.feedburner.com/lavanguardia/diariodeindia", 1));
        add(new Section("Diario itinerante: Andy Robinson", "http://feeds.feedburner.com/lavanguardia/diarioitinerante", 1));
        add(new Section("Roma: Eusebio Val", "http://feeds.feedburner.com/lavanguardia/diarioderoma", 1));

        add(new Section("Blogs", null, -1));
        add(new Section("Ailof", "http://feeds.feedburner.com/lavanguardia/ailof", 1));
        add(new Section("Desorientado en Oriente", "http://feeds.feedburner.com/lavanguardia/desorientadoenoriente", 1));
        add(new Section("El arquero", "http://feeds.feedburner.com/lavanguardia/elarquero", 1));
        add(new Section("El Dardo", "http://feeds.feedburner.com/lavanguardia/eldardo", 1));
        add(new Section("El otro escaño", "http://feeds.feedburner.com/lavanguardia/elotroescano", 1));
        add(new Section("Metamorfosis", "http://feeds.feedburner.com/lavanguardia/metamorfosis", 1));
        add(new Section("No digas que se te mueren las plantas", "http://feeds.feedburner.com/lavanguardia/plantas", 1));
        add(new Section("Pasos perdidos", "http://feeds.feedburner.com/lavanguardia/pasosperdidos", 1));
        add(new Section("Qué Llevas", "http://feeds.feedburner.com/lavanguardia/quellevas", 1));
        add(new Section("Teclado Móvil", "http://feeds.feedburner.com/lavanguardia/tecladomovil", 1));
        add(new Section("The Fourth Bit", "http://feeds.feedburner.com/lavanguardia/thefourthbit", 1));
        add(new Section("Valencia", "http://feeds.feedburner.com/lavanguardia/diariodevalencia", 1));
        add(new Section("Valor añadido", "http://feeds.feedburner.com/lavanguardia/valoranadido", 1));

    }

}
