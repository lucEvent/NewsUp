package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class MundoDeportivoSections extends ArrayList<Section> {

    public MundoDeportivoSections() {
        super();

        add(new Section("Fútbol", 0));
        add(new Section("Fichajes", 0));

        add(new Section("Equipos", 0));
        add(new Section("Almería", 1));
        add(new Section("Athletic", 1));
        add(new Section("Atlético de Madrid", 1));
        add(new Section("Celta", 1));
        add(new Section("Córdoba", 1));
        add(new Section("Deportivo", 1));
        add(new Section("Eibar", 1));
        add(new Section("Elche", 1));
        add(new Section("Espanyol", 1));
        add(new Section("F.C.Barcelona", 1));
        add(new Section("Getafe", 1));
        add(new Section("Granada", 1));
        add(new Section("Levante", 1));
        add(new Section("Málaga", 1));
        add(new Section("Rayo Vallecano", 1));
        add(new Section("Real Madrid", 1));
        add(new Section("Real Sociedad", 1));
        add(new Section("Sevilla", 1));
        add(new Section("Valencia", 1));
        add(new Section("Villarreal", 1));

        add(new Section("Baloncesto", 0));
        add(new Section("ACB", 1));
        add(new Section("Euroliga", 1));
        add(new Section("NBA", 1));

        add(new Section("Motor", 0));
        add(new Section("Fórmula 1", 1));
        add(new Section("Motociclismo", 1));
        add(new Section("Rallies", 1));
        add(new Section("Dakar", 1));
        add(new Section("Más motor", 1));

        add(new Section("Tenis", 0));
        add(new Section("Más deporte", 0));

        add(new Section("Opinión", 0));
        add(new Section("¡Vaya Mundo!", 0));
        add(new Section("Ocio", 0));
        add(new Section("Hemeroteca", 0));

    }

}
