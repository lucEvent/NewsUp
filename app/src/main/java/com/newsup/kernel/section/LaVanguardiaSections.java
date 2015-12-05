package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class LaVanguardiaSections extends ArrayList<Section> {

    public LaVanguardiaSections() {
        super();

        add(new Section("Última hora", 0));
        add(new Section("Top Noticias", 0));

        add(new Section("Internacional", 0));
        add(new Section("Política", 0));
        add(new Section("Economía", 0));
        add(new Section("Sucesos", 0));
        add(new Section("Deportes", 0));
        add(new Section("Tecnología", 0));

        add(new Section("Vida", 0));
        add(new Section("Ciencia", 1));
        add(new Section("Comunicación", 1));
        add(new Section("La Contra", 1));

        add(new Section("Cultura", -1));
        add(new Section("Música", 0));
        add(new Section("Cine", 0));
        add(new Section("Libros", 0));
        add(new Section("Escenarios", 0));

        add(new Section("Gente", 0));

        add(new Section("Ocio", 0));
        add(new Section("Televisión", 1));
        add(new Section("Viajes", 1));
        add(new Section("Motor", 1));

        add(new Section("Participación", 0));

        add(new Section("Corresponsales", -1));
        add(new Section("Beirut: Tomás Alcoverro", 1));
        add(new Section("India: Jordi Joan Baños", 1));
        add(new Section("Diario itinerante: Andy Robinson", 1));
        add(new Section("Roma: Eusebio Val", 1));

        add(new Section("Blogs Actualidad", -1));
        add(new Section("No digas que se te mueren las plantas", 1));
        add(new Section("El Dardo", 1));
        add(new Section("Metamorfosis", 1));
        add(new Section("Pasos perdidos", 1));
        add(new Section("El otro escaño", 1));
        add(new Section("Valencia", 1));
        add(new Section("Desorientado en Oriente", 1));
        add(new Section("Valor añadido", 1));

        add(new Section("Blogs Gente", -1));
        add(new Section("Ailof", 1));
        add(new Section("Qué Llevas", 1));

        add(new Section("Blogs Tecnología", -1));
        add(new Section("The Fourth Bit", 1));
        add(new Section("Teclado Móvil", 1));

        add(new Section("Blogs Cultura", -1));
        add(new Section("El arquero", 1));

    }

}
