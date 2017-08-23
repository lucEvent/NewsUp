package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LaRazonSections extends Sections {

    public LaRazonSections()
    {
        super();

        add(new Section("\u00DAltimas noticias", "http://www.larazon.es/rss/portada.xml", 0));

        add(new Section("Espa\u00F1a", "http://www.larazon.es/rss/espana.xml", 0));
        add(new Section("Andaluc\u00EDa", "http://www.larazon.es/rss/local/andalucia.xml", 1));
        add(new Section("Catalu\u00F1a", "http://www.larazon.es/rss/local/cataluna.xml", 1));
        add(new Section("Castilla y Le\u00F3n", "http://www.larazon.es/rss/local/castilla-y-leon.xml", 1));
        add(new Section("Comunidad Valenciana", "http://www.larazon.es/rss/local/comunidad-valenciana.xml", 1));
        add(new Section("Madrid", "http://www.larazon.es/rss/local/madrid.xml", 1));

        add(new Section("Deportes", "http://www.larazon.es/rss/deportes.xml", 0));
        add(new Section("F\u00FAtbol", "http://www.larazon.es/rss/deportes/futbol.xml", 1));
        add(new Section("Baloncesto", "http://www.larazon.es/rss/deportes/baloncesto.xml", 1));
        add(new Section("Tenis", "http://www.larazon.es/rss/deportes/tenis.xml", 1));
        add(new Section("Golf", "http://www.larazon.es/rss/deportes/golf.xml", 1));
        add(new Section("Motociclismo", "http://www.larazon.es/rss/deportes/motociclismo.xml", 1));
        add(new Section("Deportes", "http://www.larazon.es/rss/deportes.xml", 1));
        add(new Section("Ciclismo", "http://www.larazon.es/rss/deportes/ciclismo.xml", 1));

        add(new Section("Internacional", "http://www.larazon.es/rss/internacional.xml", 0));
        add(new Section("Econom\u00EDa", "http://www.larazon.es/rss/economia.xml", 0));
        add(new Section("Lifestyle", "http://www.larazon.es/rss/lifestyle.xml", 0));
        add(new Section("Religi\u00F3n", "http://www.larazon.es/rss/religion.xml", 0));
        add(new Section("Viajes", "http://www.larazon.es/rss/viajes.xml", 0));
        add(new Section("Verde", "http://www.larazon.es/rss/verde.xml", 0));

        add(new Section("Sociedad", "http://www.larazon.es/rss/sociedad.xml", 0));
        add(new Section("Ciencia", "http://www.larazon.es/rss/sociedad/ciencia.xml", 1));
        add(new Section("Comunicaci\u00F3n", "http://www.larazon.es/rss/sociedad/comunicacion.xml", 1));
        add(new Section("Educaci\u00F3n", "http://www.larazon.es/rss/sociedad/educacion.xml", 1));
        add(new Section("Medio Ambiente", "http://www.larazon.es/rss/sociedad/medio-ambiente.xml", 1));

        add(new Section("Cultura", "http://www.larazon.es/rss/cultura.xml", 0));
        add(new Section("Arte", "http://www.larazon.es/rss/cultura/arte.xml", 1));
        add(new Section("Cine", "http://www.larazon.es/rss/cultura/cine.xml", 1));
        add(new Section("Libros", "http://www.larazon.es/rss/cultura/libros.xml", 1));
        add(new Section("M\u00FAsica", "http://www.larazon.es/rss/cultura/musica.xml", 1));
        add(new Section("Teatro", "http://www.larazon.es/rss/cultura/teatro.xml", 1));

    }

}