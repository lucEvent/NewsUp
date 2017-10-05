package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class FullMusculoSections extends Sections {

    public FullMusculoSections()
    {
        super();

        add(new Section("Principal", "http://fullmusculo.com/home/feed/", 0));

        add(new Section("Entrenamientos y ejercicios", "http://fullmusculo.com/home/category/entrenamientos_y_ejercicios/feed/", 0));
        add(new Section("Crecimiento Muscular", "http://fullmusculo.com/home/category/entrenamientos_y_ejercicios/crecimiento-muscular/feed/", 1));
        add(new Section("Definici\u00F3n", "http://fullmusculo.com/home/category/entrenamientos_y_ejercicios/definicion/feed/", 1));
        add(new Section("Ejercicios", "http://fullmusculo.com/home/category/entrenamientos_y_ejercicios/ejercicios/feed/", 1));
        add(new Section("Abdominales", "http://fullmusculo.com/home/category/entrenamientos_y_ejercicios/ejercicios/abdominales/feed/", 1));
        add(new Section("Entrenamiento", "http://fullmusculo.com/home/category/entrenamientos_y_ejercicios/entrenamiento/feed/", 1));

        add(new Section("Nutrici\u00F3n", "http://fullmusculo.com/home/category/nutricion/feed/", 0));
        add(new Section("Dieta", "http://fullmusculo.com/home/category/nutricion/dieta/feed/", 1));
        add(new Section("Receta", "http://fullmusculo.com/home/category/nutricion/receta/feed/", 1));
        add(new Section("Suplementos", "http://fullmusculo.com/home/category/nutricion/suplementos/feed/", 1));

        add(new Section("Salud y Vida", "http://fullmusculo.com/home/category/salud_y_vida/feed/", 0));
        add(new Section("Bajar de Peso", "http://fullmusculo.com/home/category/salud_y_vida/bajar_de_peso/feed/", 1));
        add(new Section("Consejos", "http://fullmusculo.com/home/category/salud_y_vida/consejos/feed/", 1));
        add(new Section("Salud", "http://fullmusculo.com/home/category/salud_y_vida/salud/feed/", 1));

    }

}
