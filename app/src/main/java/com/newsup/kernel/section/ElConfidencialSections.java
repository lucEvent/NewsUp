package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class ElConfidencialSections extends ArrayList<Section> {

    public ElConfidencialSections() {
        super();

        add(new Section("España", 0));
        add(new Section("Mundo", 0));
        add(new Section("Comunicación", 0));
        add(new Section("Sociedad", 0));

        add(new Section("Opinión", 0));
        add(new Section("A. Casado", 1));
        add(new Section("J.A. Zarzalejos", 1));
        add(new Section("C. Sánchez", 1));
        add(new Section("El confidente", 1));

        add(new Section("Cotizalia", -1));
        add(new Section("Mercados", 1));
        add(new Section("Economía", 1));
        add(new Section("Empresas", 1));
        add(new Section("Finanzas personales", 1));
        add(new Section("Vivienda", 1));
        add(new Section("Fondos de inversión", 1));

        add(new Section("Teknautas", 0));
        add(new Section("Aplicaciones", 1));
        add(new Section("Emprendedores", 1));
        add(new Section("Gadgets", 1));
        add(new Section("Hardware", 1));
        add(new Section("Internet", 1));
        add(new Section("Móviles", 1));
        add(new Section("Redes sociales", 1));

        add(new Section("Deportes", 0));
        add(new Section("Fútbol", 1));
        add(new Section("Baloncesto", 1));
        add(new Section("Fórmula 1", 1));
        add(new Section("Motociclismo", 1));
        add(new Section("Tenis", 1));
        add(new Section("Ciclismo", 1));
        add(new Section("Golf", 1));
        add(new Section("Otros deportes", 1));

        add(new Section("Alma, Corazón, Vida", 0));
        add(new Section("Alimentación", 1));
        add(new Section("Bienestar", 1));
        add(new Section("Educación", 1));
        add(new Section("Psicología", 1));
        add(new Section("Salud", 1));
        add(new Section("Sexualidad", 1));
        add(new Section("Trabajo", 1));

        add(new Section("Cultura", 0));
        add(new Section("Libros", 1));
        add(new Section("Arte", 1));
        add(new Section("Cine", 1));
        add(new Section("Música", 1));

        add(new Section("Vanitatis", -1));
        add(new Section("Actualidad", 1));
        add(new Section("Tendencias", 1));
        add(new Section("Televisión", 1));
        add(new Section("Casas Reales", 1));
        add(new Section("Blogs", 1));

    }

}
