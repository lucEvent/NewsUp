package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class MarcaSections extends ArrayList<Section> {

    public MarcaSections() {
        super();

        add(new Section("Notícias principales", 0));

        add(new Section("Fútbol", 0));
        add(new Section("1ª División", 1));
        add(new Section("2ª División", 1));
        add(new Section("Más fútbol", 1));
        add(new Section("Fútbol internacional", 1));
        add(new Section("Liga de campeones", 1));
        add(new Section("Copa de la UEFA", 1));
        add(new Section("Copa del Rey", 1));
        add(new Section("Selección", 1));
        add(new Section("Mundial 2014", 1));
        add(new Section("Fútbol sala", 1));

        add(new Section("Equipos", 0));
        add(new Section("Athletic", 1));
        add(new Section("Atlético", 1));
        add(new Section("Barcelona", 1));
        add(new Section("Betis", 1));
        add(new Section("Celta", 1));
        add(new Section("Deportivo", 1));
        add(new Section("Espanyol", 1));
        add(new Section("Getafe", 1));
        add(new Section("Granada", 1));
        add(new Section("Levante", 1));
        add(new Section("Málaga", 1));
        add(new Section("Mallorca", 1));
        add(new Section("Osasuna", 1));
        add(new Section("Rayo Vallecano", 1));
        add(new Section("Real Madrid", 1));
        add(new Section("Real Sociedad", 1));
        add(new Section("Sevilla", 1));
        add(new Section("Valencia", 1));
        add(new Section("Valladolid", 1));
        add(new Section("Zaragoza", 1));

        add(new Section("Baloncesto", 0));
        add(new Section("ACB", 1));
        add(new Section("NBA", 1));
        add(new Section("Euroliga", 1));
        add(new Section("Basket FEB", 1));
        add(new Section("Eurocup", 1));
        add(new Section("Selección", 1));

        add(new Section("Motor", 0));
        add(new Section("Formula 1", 1));
        add(new Section("Mundial de motos", 1));
        add(new Section("Rallies", 1));
        add(new Section("Gp2", 1));
        add(new Section("Superbikes", 1));
        add(new Section("Trial", 1));
        add(new Section("Más motor", 1));

        add(new Section("Tenis", 0));
        add(new Section("Ciclismo", 0));
        add(new Section("Golf", 0));
        add(new Section("Atletismo", 0));
        add(new Section("Balonmano", 0));
        add(new Section("Más deporte", 0));
        add(new Section("Vela", 1));

    }

}
