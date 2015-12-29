package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class ElMundoSections extends ArrayList<Section> {

    public ElMundoSections() {
        super();

        add(new Section("Portada", 0));
        add(new Section("España", 0));
        add(new Section("Internacional", 0));
        add(new Section("Economía", 0));
        add(new Section("Cultura", 0));
        add(new Section("Ciencia", 0));

        add(new Section("Ciudades", -1));
        add(new Section("Alicante", 1));
        add(new Section("Castellón", 1));
        add(new Section("Madrid", 1));
        add(new Section("Málaga", 1));
        add(new Section("Sevilla", 1));
        add(new Section("Valencia", 1));

        add(new Section("Regiones", -1));
        add(new Section("Andalucía", 1));
        add(new Section("País vasco", 1));

        add(new Section("Solidaridad", 0));
        add(new Section("Comunicación", 0));
        add(new Section("Televisión", 0));
        add(new Section("Su vivienda", 0));
        add(new Section("Salud", 0));

        add(new Section("Deportes", 0));
        add(new Section("Fútbol", 1));
        add(new Section("Baloncesto", 1));
        add(new Section("Ciclismo", 1));
        add(new Section("Tenis", 1));

        add(new Section("Yo dona", 0));

    }

}

