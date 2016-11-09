package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPaisSections extends Sections {

    public ElPaisSections()
    {
        super();

        add(new Section("Titulares Espa\u00F1a", "http://ep00.epimg.net/rss/elpais/portada.xml", 0));
        add(new Section("Titulares Catalu\u00F1a", "http://ep00.epimg.net/rss/cat/portada.xml", 0));
        add(new Section("Titulares Am\u00E9rica", "http://ep00.epimg.net/rss/elpais/portada_america.xml", 0));
        add(new Section("Titulares Brasil", "http://ep00.epimg.net/rss/brasil/portada.xml", 0));

        add(new Section("\u00DAltimas not\u00EDcias", "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml", 0));
        add(new Section("M\u00E1s visto", "http://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml", 0));

        add(new Section("Internacional", null, -1));
        add(new Section("Am\u00E9rica latina", "http://elpais.com/tag/rss/latinoamerica/a/", 1));
        add(new Section("M\u00E9xico", "http://elpais.com/tag/rss/mexico/a/", 1));
        add(new Section("Europa", "http://elpais.com/tag/rss/europa/a/", 1));
        add(new Section("Estados Unidos", "http://elpais.com/tag/rss/estados_unidos/a/", 1));
        add(new Section("Oriente pr\u00F3ximo", "http://elpais.com/tag/rss/oriente_proximo/a/", 1));

        add(new Section("Opini\u00F3n", "http://ep00.epimg.net/rss/elpais/opinion.xml", 0));

        add(new Section("Espa\u00F1a", null, -1));
        add(new Section("Andaluc\u00EDa", "http://ep00.epimg.net/rss/ccaa/andalucia.xml", 1));
        add(new Section("Catalunya", "http://ep00.epimg.net/rss/ccaa/catalunya.xml", 1));
        add(new Section("C. Valenciana", "http://ep00.epimg.net/rss/ccaa/valencia.xml", 1));
        add(new Section("Madrid", "http://ep00.epimg.net/rss/ccaa/madrid.xml", 1));
        add(new Section("Pa\u00EDs Vasco", "http://ep00.epimg.net/rss/ccaa/paisvasco.xml", 1));
        add(new Section("Galicia", "http://ep00.epimg.net/rss/ccaa/galicia.xml", 1));

        add(new Section("Pol\u00EDtica", "http://ep00.epimg.net/rss/politica/portada.xml", 0));
        add(new Section("Econom\u00EDa", "http://ep00.epimg.net/rss/economia/portada.xml", 0));
        add(new Section("Ciencia", "http://ep00.epimg.net/rss/elpais/ciencia.xml", 0));
        add(new Section("Tecnolog\u00EDa", "http://ep00.epimg.net/rss/tecnologia/portada.xml", 0));

        add(new Section("Cultura", "http://ep00.epimg.net/rss/cultura/portada.xml", 0));
        add(new Section("Literatura", "http://elpais.com/tag/rss/libros/a/", 1));
        add(new Section("Cine", "http://elpais.com/tag/rss/cine/a/", 1));
        add(new Section("M\u00FAsica", "http://elpais.com/tag/rss/musica/a/", 1));
        add(new Section("Teatro", "http://elpais.com/tag/rss/teatro/a/", 1));
        add(new Section("Danza", "http://elpais.com/tag/rss/danza/a/", 1));
        add(new Section("Arte", "http://elpais.com/tag/rss/arte/a/", 1));
        add(new Section("Arquitectura", "http://elpais.com/tag/rss/arquitectura/a/", 1));

        add(new Section("Estilo", "http://ep00.epimg.net/rss/elpais/estilo.xml", 0));

        add(new Section("Deportes", "http://ep00.epimg.net/rss/deportes/portada.xml", 0));
        add(new Section("F\u00FAtbol", "http://elpais.com/tag/rss/futbol/a/", 1));
        add(new Section("Motor", "http://elpais.com/tag/rss/deportes_motor/a/", 1));
        add(new Section("Baloncesto", "http://elpais.com/tag/rss/baloncesto/a/", 1));
        add(new Section("Ciclismo", "http://elpais.com/tag/rss/ciclismo/a/", 1));
        add(new Section("Tenis", "http://elpais.com/tag/rss/tenis/a/", 1));

        add(new Section("Televisi\u00F3n", "http://ep00.epimg.net/rss/cultura/television.xml", 0));

        add(new Section("Sociedad", null, -1));
        add(new Section("Gente", "http://elpais.com/tag/rss/gente/a/", 1));
        add(new Section("Educaci\u00F3n", "http://elpais.com/tag/rss/educacion/a/", 1));
        add(new Section("Religi\u00F3n", "http://elpais.com/tag/rss/religion/a/", 1));
        add(new Section("Salud", "http://elpais.com/tag/rss/salud/a", 1));

        add(new Section("Blogs", "http://ep01.epimg.net/rss/elpais/blogs.xml", 0));

        add(new Section("Multimedia", null, -1));
        add(new Section("El d\u00EDa en im\u00E1genes", "http://elpais.com/agr/rss/el_dia_en_imagenes/a", 1));
        add(new Section("\u00DAltimas fotos", "http://elpais.com/tag/rss/albumes/a/", 1));
        add(new Section("Vi\u00F1etas", "http://elpais.com/tag/c/rss/ec7a643a2efd84d02c5948f7a9c86aa7", 1));
        add(new Section("Forges", "http://ep00.epimg.net/rss/tags/a_antonio_fraguas_forges_a.xml", 1));

    }

}
