package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class DiarioCordobaSections extends ArrayList<Section> {

    public DiarioCordobaSections() {
        super();

        add(new Section("Portada", 0));
        add(new Section("Última hora", 0));
        add(new Section("Andalucía", 0));
        add(new Section("España", 0));
        add(new Section("Internacional", 0));

        add(new Section("Deportes", 0));
        add(new Section("Área Blanquiverde", 1));
        add(new Section("Atletismo", 1));
        add(new Section("Baloncesto", 1));
        add(new Section("Balonmano", 1));
        add(new Section("Hípica", 1));
        add(new Section("Polideportivo", 1));

        add(new Section("Cultura", 0));
        add(new Section("Cofradías", 1));

        add(new Section("Economía", 0));

        add(new Section("Provincia", 0));
        add(new Section("Alto Guadalquivir", 1));
        add(new Section("Cabra", 1));
        add(new Section("Local", 1));
        add(new Section("Lucena", 1));
        add(new Section("Montilla", 1));
        add(new Section("Palma del Río", 1));
        add(new Section("Pozoblanco", 1));

        add(new Section("Gente", 0));
        add(new Section("Sociedad", 0));
        add(new Section("Televisión", 0));
        add(new Section("Turismo", 0));

        add(new Section("Tema del día", 0));
        add(new Section("Opinión", 0));
        add(new Section("Tu Informas", 0));

        add(new Section("La Brimz X en Córdoba", 0));
        add(new Section("UCOniversitas", 0));

    }

}