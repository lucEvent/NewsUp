package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LaRazonSections extends Sections {

    public LaRazonSections()
    {
        super();

        add(new Section("Últimas noticias", "http://www.larazon.es/rss/portada.xml", 0));

        add(new Section("España", "http://www.larazon.es/rss/espana.xml", 0));
        add(new Section("Andalucía", "http://www.larazon.es/rss/local/andalucia.xml", 1));
        add(new Section("Cataluña", "http://www.larazon.es/rss/local/cataluna.xml", 1));
        add(new Section("Castilla y León", "http://www.larazon.es/rss/local/castilla-y-leon.xml", 1));
        add(new Section("Comunidad Valenciana", "http://www.larazon.es/rss/local/comunidad-valenciana.xml", 1));
        add(new Section("Madrid", "http://www.larazon.es/rss/local/madrid.xml", 1));

        add(new Section("Deportes", "http://www.larazon.es/rss/deportes.xml", 0));
        add(new Section("Fútbol", "http://www.larazon.es/rss/deportes/futbol.xml", 1));
        add(new Section("Baloncesto", "http://www.larazon.es/rss/deportes/baloncesto.xml", 1));
        add(new Section("Tenis", "http://www.larazon.es/rss/deportes/tenis.xml", 1));
        add(new Section("Golf", "http://www.larazon.es/rss/deportes/golf.xml", 1));
        add(new Section("Motociclismo", "http://www.larazon.es/rss/deportes/motociclismo.xml", 1));
        add(new Section("Formula 1", "http://www.larazon.es/rss/deportes/formula1.xml", 1));
        add(new Section("Deportes", "http://www.larazon.es/rss/deportes.xml", 1));
        add(new Section("Ciclismo", "http://www.larazon.es/rss/deportes/ciclismo.xml", 1));

        add(new Section("Internacional", "http://www.larazon.es/rss/internacional.xml", 0));
        add(new Section("Economía", "http://www.larazon.es/rss/economia.xml", 0));
        add(new Section("Lifestyle", "http://www.larazon.es/rss/lifestyle.xml", 0));
        add(new Section("Religión", "http://www.larazon.es/rss/religion.xml", 0));
        add(new Section("Viajes", "http://www.larazon.es/rss/viajes.xml", 0));
        add(new Section("Verde", "http://www.larazon.es/rss/verde.xml", 0));

        add(new Section("Sociedad", "http://www.larazon.es/rss/sociedad.xml", 0));
        add(new Section("Ciencia", "http://www.larazon.es/rss/sociedad/ciencia.xml", 1));
        add(new Section("Comunicación", "http://www.larazon.es/rss/sociedad/comunicacion.xml", 1));
        add(new Section("Educación", "http://www.larazon.es/rss/sociedad/educacion.xml", 1));
        add(new Section("Medio Ambiente", "http://www.larazon.es/rss/sociedad/medio-ambiente.xml", 1));
        add(new Section("Tecnología", "http://www.larazon.es/rss/sociedad/tecnologia.xml", 1));
        add(new Section("Videojuegos", "http://www.larazon.es/rss/sociedad/videojuegos.xml", 1));

        add(new Section("Cultura", "http://www.larazon.es/rss/cultura.xml", 0));
        add(new Section("Arte", "http://www.larazon.es/rss/cultura/arte.xml", 1));
        add(new Section("Cine", "http://www.larazon.es/rss/cultura/cine.xml", 1));
        add(new Section("Libros", "http://www.larazon.es/rss/cultura/libros.xml", 1));
        add(new Section("Música", "http://www.larazon.es/rss/cultura/musica.xml", 1));
        add(new Section("Teatro", "http://www.larazon.es/rss/cultura/teatro.xml", 1));

    }

}