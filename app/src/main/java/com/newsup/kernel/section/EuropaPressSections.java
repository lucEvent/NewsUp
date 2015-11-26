package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class EuropaPressSections extends ArrayList<Section> {

    public EuropaPressSections() {
        super();

        add(new Section("Portada", 0));
        add(new Section("Nacional", 0));
        add(new Section("Internacional", 0));
        add(new Section("Economía", 0));
        add(new Section("EP Social", 0));
        add(new Section("Deportes", 0));
        add(new Section("Chance", 0));
        add(new Section("Cultura", 0));
        add(new Section("Sociedad", 0));
        add(new Section("Motor", 0));
        add(new Section("Comunicados", 0));

        add(new Section("Autonomías", 0));
        add(new Section("Andalucía", 1));
        add(new Section("Aragón", 1));
        add(new Section("Asturias", 1));
        add(new Section("C. Valenciana", 1));
        add(new Section("Cantabria", 1));
        add(new Section("Castilla-La Mancha", 1));
        add(new Section("Castilla y León", 1));
        add(new Section("Cataluña", 1));
        add(new Section("Ceuta y Melilla", 1));
        add(new Section("Extremadura", 1));
        add(new Section("Galicia", 1));
        add(new Section("Islas Baleares", 1));
        add(new Section("Islas Canarias", 1));
        add(new Section("La Rioja", 1));
        add(new Section("Madrid", 1));
        add(new Section("Murcia", 1));
        add(new Section("Navarra", 1));
        add(new Section("País Vasco", 1));

        add(new Section("Lenguas", 0));
        add(new Section("Euskera", 1));
        add(new Section("Galego", 1));
        add(new Section("Valencià ", 1));
        add(new Section("Asturianu", 1));

    }

}