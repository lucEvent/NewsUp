package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class LaRazonSections extends ArrayList<Section> {

    public LaRazonSections() {
        super();

        add(new Section("Últimas noticias", 0));

        add(new Section("España", 0));
        add(new Section("Andalucía", 1));
        add(new Section("Cataluña", 1));
        add(new Section("Castilla y León", 1));
        add(new Section("Comunidad Valenciana", 1));
        add(new Section("Madrid", 1));

        add(new Section("Deportes", 0));
        add(new Section("Fútbol", 1));
        add(new Section("Baloncesto", 1));
        add(new Section("Tenis", 1));
        add(new Section("Golf", 1));
        add(new Section("Motociclismo", 1));
        add(new Section("Formula 1", 1));
        add(new Section("Deportes", 1));
        add(new Section("Ciclismo", 1));

        add(new Section("Internacional", 0));
        add(new Section("Economía", 0));
        add(new Section("Lifestyle", 0));
        add(new Section("Religión", 0));
        add(new Section("Viajes", 0));
        add(new Section("Verde", 0));

        add(new Section("Sociedad", 0));
        add(new Section("Ciencia", 1));
        add(new Section("Comunicación", 1));
        add(new Section("Educación", 1));
        add(new Section("Medio Ambiente", 1));
        add(new Section("Tecnología", 1));
        add(new Section("Videojuegos", 1));

        add(new Section("Cultura", 0));
        add(new Section("Arte", 1));
        add(new Section("Cine", 1));
        add(new Section("Gastronomía", 1));
        add(new Section("Libros", 1));
        add(new Section("Música", 1));
        add(new Section("Teatro", 1));

    }

}