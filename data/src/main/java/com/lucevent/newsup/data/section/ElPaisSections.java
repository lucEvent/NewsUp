package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPaisSections extends Sections {

    public ElPaisSections()
    {
        super();

        add(new Section("Titulares", "http://ep00.epimg.net/rss/elpais/portada.xml", 0));
        add(new Section("Tit. Cataluña", "http://ep00.epimg.net/rss/cat/portada.xml", 0));
        add(new Section("Tit. America", "http://ep00.epimg.net/rss/elpais/portada_america.xml", 0));
        add(new Section("Última hora", "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml", 0));
        add(new Section("Más leídos", "http://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml", 0));

        add(new Section("Política", "http://ep00.epimg.net/rss/politica/portada.xml", 0));
        add(new Section("Economía", "http://ep00.epimg.net/rss/economia/portada.xml", 0));
        add(new Section("Ciencia", "http://ep00.epimg.net/rss/elpais/ciencia.xml", 0));
        add(new Section("Tecnología", "http://ep00.epimg.net/rss/tecnologia/portada.xml", 0));
        add(new Section("Cultura", "http://ep00.epimg.net/rss/cultura/portada.xml", 0));
        add(new Section("Deportes", "http://ep00.epimg.net/rss/deportes/portada.xml", 0));

        add(new Section("Internacional", null, -1));
        add(new Section("America latina", "http://elpais.com/tag/rss/latinoamerica/a/", 1));
        add(new Section("Europa", "http://elpais.com/tag/rss/mexico/a/", 1));
        add(new Section("EEUU", "http://elpais.com/tag/rss/europa/a/", 1));
        add(new Section("Oriente próximo", "http://elpais.com/tag/rss/estados_unidos/a/", 1));
        add(new Section("México", "http://elpais.com/tag/rss/oriente_proximo/a/", 1));

        add(new Section("España", null, -1));
        add(new Section("Andalucía", "http://ep00.epimg.net/rss/ccaa/andalucia.xml", 1));
        add(new Section("Catalunya", "http://ep00.epimg.net/rss/ccaa/catalunya.xml", 1));
        add(new Section("C. Valenciana", "http://ep00.epimg.net/rss/ccaa/valencia.xml", 1));
        add(new Section("Madrid", "http://ep00.epimg.net/rss/ccaa/madrid.xml", 1));
        add(new Section("Pais vasco", "http://ep00.epimg.net/rss/ccaa/paisvasco.xml", 1));
        add(new Section("Galicia", "http://ep00.epimg.net/rss/ccaa/galicia.xml", 1));

        add(new Section("Deportes", null, -1));
        add(new Section("Fútbol", "http://elpais.com/tag/rss/futbol/a/", 1));
        add(new Section("Motor", "http://elpais.com/tag/rss/deportes_motor/a/", 1));
        add(new Section("Baloncesto", "http://elpais.com/tag/rss/baloncesto/a/", 1));
        add(new Section("Ciclismo", "http://elpais.com/tag/rss/ciclismo/a/", 1));
        add(new Section("Tenis", "http://elpais.com/tag/rss/tenis/a/", 1));

        add(new Section("Literatura", "http://elpais.com/tag/rss/libros/a/", 0));
        add(new Section("Cine", "http://elpais.com/tag/rss/cine/a/", 0));
        add(new Section("Música", "http://elpais.com/tag/rss/musica/a/", 0));
        add(new Section("Teatro", "http://elpais.com/tag/rss/teatro/a/", 0));
        add(new Section("Danza", "http://elpais.com/tag/rss/danza/a/", 0));
        add(new Section("Arte", "http://elpais.com/tag/rss/arte/a/", 0));
        add(new Section("Arquitectura", "http://elpais.com/tag/rss/arquitectura/a/", 0));
        add(new Section("Estilo", "http://ep00.epimg.net/rss/elpais/estilo.xml", 0));
        add(new Section("Televisión", "http://ep00.epimg.net/rss/cultura/television.xml", 0));
        add(new Section("Sociedad", "http://ep00.epimg.net/rss/sociedad/portada.xml", 0));
        add(new Section("Blogs", "http://ep01.epimg.net/rss/elpais/blogs.xml", 0));
        add(new Section("Opinión", "http://ep00.epimg.net/rss/elpais/opinion.xml", 0));
        add(new Section("Entrevistas", "http://ep00.epimg.net/rss/elpais/entrevistasdigitales.xml", 0));

    }

}