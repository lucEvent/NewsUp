package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_LaRazonNewsReader extends BE_NewsReader {

    public BE_LaRazonNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Últimas noticias", "http://www.larazon.es/rss/portada.xml"));

        SECTIONS.add(new BE_Section("España", "http://www.larazon.es/rss/espana.xml"));
        SECTIONS.add(new BE_Section("Andalucía", "http://www.larazon.es/rss/local/andalucia.xml"));
        SECTIONS.add(new BE_Section("Cataluña", "http://www.larazon.es/rss/local/cataluna.xml"));
        SECTIONS.add(new BE_Section("Castilla y León", "http://www.larazon.es/rss/local/castilla-y-leon.xml"));
        SECTIONS.add(new BE_Section("Comunidad Valenciana", "http://www.larazon.es/rss/local/comunidad-valenciana.xml"));
        SECTIONS.add(new BE_Section("Madrid", "http://www.larazon.es/rss/local/madrid.xml"));

        SECTIONS.add(new BE_Section("Deportes", "http://www.larazon.es/rss/deportes.xml"));
        SECTIONS.add(new BE_Section("Fútbol", "http://www.larazon.es/rss/deportes/futbol.xml"));
        SECTIONS.add(new BE_Section("Baloncesto", "http://www.larazon.es/rss/deportes/baloncesto.xml"));
        SECTIONS.add(new BE_Section("Tenis", "http://www.larazon.es/rss/deportes/tenis.xml"));
        SECTIONS.add(new BE_Section("Golf", "http://www.larazon.es/rss/deportes/golf.xml"));
        SECTIONS.add(new BE_Section("Motociclismo", "http://www.larazon.es/rss/deportes/motociclismo.xml"));
        SECTIONS.add(new BE_Section("Formula 1", "http://www.larazon.es/rss/deportes/formula1.xml"));
        SECTIONS.add(new BE_Section("Deportes", "http://www.larazon.es/rss/deportes.xml"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://www.larazon.es/rss/deportes/ciclismo.xml"));

        SECTIONS.add(new BE_Section("Internacional", "http://www.larazon.es/rss/internacional.xml"));
        SECTIONS.add(new BE_Section("Economía", "http://www.larazon.es/rss/economia.xml"));
        SECTIONS.add(new BE_Section("Lifestyle", "http://www.larazon.es/rss/lifestyle.xml"));
        SECTIONS.add(new BE_Section("Religión", "http://www.larazon.es/rss/religion.xml"));
        SECTIONS.add(new BE_Section("Viajes", "http://www.larazon.es/rss/viajes.xml"));
        SECTIONS.add(new BE_Section("Verde", "http://www.larazon.es/rss/verde.xml"));

        SECTIONS.add(new BE_Section("Sociedad", "http://www.larazon.es/rss/sociedad.xml"));
        SECTIONS.add(new BE_Section("Ciencia", "http://www.larazon.es/rss/sociedad/ciencia.xml"));
        SECTIONS.add(new BE_Section("Comunicación", "http://www.larazon.es/rss/sociedad/comunicacion.xml"));
        SECTIONS.add(new BE_Section("Educación", "http://www.larazon.es/rss/sociedad/educacion.xml"));
        SECTIONS.add(new BE_Section("Medio Ambiente", "http://www.larazon.es/rss/sociedad/medio-ambiente.xml"));
        SECTIONS.add(new BE_Section("Tecnología", "http://www.larazon.es/rss/sociedad/tecnologia.xml"));
        SECTIONS.add(new BE_Section("Videojuegos", "http://www.larazon.es/rss/sociedad/videojuegos.xml"));

        SECTIONS.add(new BE_Section("Cultura", "http://www.larazon.es/rss/cultura.xml"));
        SECTIONS.add(new BE_Section("Arte", "http://www.larazon.es/rss/cultura/arte.xml"));
        SECTIONS.add(new BE_Section("Cine", "http://www.larazon.es/rss/cultura/cine.xml"));
        SECTIONS.add(new BE_Section("Gastronomía", "http://www.larazon.es/rss/cultura/gastronomia.xml"));
        SECTIONS.add(new BE_Section("Libros", "http://www.larazon.es/rss/cultura/libros.xml"));
        SECTIONS.add(new BE_Section("Música", "http://www.larazon.es/rss/cultura/musica.xml"));
        SECTIONS.add(new BE_Section("Teatro", "http://www.larazon.es/rss/cultura/teatro.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.content = news.description;
        news.description = "";
        return news;
    }

}