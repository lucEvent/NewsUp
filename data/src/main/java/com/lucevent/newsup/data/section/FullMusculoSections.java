package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class FullMusculoSections extends Sections {

    public FullMusculoSections()
    {
        super();

        add(new Section("Principal", "https://fullmusculo.com/home/feed/", 0));

        add(new Section("Entrenamientos y ejercicios", "https://fullmusculo.com/home/category/entrenamientos_y_ejercicios/feed/", 0));
        add(new Section("Crecimiento Muscular", "https://fullmusculo.com/home/category/entrenamientos_y_ejercicios/crecimiento-muscular/feed/", 1));
        add(new Section("Definici\u00F3n", "https://fullmusculo.com/home/category/entrenamientos_y_ejercicios/definicion/feed/", 1));
        add(new Section("Ejercicios", "https://fullmusculo.com/home/category/entrenamientos_y_ejercicios/ejercicios/feed/", 1));
        add(new Section("Abdominales", "https://fullmusculo.com/home/category/entrenamientos_y_ejercicios/ejercicios/abdominales/feed/", 1));
        add(new Section("Entrenamiento", "https://fullmusculo.com/home/category/entrenamientos_y_ejercicios/entrenamiento/feed/", 1));

        add(new Section("Nutrici\u00F3n", "https://fullmusculo.com/home/category/nutricion/feed/", 0));
        add(new Section("Dieta", "https://fullmusculo.com/home/category/nutricion/dieta/feed/", 1));
        add(new Section("Receta", "https://fullmusculo.com/home/category/nutricion/receta/feed/", 1));
        add(new Section("Suplementos", "https://fullmusculo.com/home/category/nutricion/suplementos/feed/", 1));

        add(new Section("Salud y Vida", "https://fullmusculo.com/home/category/salud_y_vida/feed/", 0));
        add(new Section("Bajar de Peso", "https://fullmusculo.com/home/category/salud_y_vida/bajar_de_peso/feed/", 1));
        add(new Section("Consejos", "https://fullmusculo.com/home/category/salud_y_vida/consejos/feed/", 1));
        add(new Section("Salud", "https://fullmusculo.com/home/category/salud_y_vida/salud/feed/", 1));

    }

}
