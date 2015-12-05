package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class ElPaisSections extends ArrayList<Section> {

    public ElPaisSections() {
        super();

        add(new Section("Titulares", 0));
        add(new Section("Tit. Cataluña", 0));
        add(new Section("Tit. America", 0));
        add(new Section("Última hora", 0));
        add(new Section("Más leídos", 0));

        add(new Section("Política", 0));
        add(new Section("Economía", 0));
        add(new Section("Ciencia", 0));
        add(new Section("Tecnología", 0));
        add(new Section("Cultura", 0));
        add(new Section("Deportes", 0));

        add(new Section("Internacional", -1));
        add(new Section("America latina", 1));
        add(new Section("Europa", 1));
        add(new Section("EEUU", 1));
        add(new Section("Oriente próximo", 1));
        add(new Section("México", 1));

        add(new Section("España", -1));
        add(new Section("Andalucía", 1));
        add(new Section("Catalunya", 1));
        add(new Section("C. Valenciana", 1));
        add(new Section("Madrid", 1));
        add(new Section("Pais vasco", 1));
        add(new Section("Galicia", 1));

        add(new Section("Deportes", -1));
        add(new Section("Fútbol", 1));
        add(new Section("Motor", 1));
        add(new Section("Baloncesto", 1));
        add(new Section("Ciclismo", 1));
        add(new Section("Tenis", 1));

        add(new Section("Literatura", 0));
        add(new Section("Cine", 0));
        add(new Section("Música", 0));
        add(new Section("Teatro", 0));
        add(new Section("Danza", 0));
        add(new Section("Arte", 0));
        add(new Section("Arquitectura", 0));
        add(new Section("Estilo", 0));
        add(new Section("Televisión", 0));
        add(new Section("Sociedad", 0));
        add(new Section("Blogs", 0));
        add(new Section("Opinión", 0));
        add(new Section("Entrevistas", 0));

    }

}