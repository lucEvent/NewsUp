package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class AsSections extends ArrayList<Section> {

    public AsSections() {
        super();

        add(new Section("Titulares", 0));
        add(new Section("Últimas noticias", 0));

        add(new Section("Fútbol", 0));
        add(new Section("Liga BBVA", 1));
        add(new Section("Liga Adelante", 1));
        add(new Section("Copa del rey", 1));
        add(new Section("Champions league", 1));
        add(new Section("Europa league", 1));
        add(new Section("Segunda B", 1));
        add(new Section("Mundial", 1));
        add(new Section("Internacional", 1));

        add(new Section("Motor", 0));
        add(new Section("Formula 1", 1));
        add(new Section("Motociclismo", 1));
        add(new Section("Más motor", 1));

        add(new Section("Baloncesto", 0));
        add(new Section("Liga endesa", 1));
        add(new Section("NBA", 1));
        add(new Section("Euroliga", 1));
        add(new Section("Copa del rey", 1));
        add(new Section("Eurocup", 1));
        add(new Section("Más baloncesto", 1));

        add(new Section("Tenis", 0));
        add(new Section("Ciclismo", 0));
        add(new Section("Atletismo", 0));
        add(new Section("Balonmano", 0));
        add(new Section("Golf", 0));
        add(new Section("Juegos olimpicos", 0));
        add(new Section("Polideportivo", 0));
        add(new Section("Más deportes", 0));

        add(new Section("Opinión", 0));

        add(new Section("Equipos", -1));
        add(new Section("Almería", 1));
        add(new Section("Atlético de madrid", 1));
        add(new Section("Athletic de bilbao", 1));
        add(new Section("Barcelona", 1));
        add(new Section("Betis", 1));
        add(new Section("Celta", 1));
        add(new Section("Elche", 1));
        add(new Section("Espanyol", 1));
        add(new Section("Getafe", 1));
        add(new Section("Granada", 1));
        add(new Section("Levante", 1));
        add(new Section("Málaga", 1));
        add(new Section("Osasuna", 1));
        add(new Section("Rayo Vallecano", 1));
        add(new Section("Real Madrid", 1));
        add(new Section("Real Sociedad", 1));
        add(new Section("Sevilla", 1));
        add(new Section("Valencia", 1));
        add(new Section("Valladolid", 1));
        add(new Section("Villarreal", 1));

        add(new Section("Autores", -1));
        add(new Section("Alfredo Relaño", 1));
        add(new Section("Juan Mora", 1));
        add(new Section("Alejandro Elortegui", 1));
        add(new Section("Juanma Trueba", 1));
        add(new Section("Tomás Roncero", 1));
        add(new Section("Manolete", 1));
        add(new Section("Javier Matallanas", 1));

    }

}
