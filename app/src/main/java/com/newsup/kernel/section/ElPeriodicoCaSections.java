package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class ElPeriodicoCaSections extends ArrayList<Section> {

    public ElPeriodicoCaSections() {
        super();

        add(new Section("Portada", 0));
        add(new Section("Opinió", 0));
        add(new Section("Internacional", 0));
        add(new Section("Política", 0));
        add(new Section("Societat", 0));
        add(new Section("Economía", 0));
        add(new Section("Tecnología", 0));
        add(new Section("Esports", 0));
        add(new Section("Oci i cultura", 0));
        add(new Section("Gent i TV", 0));

        add(new Section("Ciutats", -1));
        add(new Section("Barcelona", 1));
        add(new Section("L'Hospitalet", 1));
        add(new Section("Cornellà", 1));
        add(new Section("Sabadell", 1));
        add(new Section("Terrassa", 1));
        add(new Section("Badalona", 1));
        add(new Section("Santa Coloma", 1));

        add(new Section("Canal Bellesa", 0));
        add(new Section("Motor", 0));

        add(new Section("Blogs", -1));
        add(new Section("Los restaurantes de Pau Arenós", 1));
        add(new Section("Your disco needs you", 1));
        add(new Section("I can hear music", 1));
        add(new Section("Brutus", 1));
        add(new Section("Olor de tinta", 1));
        add(new Section("L'Escuradents", 1));
        add(new Section("Literatura barata", 1));
        add(new Section("Addictes al 8è art", 1));
        add(new Section("Cuaderno de viaje", 1));
        add(new Section("El Tourmalet", 1));
        add(new Section("Rumbo a la Casa Blanca #USA2016", 1));
        add(new Section("Bloglobal", 1));
        add(new Section("Destinos ", 1));
        add(new Section("+ Digital", 1));

    }

}
