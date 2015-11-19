package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class SportSections extends ArrayList<Section> {

    public SportSections() {
        super();

        add(new Section("Últimas noticias", 0));

        add(new Section("Fútbol", 0));

        add(new Section("Barça", 1));
        add(new Section("Madrid", 1));
        add(new Section("Espanyol", 1));
        add(new Section("Liga bbva", 1));
        add(new Section("Liga adelante", 1));
        add(new Section("Champions league", 1));
        add(new Section("Europa league", 1));
        add(new Section("Copa del rey", 1));
        add(new Section("Selección", 1));
        add(new Section("Futbol català", 1));
        add(new Section("Fútbol base", 1));
        add(new Section("Fútbol américa", 1));
        add(new Section("Fútbol internacional", 1));


        add(new Section("Basket", 0));
        add(new Section("Liga endesa", 1));
        add(new Section("Euroliga", 1));
        add(new Section("NBA", 1));
        add(new Section("Mundial baloncesto", 1));

        add(new Section("Internacional", -1));
        add(new Section("Inglaterra", 1));
        add(new Section("Francia", 1));
        add(new Section("Italia", 1));
        add(new Section("Alemania", 1));
        add(new Section("Resto del mundo", 1));

        add(new Section("Motor", 0));
        add(new Section("Fórmula 1", 1));
        add(new Section("Motociclismo", 1));
        add(new Section("Más motor", 1));
        add(new Section("Mundo motor", 1));

        add(new Section("Tenis", 0));
        add(new Section("Atletismo", 0));
        add(new Section("Ciclismo", 0));
        add(new Section("Hockey", 0));
        add(new Section("Fútbol sala", 0));
        add(new Section("Balonmano", 0));
        add(new Section("Pàdel", 0));
        add(new Section("Golf", 0));
        add(new Section("Deporte extremo", 0));
        add(new Section("Otros deportes", 0));

        add(new Section("Opinión", 0));
        add(new Section("Veteranos", 0));

    }

}